package cn.com.jinwang.security;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.AbstractFactory;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

public class JwWebSecurityManagerFactory extends AbstractFactory<SecurityManager>{

  @Override
  protected SecurityManager createInstance() {
    return new DefaultWebSecurityManager(new JpaUserRealmFactory().getInstance());
  }
}
