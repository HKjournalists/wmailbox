package cn.com.jinwang.viewmodel;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;

import com.google.common.base.Optional;

import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.factory.RepositoryFactoryHolder;
import cn.com.jinwang.jpql.SortBy;

public class UserGroupProvider implements ITreeProvider<UserGroup> {

  private static final long serialVersionUID = 1L;

  @Override
  public void detach() {
  }

  @Override
  public Iterator<? extends UserGroup> getRoots() {
    List<UserGroup> ugs = RepositoryFactoryHolder.getUserGroupRepository().findAllTop(0, 10000, Optional.<SortBy>absent());
    return ugs.iterator();
  }

  @Override
  public boolean hasChildren(UserGroup node) {
    int size = node.getChildren().size(); 
    return size > 0;
  }

  @Override
  public Iterator<? extends UserGroup> getChildren(UserGroup node) {
    return node.getChildren().iterator();
  }

  @Override
  public IModel<UserGroup> model(UserGroup object) {
    return new JpaLoadableModel<UserGroup>(object); 
  }
}
