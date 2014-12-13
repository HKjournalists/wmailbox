package cn.com.jinwang.repository;

import java.util.List;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;

import com.google.common.base.Optional;


public interface MixDomainPermissionRepository extends GenericRepository<MixDomainPermission, Long> {

  Optional<MixDomainPermission> byAllFields(String simpleName, String actions, String targets);

  List<MixDomainPermission> myPermissions(int startRow, int endRow, LocalUser user, String qstr);

  List<MixDomainPermission> myPermissionsOnTarget(LocalUser user, String simpleName, String target);

  List<MixDomainPermission> groupsPermissions(int startRow, int endRow, UserGroup group);

  List<MixDomainPermission> groupsPermissionsOnTarget(UserGroup group, String simpleName,
      String target);

  long countGroupsPermissions(UserGroup group);

  long countMyPermissions(LocalUser user, String qstr);

}
