package cn.com.jinwang.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import cn.com.jinwang.domain.LocalRole;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.MixDomainPermission;
import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.guice.JwGuiceServletConfig;
import cn.com.jinwang.jpql.SortBy;

import com.google.common.base.Optional;

public class LocalUserJpaRepository extends GenericJpaRepository<LocalUser, Long>
    implements
      LocalUserRepository {

  private static final LocalUserJpaRepository INSTANCE = new LocalUserJpaRepository();

  public static LocalUserJpaRepository getInstance() {
    return INSTANCE;
  }

  private LocalUserJpaRepository() {
    super(LocalUser.class);
  }

  @Override
  public Optional<LocalUser> findByEmailOrMobile(String emailOrMobile) {
    try {
      TypedQuery<LocalUser> q;
      String jql;
      if (emailOrMobile.indexOf('@') == -1) {
        jql = "SELECT lu FROM LocalUser AS lu WHERE lu.mobile = :mobile";
        q = JwGuiceServletConfig.getEntityManager().createQuery(jql, LocalUser.class);
        q.setParameter("mobile", emailOrMobile);
      } else {
        jql = "SELECT lu FROM LocalUser AS lu WHERE lu.email = :email";
        q = JwGuiceServletConfig.getEntityManager().createQuery(jql, LocalUser.class);
        q.setParameter("email", emailOrMobile);
      }
      LocalUser user = q.getSingleResult();
      return Optional.of(user);
    } catch (NoResultException e) {
      return Optional.absent();
    }
  }

  @Override
  public Optional<LocalUser> findByEmail(String email) {
    try {
      String jql = "SELECT lu FROM LocalUser AS lu WHERE lu.email = :email";
      TypedQuery<LocalUser> q =
          JwGuiceServletConfig.getEntityManager().createQuery(jql, LocalUser.class);
      q.setParameter("email", email);
      LocalUser user = q.getSingleResult();
      return Optional.of(user);
    } catch (NoResultException e) {
      return Optional.absent();
    }
  }

  @Override
  public List<LocalUser> findAll(int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    if (qstr.isPresent()) {
      String wherestr = "p.nickname like '%s' or p.email like '%s' or p.mobile like '%s'";
      String qqsr = "%" + qstr.get() + "%";
      return findAllWithQstr(startRow, endRow, sortBy, String.format(wherestr, qqsr, qqsr, qqsr));
    } else {
      return findAll(startRow, endRow, sortBy);
    }
  }

  @Override
  public long countAll(Optional<String> qstr) {
    if (qstr.isPresent()) {
      String qqstr = "%" + qstr.get() + "%";
      String whereStr = "p.nickname like '%s' or p.email like '%s' or p.mobile like '%s'";
      return countAllWithQstr(String.format(whereStr, qqstr, qqstr, qqstr));
    } else {
      return countAll();
    }
  }

  @Override
  public List<LocalUser> findAllMine(LocalUser user, int startRow, int endRow,
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
  public List<LocalUser> findAllOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public List<LocalUser> findAllMineAndOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllMineAndOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public SortBy getDefaultSortBy() {
    return null;
  }

  @Override
  public List<LocalUser> hasThisPermission(MixDomainPermission mp) {
    String qs = "select u from LocalUser as u where :mp member of u.permissions";
    TypedQuery<LocalUser> tq =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, LocalUser.class);
    tq.setParameter("mp", mp);
    return tq.getResultList();
  }

  @Override
  public List<LocalUser> hasThisLocalRole(LocalRole localRole) {
    String qs = "select u from LocalUser as u where :localRole member of u.roles";
    TypedQuery<LocalUser> q =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, LocalUser.class);
    q.setParameter("localRole", localRole);
    return q.getResultList();
  }

  @Override
  public Long countHasThisLocalRole(LocalRole localRole) {
    String qs = "select count(distinct u) from LocalUser as u where :localRole member of u.roles";
    TypedQuery<Long> q =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, Long.class);
    q.setParameter("localRole", localRole);
    return q.getSingleResult();
  }

  @Override
  public List<LocalUser> hasThisLocalRole(LocalRole localRole, int start, int end) {
    String qs = "select u from LocalUser as u where :localRole member of u.roles";
    TypedQuery<LocalUser> q =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, LocalUser.class);
    q.setParameter("localRole", localRole);
    q.setFirstResult(start);
    q.setMaxResults(end - start);
    return q.getResultList();
  }

  @Override
  public List<LocalUser> byGroup(UserGroup group) {
    String qs = "select u from LocalUser as u where :group member of u.groups";
    TypedQuery<LocalUser> q =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, LocalUser.class);
    q.setParameter("group", group);
    return q.getResultList();
  }

  @Override
  public List<LocalUser> byGroup(UserGroup group, int start, int end) {
    String qs = "select u from LocalUser as u where :group member of u.groups";
    TypedQuery<LocalUser> q =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, LocalUser.class);
    q.setParameter("group", group);
    q.setFirstResult(start);
    q.setMaxResults(end - start);
    return q.getResultList();
  }

  @Override
  public Long countByGroup(UserGroup group) {
    String qs = "select count(distinct u ) from LocalUser as u where :group member of u.groups";
    TypedQuery<Long> q =
        JwGuiceServletConfig.getEntityManager().createQuery(qs, Long.class);

    q.setParameter("group", group);
    return q.getSingleResult();
  }
}
