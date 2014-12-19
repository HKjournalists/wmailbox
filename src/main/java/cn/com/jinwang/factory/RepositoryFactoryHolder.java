package cn.com.jinwang.factory;

import cn.com.jinwang.guice.JwGuiceServletConfig;
import cn.com.jinwang.repository.LocalLoginLogRepository;
import cn.com.jinwang.repository.LocalRoleRepository;
import cn.com.jinwang.repository.LocalUserRepository;
import cn.com.jinwang.repository.MixDomainPermissionRepository;
import cn.com.jinwang.repository.UserGroupRepository;

public class RepositoryFactoryHolder {

  public static LocalUserRepository getLocalUserRepository() {
    return JwGuiceServletConfig.injector.getInstance(LocalUserRepository.class);
  }

  public static UserGroupRepository getUserGroupRepository() {
    return JwGuiceServletConfig.injector.getInstance(UserGroupRepository.class);
  }

  public static LocalRoleRepository getLocalRoleRepository() {
    return JwGuiceServletConfig.injector.getInstance(LocalRoleRepository.class);
  }

  public static MixDomainPermissionRepository getMixDomainPermissionRepository() {
    return JwGuiceServletConfig.injector.getInstance(MixDomainPermissionRepository.class);
  }
  
  public static LocalLoginLogRepository getLocalLoginLogRepository() {
    return JwGuiceServletConfig.injector.getInstance(LocalLoginLogRepository.class);
  }

}
