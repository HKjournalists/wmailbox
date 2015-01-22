package cn.com.jinwang.viewmodel;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.eclipse.persistence.internal.queries.EntityFetchGroup;

import sun.misc.Sort;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.factory.RepositoryFactoryHolder;
import cn.com.jinwang.jpql.SortBy;

public class LocalUserDataProvider extends JpaDataProvider<LocalUser> {


  private static final long serialVersionUID = 1L;

  public LocalUserDataProvider() {
    super();
  }

  public LocalUserDataProvider(Model<String> filterModel) {
    super(filterModel);
  }

  @Override
  public Iterator<? extends LocalUser> iterator(long first, long count) {
    Optional<SortBy> osb = Optional.absent();
    if (getSort() != null) {
      SortBy sb = new SortBy(getSort().getProperty(), getSort().isAscending());
      osb = Optional.of(sb);
    }
    return RepositoryFactoryHolder.getLocalUserRepository()
        .findAll((int) first, (int) count, osb, getQueryStr()).iterator();
  }

  @Override
  public long size() {
    return RepositoryFactoryHolder.getLocalUserRepository().countAll(getQueryStr());
  }
  
  private Optional<String> getQueryStr() {
    Optional<String> oqs = Optional.absent();
    if (getFilterModel().getObject() != null && !getFilterModel().getObject().isEmpty()) {
      oqs = Optional.of(getFilterModel().getObject());
    }
    return oqs;
  }

  @Override
  public IModel<LocalUser> model(LocalUser object) {
    return new JpaLoadableModel<LocalUser>(object);
  }

  // @Override
  // public Iterator<JpaLoadableModel<LocalUser>> iterator(long first, long count) {
  // List<LocalUser> lus = RepositoryFactoryHolder.getLocalUserRepository().findAll(first, count);
  // List<JpaLoadableModel<LocalUser>> lums = Lists.newArrayList();
  // return lums.iterator();
  // }

  // @Override
  // public long size() {
  // return RepositoryFactoryHolder.getLocalUserRepository().countAll();
  // }
  //
  // @Override
  // public IModel<JpaLoadableModel<LocalUser>> model(JpaLoadableModel<LocalUser> object) {
  // return Model.of(object);
  // }
  //
  // @Override
  // public Iterator<? extends JpaLoadableModel<LocalUser>> iterator(long first, long count) {
  // List<LocalUser> lus = RepositoryFactoryHolder.getLocalUserRepository().findAll(first, count);
  // List<JpaLoadableModel<LocalUser>> lums = Lists.newArrayList();
  // return lums.iterator();
  // }



}
