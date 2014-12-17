package cn.com.jinwang.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.initializer.EntityManagerFactoryHolder;
import cn.com.jinwang.jpql.SortBy;

import com.google.common.base.Optional;

public class MixDomainPermissionJpaRepository
    extends GenericJpaRepository<MixDomainPermission, Long>
    implements
      MixDomainPermissionRepository {

  private static final MixDomainPermissionJpaRepository INSTANCE =
      new MixDomainPermissionJpaRepository();

  public static MixDomainPermissionJpaRepository getInstance() {
    return INSTANCE;
  }

  private MixDomainPermissionJpaRepository() {
    super(MixDomainPermission.class);
  }

  private String getWhereString(String qstr) {
    String qs = "'%" + qstr + "%'";
    return new StringBuilder("(p.simpleName like ").append(qs).append(" or p.targets like ")
        .append(qs).append(" or p.actions like ").append(qs).append(")").toString();
  }

  @Override
  public List<MixDomainPermission> findAll(int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    if (qstr.isPresent()) {
      return findAllWithQstr(startRow, endRow, sortBy, getWhereString(qstr.get()));
    } else {
      return findAll(startRow, endRow, sortBy);
    }
  }

  @Override
  public long countAll(Optional<String> qstr) {
    if (qstr.isPresent()) {
      return countAllWithQstr(getWhereString(qstr.get()));
    } else {
      return countAll();
    }
  }

  @Override
  public Optional<MixDomainPermission> byAllFields(String simpleName, String actions, String targets) {
    TypedQuery<MixDomainPermission> q;
    MixDomainPermission mp = null;
    try {
      q =
          EntityManagerFactoryHolder.emf.createEntityManager().createNamedQuery(
              MixDomainPermission.NamedQueryNames.BY_ALL_FIELDS, MixDomainPermission.class);
      q.setParameter("simpleName", simpleName);
      q.setParameter("actions", actions);
      q.setParameter("targets", targets);
      mp = q.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (mp == null) {
      return Optional.absent();
    } else {
      return Optional.of(mp);
    }
  }

  @Override
  public List<MixDomainPermission> findAllMine(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllMine(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public List<MixDomainPermission> findAllOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public List<MixDomainPermission> findAllMineAndOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllMineAndOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public SortBy getDefaultSortBy() {
    return new SortBy("simpleName");
  }

  @Override
  public List<MixDomainPermission> myPermissions(int startRow, int endRow, LocalUser user,
      String qstr) {
    String qs = "select distinct p from LocalUser as u, in(u.permissions) as p where u = :user";
    if (qstr != null && !qstr.trim().isEmpty()) {
      qs = qs + " and " + getWhereString(qstr);
    }
    TypedQuery<MixDomainPermission> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs,
            MixDomainPermission.class);
    tq.setParameter("user", user);
    tq.setFirstResult(startRow);
    tq.setMaxResults(endRow - startRow);
    return tq.getResultList();
  }

  @Override
  public long countMyPermissions(LocalUser user, String qstr) {
    String qs =
        "select count(distinct p) from LocalUser as u, in(u.permissions) as p where u = :user";
    if (qstr != null && !qstr.trim().isEmpty()) {
      qs = qs + " and " + getWhereString(qstr);
    }
    TypedQuery<Long> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, Long.class);
    tq.setParameter("user", user);
    return tq.getSingleResult();
  }

  @Override
  public List<MixDomainPermission> myPermissionsOnTarget(LocalUser user, String simpleName,
      String target) {
    String qs =
        "select p from LocalUser as u, in(u.permissions) as p where u = :user and p.simpleName = :simpleName and p.targets = :target";
    TypedQuery<MixDomainPermission> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs,
            MixDomainPermission.class);
    tq.setParameter("user", user);
    tq.setParameter("simpleName", simpleName);
    tq.setParameter("target", target);
    return tq.getResultList();
  }

  @Override
  public List<MixDomainPermission> groupsPermissions(int startRow, int endRow, UserGroup group) {
    String qs = "select distinct p from UserGroup as g, in(g.permissions) as p where g = :group";
    TypedQuery<MixDomainPermission> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs,
            MixDomainPermission.class);
    tq.setParameter("group", group);
    tq.setFirstResult(startRow);
    tq.setMaxResults(endRow - startRow);
    return tq.getResultList();
  }

  @Override
  public long countGroupsPermissions(UserGroup group) {
    String qs =
        "select count(distinct p) from UserGroup as g, in(g.permissions) as p where g = :group";
    TypedQuery<Long> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs, Long.class);
    tq.setParameter("group", group);
    return tq.getSingleResult();
  }

  @Override
  public List<MixDomainPermission> groupsPermissionsOnTarget(UserGroup group, String simpleName,
      String target) {
    String qs =
        "select p from UserGroup as g, in(g.permissions) as p where g = :group and p.simpleName = :simpleName and p.targets = :target";
    TypedQuery<MixDomainPermission> tq =
        EntityManagerFactoryHolder.emf.createEntityManager().createQuery(qs,
            MixDomainPermission.class);
    tq.setParameter("group", group);
    tq.setParameter("simpleName", simpleName);
    tq.setParameter("target", target);
    return tq.getResultList();
  }

}
