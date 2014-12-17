package cn.com.jinwang.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import cn.com.jinwang.interf.HasLongId;
import cn.com.jinwang.interf.HasToJson;
import cn.com.jinwang.jpql.SortBy;
import cn.com.jinwang.repository.GenericRepository;

import com.google.common.base.Optional;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

@MappedSuperclass
public abstract class BaseDomain<T extends HasLongId> implements Serializable, HasLongId, HasToJson, GenericRepository<T, Long> {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "appSeqM3958")
  @Column(name = "id")
  @Expose
  @TableGenerator(name = "appSeqM3958", table = "APP_SEQ_STORE", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "APP_M3958.APP_SHARED_PK", valueColumnName = "APP_SEQ_VALUE")
  private long id;

  @Version
  @Column(name = "version")
  private Integer version;

  public String stringfyDomains(List<T> domains) {
    StringBuilder sb = new StringBuilder();
    for (T a : domains) {
      sb.append(a.getId());
      sb.append(",");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String toJson(String jsonv) {
    JsonObject jo = null;
    if (jsonv == null || jsonv.isEmpty()) {
      jo = toJsonObject();
    } else {
      jo = toJsonObject(jsonv);
    }
    return jo.toString();
  }

  public abstract JsonObject toJsonObject(String jsonv);

  public JsonObject toJsonObject() {
    return null;
  }

  public abstract void save();

  
  @Override
  public Class<T> getEntityClass() {
    return null;
  }

  @Override
  public Optional<T> findById(Long id) {
    return null;
  }

  @Override
  public List<T> findByIds(String ids) {
    return null;
  }

  @Override
  public List<T> findByIds(Long[] ids) {
    return null;
  }

  @Override
  public List<T> findByIds(List<Long> ids) {
    return null;
  }

  @Override
  public Optional<T> findById(Optional<Long> idOp) {
    return null;
  }

  @Override
  public List<T> findAll() {
    return null;
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

  @Override
  public long countAll() {
    return 0;
  }

  @Override
  public int countByExample(T exampleInstance) {
    return 0;
  }

  @Override
  public T save(T entity) {
    return null;
  }

  @Override
  public T update(T entity) {
    return null;
  }

  @Override
  public void delete(T entity) {
  }

  @Override
  public void deleteById(Long id) {
  }

  @Override
  public void deleteById(Optional<Long> id) {
  }

  @Override
  public Long countBySql(String sqlstr, long idpara) {
    return null;
  }

  @Override
  public List<T> findAll(int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAll(Optional<String> qstr) {
    return 0;
  }

  @Override
  public List<T> findAllMine(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
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
  public long countAllMineAndOthers(LocalUser user) {
    return 0;
  }

  @Override
  public List<T> findAllMine(LocalUser user) {
    return null;
  }

  @Override
  public long countAllMine(LocalUser user) {
    return 0;
  }

  @Override
  public List<T> findAllOthers(LocalUser user, int startRow, int endRow,
      Optional<SortBy> sortBy, Optional<String> qstr) {
    return null;
  }

  @Override
  public List<T> findAllOthers(LocalUser user) {
    return null;
  }

  @Override
  public List<T> findAllGroupOthers(LocalUser user) {
    return null;
  }

  @Override
  public long countAllOthers(LocalUser user, Optional<String> qstr) {
    return 0;
  }

  @Override
  public long countAllOthers(LocalUser user) {
    return 0;
  }

  @Override
  public Optional<T> findOneByOneField(String fieldName, String fieldValue) {
    return null;
  }

  @Override
  public List<T> findAllByOneField(String fieldName, String fieldValue) {
    return null;
  }

  @Override
  public List<T> findAllByLikeOneField(String fieldName, String fieldValue) {
    return null;
  }

  @Override
  public List<T> findAllByOneField(String fieldName, Long fieldValue) {
    return null;
  }

  @Override
  public List<T> amIInSharedUsers(LocalUser user) {
    return null;
  }

  @Override
  public void deleteAll() {
    
  }


}
