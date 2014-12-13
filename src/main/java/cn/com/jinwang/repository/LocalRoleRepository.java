package cn.com.jinwang.repository;

import cn.com.jinwang.domain.LocalRole;

import com.google.common.base.Optional;

public interface LocalRoleRepository extends GenericRepository<LocalRole, Long> {

  Optional<LocalRole> findByName(String name);

  Optional<LocalRole> findByName(Optional<String> nameOp);

}
