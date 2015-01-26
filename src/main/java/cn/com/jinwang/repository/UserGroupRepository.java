package cn.com.jinwang.repository;

import java.util.List;

import cn.com.jinwang.domain.LocalRole;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;

import com.google.common.base.Optional;

public interface UserGroupRepository extends GenericTreeRepository<UserGroup, Long> {

  List<UserGroup> hasThisPermission(MixDomainPermission mp);

  List<UserGroup> hasThisLocalRole(LocalRole localRole);

  Long countHasThisLocalRole(LocalRole localRole);

  List<UserGroup> hasThisLocalRole(LocalRole localRole, int start, int end);

  Optional<UserGroup> findByName(Optional<String> nameOp);

  Optional<UserGroup> findByName(String name);

}
