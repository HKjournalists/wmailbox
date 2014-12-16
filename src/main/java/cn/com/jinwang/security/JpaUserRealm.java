package cn.com.jinwang.security;

import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.com.jinwang.domain.LocalRole;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.repository.LocalUserRepository;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class JpaUserRealm extends AuthorizingRealm {

  private LocalUserRepository userRepo;

  public JpaUserRealm(LocalUserRepository userRepo, CredentialsMatcher cm) {
    super(cm);
    this.userRepo = userRepo;
  }


  /**
   * 需要权限的时候会调用
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // null usernames are invalid
    if (principals == null) {
      throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
    }

    JwPrinciple mp = (JwPrinciple) getAvailablePrincipal(principals);

    Optional<LocalUser> sfuOp = userRepo.findById(mp.getUserId());
    if (sfuOp.isPresent()) {
      Set<LocalRole> localRoles = sfuOp.get().getRoles();
      Set<MixDomainPermission> mps = sfuOp.get().getPermissions();
      Set<UserGroup> groups = sfuOp.get().getGroups();

      for (UserGroup ug : groups) {
        localRoles.addAll(ug.getRoles());
        mps.addAll(ug.getPermissions());
      }

      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

      Set<Permission> wps = Sets.newHashSet();
      for (MixDomainPermission m : mps) {
        wps.add(m.toWildcardPermission());
      }
      info.setObjectPermissions(wps);
      Set<String> roles = Sets.newHashSet();
      for (LocalRole lr : localRoles) {
        roles.add(lr.getName());
      }
      info.setRoles(roles);
      return info;
    }

    return null;
  }

  /**
   * AuthenticationInfo represents a Subject's (aka user's) stored account information relevant to
   * the authentication/log-in process only. It is important to understand the difference between
   * this interface and the AuthenticationToken interface. AuthenticationInfo implementations
   * represent already-verified and stored account data, whereas an AuthenticationToken represents
   * data submitted for any given login attempt (which may or may not successfully match the
   * verified and stored account AuthenticationInfo). Because the act of authentication (log-in) is
   * orthogonal to authorization (access control), this interface is intended to represent only the
   * account data needed by Shiro during an authentication attempt. Shiro also has a parallel
   * AuthorizationInfo interface for use during the authorization process that references access
   * control data such as roles and permissions. But because many if not most Realms store both sets
   * of data for a Subject, it might be convenient for a Realm implementation to utilize an
   * implementation of the Account interface instead, which is a convenience interface that combines
   * both AuthenticationInfo and AuthorizationInfo. Whether you choose to implement these two
   * interfaces separately or implement the one Account interface for a given Realm is entirely
   * based on your application's needs or your preferences. Pleae note: Since Shiro sometimes logs
   * authentication operations, please ensure your AuthenticationInfo's toString() implementation
   * does not print out account credentials (password, etc), as these might be viewable to someone
   * reading your logs. This is good practice anyway, and account credentials should rarely (if
   * ever) be printed out for any reason. If you're using Shiro's default implementations of this
   * interface, they only ever print the account principals, so you do not need to do anything
   * additional.
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {

    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    String username = upToken.getUsername();

    // Null username is invalid
    if (username == null) {
      throw new AccountException("Null usernames are not allowed by this realm.");
    }

    SimpleAuthenticationInfo info = null;
    Optional<LocalUser> user = userRepo.findByEmailOrMobile(username);

    if (!user.isPresent()) {
      throw new UnknownAccountException("No account found for user [" + username + "]");
    }
    String password = user.get().getPassword();
    String saltBase64 = user.get().getPasswordSalt();
    Sha256Hash dpassword = Sha256Hash.fromBase64String(password);
    info = new SimpleAuthenticationInfo(new JwPrinciple(user.get()), dpassword, "myUserRealm");

    if (saltBase64 != null) {
      byte[] salt = Base64.decode(saltBase64);
      info.setCredentialsSalt(ByteSource.Util.bytes(salt));
    }
    return info;
  }

}
