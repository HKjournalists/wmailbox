package cn.com.jinwang.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.jpql.SortBy;

import com.google.common.base.Optional;

public interface GenericRepository<T, ID extends Serializable> {

  // ~ Methods
  // ----------------------------------------------------------------

  /**
   * Get the Class of the entity.
   * 
   * @return the class
   */
  Class<T> getEntityClass();

  /**
   * Find an entity by its primary key
   * 
   * @param id the primary key
   * @return the entity
   */
  Optional<T> findById(final ID id);

  List<T> findByIds(String ids);

  List<T> findByIds(Long[] ids);

  List<T> findByIds(List<Long> ids);

  // List<T> byReqInfo(RequestInfo reqInfo) throws AccessDenyException;
  //
  // List<T> byReqInfo(RequestInfo reqInfo, int defaultpp) throws
  // AccessDenyException;

  Optional<T> findById(final Optional<ID> idOp);

  /**
   * Load all entities.
   * 
   * @return the list of entities
   */
  List<T> findAll();

  /**
   * Find entities based on an example.
   * 
   * @param exampleInstance the example
   * @return the list of entities
   */
  List<T> findByExample(final T exampleInstance);

  /**
   * Find using a named query.
   * 
   * @param queryName the name of the query
   * @param params the query parameters
   * 
   * @return the list of entities
   */
  List<T> findByNamedQuery(final String queryName, Object... params);

  /**
   * Find using a named query.
   * 
   * @param queryName the name of the query
   * @param params the query parameters
   * 
   * @return the list of entities
   */
  List<T> findByNamedQueryAndNamedParams(final String queryName,
      final Map<String, ? extends Object> params);

  /**
   * Count all entities.
   * 
   * @return the number of entities
   */
  long countAll();

  /**
   * Count entities based on an example.
   * 
   * @param exampleInstance the search criteria
   * @return the number of entities
   */
  int countByExample(final T exampleInstance);

  /**
   * save an entity. This can be either a INSERT or UPDATE in the database.
   * 
   * @param entity the entity to save
   * 
   * @return the saved entity
   */
  T save(final T entity);

  T update(final T entity);

  /**
   * delete an entity from the database.
   * 
   * @param entity the entity to delete
   */
  void delete(final T entity);

  void deleteById(Long id);

  void deleteById(Optional<Long> id);

  Long countBySql(String sqlstr, long idpara);

  List<T> findAll(int startRow, int endRow, Optional<SortBy> sortBy, Optional<String> qstr);
  
  List<T> findAll(long first, long count);

  long countAll(Optional<String> qstr);

  List<T> findAllMine(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr);

  List<T> findAllMineAndOthers(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr);

  long countAllMine(LocalUser user, Optional<String> qstr);

  long countAllMineAndOthers(LocalUser user, Optional<String> qstr);

  long countAllMineAndOthers(LocalUser user);

  List<T> findAllMine(LocalUser user);

  long countAllMine(LocalUser user);

  List<T> findAllOthers(LocalUser user, int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr);

  List<T> findAllOthers(LocalUser user);

  List<T> findAllGroupOthers(LocalUser user);

  long countAllOthers(LocalUser user, Optional<String> qstr);

  long countAllOthers(LocalUser user);

  Optional<T> findOneByOneField(String fieldName, String fieldValue);

  List<T> findAllByOneField(String fieldName, String fieldValue);

  List<T> findAllByLikeOneField(String fieldName, String fieldValue);

  List<T> findAllByOneField(String fieldName, Long fieldValue);

  List<T> amIInSharedUsers(LocalUser user);

  void deleteAll();
}
