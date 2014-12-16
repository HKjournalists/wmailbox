package cn.com.jinwang.security;

import org.apache.shiro.config.ResourceConfigurable;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.mgt.WebSecurityManager;

public class JwWebEnvironment extends DefaultWebEnvironment  implements ResourceConfigurable{
  
  
  public JwWebEnvironment() {
    WebSecurityManager securityManager = createWebSecurityManager();
    setWebSecurityManager(securityManager);
  }

  private WebSecurityManager createWebSecurityManager() {
    return (WebSecurityManager) new JwWebSecurityManagerFactory().getInstance();
  }

  @Override
  public void setConfigLocations(String locations) {
  }

  @Override
  public void setConfigLocations(String[] locations) {
  }

}
