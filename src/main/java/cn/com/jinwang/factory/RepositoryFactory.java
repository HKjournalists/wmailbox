package cn.com.jinwang.factory;

import cn.com.jinwang.repository.LocalRoleJpaRepository;
import cn.com.jinwang.repository.LocalRoleRepository;
import cn.com.jinwang.repository.LocalUserJpaRepository;
import cn.com.jinwang.repository.LocalUserRepository;
import cn.com.jinwang.repository.MixDomainPermissionJpaRepository;
import cn.com.jinwang.repository.MixDomainPermissionRepository;
import cn.com.jinwang.repository.UserGroupJpaRepository;
import cn.com.jinwang.repository.UserGroupRepository;

public class RepositoryFactory {

  public static LocalUserRepository getLocalUserRepository() {
    return LocalUserJpaRepository.getInstance();
  }

  public static UserGroupRepository getUserGroupRepository() {
    return UserGroupJpaRepository.getInstance();
  }

  public static LocalRoleRepository getLocalRoleRepository() {
    return LocalRoleJpaRepository.getInstance();
  }

  public static MixDomainPermissionRepository getMixDomainPermissionRepository() {
    return MixDomainPermissionJpaRepository.getInstance();
  }

}
