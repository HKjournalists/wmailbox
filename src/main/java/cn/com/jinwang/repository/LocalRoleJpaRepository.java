package cn.com.jinwang.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import cn.com.jinwang.domain.LocalRole;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.guice.JwGuiceServletConfig;
import cn.com.jinwang.jpql.SortBy;

import com.google.common.base.Optional;
import com.google.inject.Singleton;

@Singleton
public class LocalRoleJpaRepository extends GenericJpaRepository<LocalRole, Long>
    implements
      LocalRoleRepository {

  public LocalRoleJpaRepository() {
    super(LocalRole.class);
  }

  @Override
  public List<LocalRole> findAll(int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    if (qstr.isPresent()) {

    } else {
      return findAll(startRow, endRow, sortBy);
    }
    return null;
  }

  @Override
  public long countAll(Optional<String> qstr) {
    if (qstr.isPresent()) {

    } else {
      return countAll();
    }
    return 0;
  }

  @Override
  public List<LocalRole> findAllMine(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    if (qstr.isPresent()) {

    } else {
      return findAllMine(user, startRow, endRow, sortBy);
    }
    return null;
  }

  @Override
  public long countAllMine(LocalUser user, Optional<String> qstr) {
    if (qstr.isPresent()) {

    } else {
      return countAllMine(user);
    }
    return 0;
  }

  @Override
  public List<LocalRole> findAllOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public List<LocalRole> findAllMineAndOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllMineAndOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public SortBy getDefaultSortBy() {
    return new SortBy("-createdAt");
  }

  @Override
  public Optional<LocalRole> findByName(String name) {
    return findByName(Optional.fromNullable(name));
  }

  @Override
  public Optional<LocalRole> findByName(Optional<String> nameOp) {
    if (nameOp.isPresent()) {
      try {
        String qs = "select r from LocalRole as r where r.name = :name";
        TypedQuery<LocalRole> tq =
            JwGuiceServletConfig.getEntityManager().createQuery(qs, LocalRole.class);
        tq.setParameter("name", nameOp.get());
        return Optional.of(tq.getSingleResult());
      } catch (Exception e) {
        return Optional.<LocalRole>absent();
      }
    } else {
      return Optional.<LocalRole>absent();
    }
  }
}
