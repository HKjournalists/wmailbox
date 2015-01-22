package cn.com.jinwang.viewmodel;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import cn.com.jinwang.domain.BaseDomain;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.factory.RepositoryFactoryHolder;

public abstract class JpaDataProvider<T extends BaseDomain<T>> extends SortableDataProvider<T, String> {

  private static final long serialVersionUID = 1L;
  
  private Model<String> filterModel;
  
  public JpaDataProvider() {
    setFilterModel(Model.of(""));
  }
  
  
  public JpaDataProvider(Model<String> filterModel) {
    setFilterModel(filterModel);
  }

  public Model<String> getFilterModel() {
    return filterModel;
  }

  public void setFilterModel(Model<String> filterModel) {
    this.filterModel = filterModel;
  }

}

//  @Override
//  public void detach() {
//
//  }
//
//  @Override
//  public Iterator<T> iterator(long first, long count) {
//    return RepositoryFactoryHolder.getLocalUserRepository().findAll(first, count).iterator();
//  }
//
//  @Override
//  public long size() {
//    return RepositoryFactoryHolder.getLocalUserRepository().countAll();
//  }
//
//
//  @Override
//  public IModel<T> model(T object) {
//    return Model.of(object);
//  }
//}
