package cn.com.jinwang.repository;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import cn.com.jinwang.domain.BaseTreeDomain;

public abstract class GenericJpaTreeRepository<T extends BaseTreeDomain<T>, ID>
    extends
    GenericJpaRepository<T, Long> {

  public GenericJpaTreeRepository(Class<T> persistentClass) {
    super(persistentClass);
  }

  public List<T> breadcrumb(Long domainId) {
    T d = findById(domainId).get();
    String parentIds = d.getParentIds();
    return findByIds(parentIds);
  }

  public List<T> breadcrumb(Optional<Long> domainId) {
    if (domainId.isPresent()) {
      return breadcrumb(domainId.get());
    }
    return Lists.newArrayList();
  }

}
