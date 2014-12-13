package cn.com.jinwang.repository;

import java.util.List;

import cn.com.jinwang.domain.LocalRole;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;

import com.google.common.base.Optional;

public interface LocalUserRepository extends GenericRepository<LocalUser, Long> {

  Optional<LocalUser> findByEmailOrMobile(String emailOrMobile);

  Optional<LocalUser> findByEmail(String email);

  List<LocalUser> byGroup(UserGroup group);

  List<LocalUser> byGroup(UserGroup group, int start, int end);

  Long countByGroup(UserGroup group);

  List<LocalUser> hasThisLocalRole(LocalRole localRole);

  Long countHasThisLocalRole(LocalRole localRole);

  List<LocalUser> hasThisLocalRole(LocalRole localRole, int start, int end);

  List<LocalUser> hasThisPermission(MixDomainPermission mp);

}
