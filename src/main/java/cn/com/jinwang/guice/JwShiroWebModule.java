package cn.com.jinwang.guice;

import javax.servlet.ServletContext;

import org.apache.shiro.guice.web.ShiroWebModule;

import cn.com.jinwang.security.JwJpaUserRealmProvider;

public class JwShiroWebModule extends ShiroWebModule {

  public JwShiroWebModule(ServletContext sc) {
    super(sc);
  }

  @Override
  protected void configureShiroWeb() {
    // try {
    // bindRealm().toConstructor(IniRealm.class.getConstructor(Ini.class));
    bindRealm().toProvider(JwJpaUserRealmProvider.class);
    // bindRealm().toProvider(LocalUserRealmProvider.class);
    // } catch (NoSuchMethodException e) {
    // addError(e);
    // }
    // addFilterChain("/opendoor*", AUTHC_BASIC, config(ROLES, "president"));
    // addFilterChain("/public/**", ANON);
    // addFilterChain("/stuff/allowed/**", AUTHC_BASIC, config(ROLES, "president"));
    // addFilterChain("/stuff/forbidden/**", AUTHC_BASIC, config(PERMS, "no"));
    // addFilterChain("/stuff/form/**", AUTHC, config(PERMS, "no"));

    // /login.jsp = authc
    // /logout = logout
    // /account/** = authc
    // /remoting/** = authc, roles[b2bClient], perms["remote:invoke:lan,wan"]
  }
}
