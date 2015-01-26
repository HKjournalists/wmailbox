package cn.com.jinwang.pages;

import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.viewmodel.UserGroupExpansion;
import cn.com.jinwang.viewmodel.UserGroupProvider;

public class UserGroupTreePage extends WebPage {

  private static final long serialVersionUID = 1L;

  public UserGroupTreePage() {
    add(new DefaultNestedTree<UserGroup>("tree", new UserGroupProvider(), new UserGroupExpansionModel()) {

      private static final long serialVersionUID = 1L;

      /**
       * To use a custom component for the representation of a node's content we would override this
       * method.
       */
      @Override
      protected Component newContentComponent(String id, IModel<UserGroup> node) {
        return super.newContentComponent(id, node);
      }
    });
    
    add(new HeaderResponseContainer("js-container", "js-container-decorator"));
  }
  
  private class UserGroupExpansionModel extends AbstractReadOnlyModel<Set<UserGroup>>
  {
    private static final long serialVersionUID = 1L;

      @Override
      public Set<UserGroup> getObject()
      {
          return UserGroupExpansion.get();
      }
  }
  
  
  
}
