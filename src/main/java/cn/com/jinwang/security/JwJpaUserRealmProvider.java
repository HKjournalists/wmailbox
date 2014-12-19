package cn.com.jinwang.security;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import cn.com.jinwang.constant.ConstantBase;
import cn.com.jinwang.repository.LocalUserRepository;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class JwJpaUserRealmProvider implements Provider<JpaUserRealm> {

  private LocalUserRepository userRepo;

  @Inject
  public JwJpaUserRealmProvider(LocalUserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public JpaUserRealm get() {
    HashedCredentialsMatcher hc = new HashedCredentialsMatcher(ConstantBase.PASSWORD_HASHALGORITHM);
    hc.setHashIterations(ConstantBase.PASSWORD_HASH_ITERATIONS);
    JpaUserRealm jrm = new JpaUserRealm(userRepo, hc);
    return jrm;
  }

}
