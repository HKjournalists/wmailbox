package cn.com.jinwang.components.base;

import java.util.Iterator;

import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.factory.RepositoryFactoryHolder;

public class PureTable extends Panel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PureTable(String id, IModel<?> model) {
    super(id, model);
    IDataProvider<LocalUser> dataProvider = new JpaLocalUserProvider();

    @SuppressWarnings("serial")
    DataView<LocalUser> dataView = new DataView<LocalUser>("rows", dataProvider) {
      @Override
      protected void populateItem(Item<LocalUser> item) {
        LocalUser lu = item.getModelObject();
        RepeatingView repeatingView = new RepeatingView("dataRow");
        
        AbstractItem aitem = new AbstractItem(repeatingView.newChildId());
        
        aitem.add(new CheckBox("ck"));
        
        repeatingView.add(aitem);
        repeatingView.add(new Label(repeatingView.newChildId(), Model.of(lu.getId())));
        repeatingView.add(new Label(repeatingView.newChildId(), Model.of(lu.getEmail())));
        repeatingView.add(new Label(repeatingView.newChildId(), Model.of(lu.getActivityState())));
        repeatingView.add(new Label(repeatingView.newChildId(), Model.of(lu.getMobile())));
        
        item.add(repeatingView);
      }
    };

    dataView.setItemsPerPage(10);

    add(dataView);
    add(new AjaxPagingNavigator("pagingNavigator", dataView));
  }


  @SuppressWarnings("serial")
  public static class JpaLocalUserProvider extends SortableDataProvider<LocalUser, String > {
    
    public JpaLocalUserProvider() {
      setSort("email",SortOrder.ASCENDING);
    }

    @Override
    public void detach() {

    }

    @Override
    public Iterator<LocalUser> iterator(long first, long count) {
      return RepositoryFactoryHolder.getLocalUserRepository().findAll(first, count).iterator();
    }

    @Override
    public long size() {
      return RepositoryFactoryHolder.getLocalUserRepository().countAll();
    }


    @Override
    public IModel<LocalUser> model(LocalUser object) {
      return Model.of(object);
    }
  }
}
