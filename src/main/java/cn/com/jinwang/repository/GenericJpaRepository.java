package cn.com.jinwang.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cn.com.jinwang.domain.BaseDomain;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.jpql.SortBy;
import cn.com.jinwang.ptn.ModelSaverFilters;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

/**
 * 
 * @author jianglibo@gmail.com
 * 
 *         this class is used in guice environment.
 * 
 */
public abstract class GenericJpaRepository<T extends BaseDomain<T>, ID>
    implements
      GenericRepository<T, Long> {

  // ~ Instance fields
  // --------------------------------------------------------

  private/* final */Class<T> persistentClass;

  private SortBy defaultSortBy = new SortBy("-id");

  private Splitter commaSplitter = Splitter.on(",").omitEmptyStrings();

  @Inject
  private Provider<EntityManager> emProvider;

  public GenericJpaRepository(Class<T> persistentClass) {
    super();
    this.persistentClass = persistentClass;
  }

  protected SortBy getSortByOrDefault(Optional<SortBy> sortBy) {
    if (sortBy.isPresent()) {
      return sortBy.get();
    } else {
      if (getDefaultSortBy() == null) {
        return defaultSortBy;
      } else {
        return getDefaultSortBy();
      }
    }
  }

  @Override
  public Class<T> getEntityClass() {
    return this.persistentClass;
  }

  @Override
  public Optional<T> findById(Long id) {
    final T result = emProvider.get().find(persistentClass, id);
    return Optional.fromNullable(result);
  }

  @Override
  public Optional<T> findById(Optional<Long> idOp) {
    if (idOp.isPresent()) {
      final T result = emProvider.get().find(persistentClass, idOp.get());
      return Optional.fromNullable(result);
    } else {
      return Optional.absent();
    }
  }

  @Override
  public List<T> findAll() {
    CriteriaBuilder cb = emProvider.get().getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(this.persistentClass);
    Root<T> entities = cq.from(this.persistentClass);
    cq.select(entities);
    TypedQuery<T> q = emProvider.get().createQuery(cq);
    return q.getResultList();
  }

  @Override
  public List<T> findByExample(T exampleInstance) {
    return null;
  }

  @Override
  public List<T> findByNamedQuery(String queryName, Object... params) {

    return null;
  }

  @Override
  public List<T> findByNamedQueryAndNamedParams(String queryName,
      Map<String, ? extends Object> params) {

    return null;
  }

  public long countAll() {
    String cn = persistentClass.getSimpleName();
    String qs = "SELECT count(o) from %s as o";
    qs = String.format(qs, cn);
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    return q.getSingleResult();
  }
  
  @Override
  public List<T> findAll(long first, long count) {
    String cn = persistentClass.getSimpleName();
    String jpql = "SELECT o FROM %s as o";
    jpql = String.format(jpql, cn);
    TypedQuery<T> q = emProvider.get().createQuery(jpql, getEntityClass());
    q.setFirstResult((int) first);
    q.setMaxResults((int) count);
    return q.getResultList();
  }

  @Override
  public int countByExample(T exampleInstance) {

    return 0;
  }

  @Override
  @Transactional
  public T save(T entity) {
    ModelSaverFilters.filter(entity);
    if (entity.getId() == 0) {
      emProvider.get().persist(entity);
    } else {
      entity = emProvider.get().merge(entity);
    }
    return entity;
  }

  @Override
  @Transactional
  public T update(T entity) {
    ModelSaverFilters.filter(entity);
    return emProvider.get().merge(entity);
  }

  @Override
  @Transactional
  public void delete(T entity) {
    emProvider.get().remove(entity);
  }

  @Override
  public void deleteById(Long id) {
    T entity = emProvider.get().find(getEntityClass(), id);
    delete(entity);
  }

  @Override
  public void deleteById(Optional<Long> idOp) {
    if (idOp.isPresent()) {
      deleteById(idOp.get());
    }
  }

  @Override
  public Long countBySql(String sqlstr, long idpara) {
    // Connection conn = null;
    // PreparedStatement stmt = null;
    // try {
    // conn = sqlds.getConnection();
    // stmt = conn.prepareStatement(sqlstr);
    // stmt.setLong(1, idpara);
    // ResultSet rs = stmt.executeQuery();
    // while (rs.next()) {
    // return rs.getLong(1);
    // }
    // rs.close();
    // stmt.close();
    // conn.close();
    // } catch (SQLException se) {
    // se.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // if (stmt != null)
    // stmt.close();
    // } catch (SQLException se2) {
    // }
    // try {
    // if (conn != null)
    // conn.close();
    // } catch (SQLException se) {
    // se.printStackTrace();
    // }
    // }
    return 0l;
  }

  public List<T> findAll(int startRow, int endRow, Optional<SortBy> sortBy) {
    String qs = "SELECT p from " + getEntityClass().getSimpleName() + " as p order by p.%s %s";
    SortBy sb = getSortByOrDefault(sortBy);
    qs = String.format(qs, sb.getField(), sb.getDirection());
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
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

  public List<T> findAllTopMine(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy) {
    SortBy sb = getSortByOrDefault(sortBy);
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where p.parent is NULL and p.creator = :creator order by p." + sb.getField()
            + " " + sb.getDirection();
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("creator", user);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public List<T> findAllTopOthers(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy) {
    SortBy sb = getSortByOrDefault(sortBy);
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where p.parent is NULL and :user member of p.sharedUsers order by p."
            + sb.getField() + " " + sb.getDirection();
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("user", user);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public long countAllTopMine(LocalUser user) {
    String qs =
        "SELECT count(distinct p) from " + getEntityClass().getSimpleName()
            + " as p where p.parent is NULL and p.creator = :creator";
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    q.setParameter("creator", user);
    return q.getSingleResult();
  }

  public long countAllTopOthers(LocalUser user) {
    String qs =
        "SELECT count(distinct p) from " + getEntityClass().getSimpleName()
            + " as p where p.parent is NULL and :user member of p.sharedUsers";
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    q.setParameter("user", user);
    return q.getSingleResult();
  }

  public List<T> findAllMine(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy) {
    SortBy sb = getSortByOrDefault(sortBy);
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where p.creator = :creator order by p." + sb.getField() + " "
            + sb.getDirection();
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("creator", user);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public List<T> findAllMine(LocalUser user) {
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where p.creator = :creator order by p.createdAt DESC";
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("creator", user);
    return q.getResultList();
  }

  public List<T> findAllOthers(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy) {
    SortBy sb = getSortByOrDefault(sortBy);
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where :user member of p.sharedUsers order by p." + sb.getField() + " "
            + sb.getDirection();
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("user", user);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public List<T> findAllOthers(LocalUser user) {
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where :user member of p.sharedUsers";
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("user", user);
    return q.getResultList();
  }

  public List<T> findAllGroupOthers(LocalUser user) {
    List<Long> ids = Lists.newArrayList();
    for (UserGroup ug : user.getGroups()) {
      ids.add(ug.getId());
    }
    if (ids.size() == 0) return Lists.newArrayList();
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p,in(p.sharedGroups) g where g.id in :ids";
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("ids", ids);
    return q.getResultList();
  }

  public List<T> descendant(String ascendantId) {
    TypedQuery<T> q =
        emProvider.get().createQuery(
            "SELECT distinct v from " + getEntityClass().getSimpleName()
                + " as v where v.parentIds like :parentIds order by v.parentIds asc",
            getEntityClass());
    String likestr = "%" + ascendantId + ",%";
    q.setParameter("parentIds", likestr);
    return q.getResultList();
  }

  @Override
  public long countAllMine(LocalUser user) {
    String qs =
        "SELECT count(distinct p) from " + getEntityClass().getSimpleName()
            + " as p where p.creator = :creator";
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    q.setParameter("creator", user);
    return q.getSingleResult();
  }

  @Override
  public long countAllMineAndOthers(LocalUser user) {
    Query query =
        emProvider
            .get()
            .createNativeQuery(
                "SELECT COUNT(DISTINCT(t0.id)) FROM WEB_SITE t0, WEB_SITE_LOCAL_USER t2, LOCAL_USER t1 WHERE (((t0.creator_id = ?)) OR ((t2.WebSite_id = t0.id) AND ((t1.id = t2.sharedUsers_id) AND t1.id = ?)))");
    query.setParameter(1, user.getId());
    query.setParameter(2, user.getId());
    return (Long) query.getSingleResult();
  }

  @Override
  public long countAllOthers(LocalUser user) {
    String qs =
        "SELECT count(distinct p) from " + getEntityClass().getSimpleName()
            + " as p where :user member of p.sharedUsers";
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    q.setParameter("user", user);
    return q.getSingleResult();
  }

  public List<T> children(T parent, int startRow, int endRow, Optional<SortBy> sortBy) {
    SortBy sb = getSortByOrDefault(sortBy);
    String qs =
        "SELECT p from " + getEntityClass().getSimpleName()
            + " as p where p.parent = :parent order by p." + sb.getField() + " "
            + sb.getDirection();
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("parent", parent);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public long countChildren(T parent) {
    String qs =
        "SELECT count(distinct p) from " + getEntityClass().getSimpleName()
            + " as p where p.parent = :parent";
    TypedQuery<Long> q = emProvider.get().createQuery(qs, Long.class);
    q.setParameter("parent", parent);
    return q.getSingleResult();
  }

  public List<T> findAllWithQstr(int startRow, int endRow, Optional<SortBy> sortBy,
      String whereLikeString) {
    SortBy sb = getSortByOrDefault(sortBy);
    StringBuilder sbd = new StringBuilder("SELECT p from ");
    sbd.append(getEntityClass().getSimpleName());
    sbd.append(" as p where ");
    sbd.append(whereLikeString);
    sbd.append(" order by p.");
    sbd.append(sb.getField());
    sbd.append(" ");
    sbd.append(sb.getDirection());
    TypedQuery<T> q = emProvider.get().createQuery(sbd.toString(), getEntityClass());
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public long countAllWithQstr(String whereLikeString) {
    StringBuilder sbd = new StringBuilder("SELECT count(distinct p) from ");
    sbd.append(getEntityClass().getSimpleName());
    sbd.append(" as p where ");
    sbd.append(whereLikeString);
    TypedQuery<Long> q = emProvider.get().createQuery(sbd.toString(), Long.class);
    return q.getSingleResult();
  }

  public List<T> findAllMineWithQstr(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, String whereLikeString) {
    SortBy sb = getSortByOrDefault(sortBy);
    StringBuilder sbd = new StringBuilder("SELECT p from ");
    sbd.append(getEntityClass().getSimpleName());
    sbd.append(" as p where p.creator = :creator and ");
    sbd.append(whereLikeString);
    sbd.append(" order by p.");
    sbd.append(sb.getField());
    sbd.append(" ");
    sbd.append(sb.getDirection());
    TypedQuery<T> q = emProvider.get().createQuery(sbd.toString(), getEntityClass());
    q.setParameter("creator", user);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public long countAllMineWithQstr(LocalUser user, String whereLikeString) {
    StringBuilder sbd = new StringBuilder("SELECT count(p) from ");
    sbd.append(getEntityClass().getSimpleName());
    sbd.append(" as p where p.creator = :creator and ");
    sbd.append(whereLikeString);
    TypedQuery<Long> q = emProvider.get().createQuery(sbd.toString(), Long.class);
    q.setParameter("creator", user);
    return q.getSingleResult();
  }

  public List<T> findAllOthersWithQstr(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, String whereLikeString) {
    SortBy sb = getSortByOrDefault(sortBy);
    StringBuilder sbd = new StringBuilder("SELECT p from ");
    sbd.append(getEntityClass().getSimpleName());
    sbd.append(" as p where :user member of p.sharedUsers and ");
    sbd.append(whereLikeString);
    sbd.append(" order by p.");
    sbd.append(sb.getField());
    sbd.append(" ");
    sbd.append(sb.getDirection());
    TypedQuery<T> q = emProvider.get().createQuery(sbd.toString(), getEntityClass());
    q.setParameter("user", user);
    q.setFirstResult(startRow);
    q.setMaxResults(endRow - startRow);
    return q.getResultList();
  }

  public long countAllOthersWithQstr(LocalUser user, String whereLikeString) {
    StringBuilder sbd = new StringBuilder("SELECT count(p) from ");
    sbd.append(getEntityClass().getSimpleName());
    sbd.append(" as p where :user member of p.sharedUsers and ");
    sbd.append(whereLikeString);
    TypedQuery<Long> q = emProvider.get().createQuery(sbd.toString(), Long.class);
    q.setParameter("user", user);
    return q.getSingleResult();
  }

  @Override
  public Optional<T> findOneByOneField(String fieldName, String fieldValue) {
    try {
      StringBuilder sb = new StringBuilder("SELECT p from ");
      sb.append(getEntityClass().getSimpleName());
      sb.append(" as p where  p.");
      sb.append(fieldName);
      sb.append(" = :");
      sb.append(fieldName);
      TypedQuery<T> q = emProvider.get().createQuery(sb.toString(), getEntityClass());
      q.setParameter(fieldName, fieldValue);
      T r = q.getSingleResult();
      if (r != null) return Optional.of(r);
    } catch (Exception e) {}
    return Optional.absent();
  }

  @Override
  public List<T> findAllByOneField(String fieldName, String fieldValue) {
    StringBuilder sb = new StringBuilder("SELECT distinct(p) from ");
    sb.append(getEntityClass().getSimpleName());
    sb.append(" as p where p.");
    sb.append(fieldName);
    sb.append(" = :");
    sb.append(fieldName);
    TypedQuery<T> q = emProvider.get().createQuery(sb.toString(), getEntityClass());
    q.setParameter(fieldName, fieldValue);
    return q.getResultList();
  }

  @Override
  public List<T> findAllByLikeOneField(String fieldName, String fieldValue) {
    StringBuilder sb = new StringBuilder("SELECT p from ");
    sb.append(getEntityClass().getSimpleName());
    sb.append(" as p where p.");
    sb.append(fieldName);
    sb.append(" like :");
    sb.append(fieldName);
    TypedQuery<T> q = emProvider.get().createQuery(sb.toString(), getEntityClass());
    q.setParameter(fieldName, fieldValue);
    return q.getResultList();
  }

  @Override
  public List<T> findAllByOneField(String fieldName, Long fieldValue) {
    StringBuilder sb = new StringBuilder("SELECT p from ");
    sb.append(getEntityClass().getSimpleName());
    sb.append(" as p where p.");
    sb.append(fieldName);
    sb.append(" = :");
    sb.append(fieldName);
    TypedQuery<T> q = emProvider.get().createQuery(sb.toString(), getEntityClass());
    q.setParameter(fieldName, fieldValue);
    return q.getResultList();
  }

  public List<T> findByIds(String idstr) {
    List<T> domains = Lists.newArrayList();
    if (idstr == null || idstr.isEmpty()) return domains;
    Iterable<String> ids = commaSplitter.split(idstr);
    List<Long> longs = Lists.newArrayList();
    for (String id : ids) {
      longs.add(Long.valueOf(id));
    }
    return findByIds(longs);
  }

  public List<T> findByIds(List<Long> longs) {
    if (longs.size() == 0) return Lists.newArrayList();
    String qs = "SELECT p from " + getEntityClass().getSimpleName() + " as p where p.id in :ids";
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("ids", longs);
    return q.getResultList();
  }

  public List<T> findByIds(Long[] longs) {
    return findByIds(Lists.newArrayList(longs));
  }

  @Override
  public List<T> amIInSharedUsers(LocalUser user) {
    String qs =
        "SELECT w from " + getEntityClass().getSimpleName()
            + " as w where :user member of w.sharedUsers";
    TypedQuery<T> q = emProvider.get().createQuery(qs, getEntityClass());
    q.setParameter("user", user);
    return q.getResultList();
  }

  protected String getStringEq(JsonObject jo) {
    StringBuilder sb = new StringBuilder("p.");
    sb.append(jo.get("fieldName").getAsString());
    sb.append("='");
    sb.append(jo.get("value").getAsString());
    sb.append("'");
    return sb.toString();
  }

  public abstract SortBy getDefaultSortBy();

  @Override
  public List<T> findAllMine(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    return null;
  }

  @Override
  public List<T> findAllMineAndOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllMine(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public long countAllMineAndOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public List<T> findAllOthers(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAllOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  public String getLikeStr(String str, LikeType lt) {
    switch (lt) {
      case FULL:
        return "%" + str + "%";
      case PRE:
        return str + "%";
      case POST:
        return "%" + str;
    }
    return str;
  }

  public enum LikeType {
    FULL, PRE, POST
  }

  @Override
  @Transactional
  public void deleteAll() {
    String jql = String.format("DELETE FROM %s AS m", persistentClass.getSimpleName());
    emProvider.get().createQuery(jql).executeUpdate();
  }

}
