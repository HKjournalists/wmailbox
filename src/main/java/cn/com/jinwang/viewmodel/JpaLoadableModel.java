package cn.com.jinwang.viewmodel;

import org.apache.wicket.model.LoadableDetachableModel;

import com.google.common.base.Optional;

import cn.com.jinwang.domain.BaseDomain;

public class JpaLoadableModel<T extends BaseDomain<T>> extends LoadableDetachableModel<T> {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Class<T> entityClass;

  private Long identifier;

  @SuppressWarnings("unchecked")
  public JpaLoadableModel(T entity) {
    this.identifier = entity.getId();
    this.entityClass = ((Class<T>) entity.getClass());
    setObject(entity);
  }

  /**
   * when create new entity,you will find no record from back end.
   * so create one.
   */
  @Override
  protected T load() {
    try {
      Optional<T> entityOp = entityClass.newInstance().findById(this.identifier);
      if (entityOp.isPresent()) {
        return entityOp.get();
      } else {
        return entityClass.newInstance();
      }
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onDetach() {
    super.onDetach();
    T entity = getObject();
    if (entity == null) return;
    this.identifier = entity.getId();
  }
}
