package cn.com.jinwang.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.inject.persist.Transactional;

import cn.com.jinwang.domain.BaseTreeDomain;
import cn.com.jinwang.jpql.SortBy;

public abstract class GenericJpaTreeRepository<T extends BaseTreeDomain<T>, ID>
    extends
    GenericJpaRepository<T, Long> implements GenericTreeRepository<T, Long>{

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
  
  public List<T> findAllTop(int startRow, int endRow, Optional<SortBy> sortBy) {
    SortBy sb = getSortByOrDefault(sortBy);
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where p.parent is NULL order by p." + sb.getField() + " " + sb.getDirection();
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public long countAllTop() {
    String qs =
        "SELECT count(distinct p) from " + getEntityClass().getSimpleName()
            + " as p where p.parent is NULL";
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    return q.getSingleResult();
  }
  
  /**
   * TODO change old parent.
   */
  @Override
  @Transactional
  public void updateParent(T parent, T...childs) {
    List<T> children = parent.getChildren();
    for(T child : childs) {
      child.setParent(parent);
      save(child);
      children.add(child);
    }
    parent.setChildren(children);
    save(parent);
  }

}
