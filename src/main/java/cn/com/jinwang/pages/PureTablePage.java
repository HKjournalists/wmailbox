package cn.com.jinwang.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.cglib.core.Local;

import org.apache.wicket.Application;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import cn.com.jinwang.assets.pure.PureButtonMarkup;
import cn.com.jinwang.assets.pure.PureButtonStyle;
import cn.com.jinwang.assets.pure.PureCss;
import cn.com.jinwang.components.datable.CheckBoxColumn;
import cn.com.jinwang.components.datable.JwDataTable;
import cn.com.jinwang.components.pure.PureButtonGroup;
import cn.com.jinwang.components.pure.PureTable;
import cn.com.jinwang.components.pure.PureTable.JpaLocalUserProvider;
import cn.com.jinwang.domain.BaseDomain;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.interf.HasLongId;
import cn.com.jinwang.utilbase.UiSize;

public class PureTablePage extends WebPage {
  private static final long serialVersionUID = 1L;


  private Set<LocalUser> selected = new HashSet<LocalUser>();

  /**
   * NOTE To use the AjaxPagingNavigator, you have to put your ListView in a WebMarkupContainer,
   * otherwise it is not possible to update the contents of the listview using Ajax.
   * 
   * @param parameters
   */
  public PureTablePage(final PageParameters parameters) {
    super(parameters);
    // WebMarkupContainer wmc = new WebMarkupContainer("pure-table-webmarkupcontainer");
    //
    // PureTable pt = new PureTable("pure-table", Model.of(""));
    // pt.setOutputMarkupId(true);
    //
    // wmc.add(pt);
    //
    // add(wmc);
    add(new PureButtonGroup("btn-group", new PureButtonMarkup("a", "Hello Btn",
        new PureButtonStyle(UiSize.XSMALL, false, false))));

    Form<?> form = new Form("form") {
      @Override
      protected void onSubmit() {
        for (LocalUser contact : selected) {
          info("Selected " + contact.getEmail());
        }
      }
    };
    add(form);

    List<IColumn<LocalUser, String>> columns = new ArrayList<IColumn<LocalUser, String>>();

    columns
        .add(new PropertyColumn<LocalUser, String>(new Model<String>("email"), "email", "email"));
    columns.add(new PropertyColumn<LocalUser, String>(new Model<String>("mobile"), "mobile"));

    columns.add(new CheckBoxColumn<LocalUser, String>(Model.of("")) {

      private static final long serialVersionUID = 1L;

      @Override
      protected IModel<Boolean> newCheckBoxModel(final IModel<LocalUser> rowModel) {
        return new AbstractCheckBoxModel() {

          private static final long serialVersionUID = 1L;

          @Override
          public void unselect() {
            selected.remove(rowModel.getObject());
          }

          @Override
          public void select() {
            selected.add(rowModel.getObject());
          }

          @Override
          public boolean isSelected() {
            return selected.contains(rowModel.getObject());
          }

          @Override
          public void detach() {
            rowModel.detach();
          }
        };
      }
    });
    // columns.add(new DomainIdColumn(Model.of("ID")));
    DataTable<LocalUser, String> table =
        new JwDataTable<LocalUser, String>("datatable", columns, new JpaLocalUserProvider(), 10);

    form.add(table);

    add(new DebugBar("debug"));
    add(new FeedbackPanel("feedback"));
    add(new HeaderResponseContainer("js-container", "js-container-decorator"));
  }

  public static class DomainIdColumn extends AbstractColumn<LocalUser, String> {

    private static final long serialVersionUID = 1L;

    public DomainIdColumn(IModel<String> displayModel) {
      super(displayModel, null);
    }

    @Override
    public void populateItem(Item<ICellPopulator<LocalUser>> cellItem, String componentId,
        IModel<LocalUser> rowModel) {
      cellItem.add(new Label(componentId, Model.of(rowModel.getObject().getId())));
    }
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    Application app = Application.get();
    ResourceReference jqReference = app.getJavaScriptLibrarySettings().getJQueryReference();
    response.render(CssHeaderItem.forReference(new PureCss()));
    response.render(JavaScriptHeaderItem.forReference(jqReference));
  }
}
