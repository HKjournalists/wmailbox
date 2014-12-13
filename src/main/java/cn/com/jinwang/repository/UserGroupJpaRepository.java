package cn.com.jinwang.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import cn.com.jinwang.domain.LocalRole;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.initializer.EntityManagerFactoryHolder;
import cn.com.jinwang.sql.SortBy;

import com.google.common.base.Optional;

public class UserGroupJpaRepository extends GenericJpaRepository<UserGroup, Long>
    implements
      UserGroupRepository {

  private static final UserGroupJpaRepository INSTANCE = new UserGroupJpaRepository();

  public static UserGroupJpaRepository getInstance() {
    return INSTANCE;
  }

  private UserGroupJpaRepository() {
    super(UserGroup.class);
  }

  @Override
  public long countAll(Optional<String> qstr) {
    if (qstr.isPresent()) {
      return countAllWithQstr(getLikeString(qstr.get()));
    } else {
      return countAll();
    }
  }

  @Override
  public long countAllMine(LocalUser user, Optional<String> qstr) {
    if (qstr.isPresent()) {
      return countAllMineWithQstr(user, getLikeString(qstr.get()));
    } else {
      return countAllMine(user);
    }
  }

  @Override
  public long countAllOthers(LocalUser user, Optional<String> qstr) {
    if (qstr.isPresent()) {
      return countAllOthersWithQstr(user, getLikeString(qstr.get()));
    } else {
      return countAllOthers(user);
    }
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
  public List<UserGroup> findAll(int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    if (qstr.isPresent()) {
      return findAllWithQstr(startRow, endRow, Optional.of(getDefaultSortBy()),
          getLikeString(qstr.get()));
    } else {
      return findAll(startRow, endRow, Optional.of(getDefaultSortBy()));
    }
  }

  private String getLikeString(String likeStr) {
    StringBuilder sb = new StringBuilder("p.name like ");
    sb.append("'%").append(likeStr).append("%'").append(" ");
    return sb.toString();
  }

  @Override
  public List<UserGroup> findAllMine(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    if (qstr.isPresent()) {
      return findAllMineWithQstr(user, startRow, endRow, Optional.of(getDefaultSortBy()),
          getLikeString(qstr.get()));
    } else {
      return findAllMine(user, startRow, endRow, Optional.of(getDefaultSortBy()));
    }
  }

  @Override
  public List<UserGroup> findAllMineAndOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public List<UserGroup> findAllOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    if (qstr.isPresent()) {
      return findAllOthersWithQstr(user, startRow, endRow, Optional.of(getDefaultSortBy()),
          getLikeString(qstr.get()));
    } else {
      return findAllOthers(user, startRow, endRow, Optional.of(getDefaultSortBy()));
    }
  }

  @Override
  public List<UserGroup> hasThisPermission(MixDomainPermission mp) {
    String qs = "select u from UserGroup as u where :mp member of u.permissions";
    TypedQuery<UserGroup> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, UserGroup.class);
    tq.setParameter("mp", mp);
    return tq.getResultList();
  }

  @Override
  public Optional<UserGroup> findByName(Optional<String> nameOp) {
    if (nameOp.isPresent()) {
      try {
        String qs = "select u from UserGroup as u where u.name = :name";
        TypedQuery<UserGroup> tq =
            EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, UserGroup.class);
        tq.setParameter("name", nameOp.get());
        return Optional.of(tq.getSingleResult());
      } catch (Exception e) {
        return Optional.<UserGroup>absent();
      }
    } else {
      return Optional.<UserGroup>absent();
    }
  }

  @Override
  public Optional<UserGroup> findByName(String name) {
    return findByName(Optional.fromNullable(name));
  }

  @Override
  public List<UserGroup> hasThisLocalRole(LocalRole localRole) {
    String qs = "select g from UserGroup as g where :localRole member of g.roles";
    TypedQuery<UserGroup> q =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, UserGroup.class);
    q.setParameter("localRole", localRole);
    return q.getResultList();
  }

  @Override
  public Long countHasThisLocalRole(LocalRole localRole) {
    String qs = "select count(distinct g) from UserGroup as g where :localRole member of g.roles";
    TypedQuery<Long> q =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, Long.class);
    q.setParameter("localRole", localRole);
    return q.getSingleResult();
  }

  @Override
  public List<UserGroup> hasThisLocalRole(LocalRole localRole, int start, int end) {
    String qs = "select g from UserGroup as g where :localRole member of g.roles";
    TypedQuery<UserGroup> q =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, UserGroup.class);
    q.setParameter("localRole", localRole);
    q.setFirstResult(start);
    q.setMaxResults(end - start);
    return q.getResultList();
  }

}
