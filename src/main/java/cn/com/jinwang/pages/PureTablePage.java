package cn.com.jinwang.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import cn.com.jinwang.assets.pure.PureButtonMarkup;
import cn.com.jinwang.assets.pure.PureButtonStyle;
import cn.com.jinwang.assets.pure.PureCss;
import cn.com.jinwang.components.base.PureButtonGroup;
import cn.com.jinwang.components.base.PureTable;
import cn.com.jinwang.components.base.PureTable.JpaLocalUserProvider;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.utilbase.UiSize;

public class PureTablePage extends WebPage {
  private static final long serialVersionUID = 1L;


  /**
   * NOTE To use the AjaxPagingNavigator, you have to put your ListView in a WebMarkupContainer,
   * otherwise it is not possible to update the contents of the listview using Ajax.
   * 
   * @param parameters
   */
  public PureTablePage(final PageParameters parameters) {
    super(parameters);
//    WebMarkupContainer wmc = new WebMarkupContainer("pure-table-webmarkupcontainer");
//    
//    PureTable pt = new PureTable("pure-table", Model.of(""));
//    pt.setOutputMarkupId(true);
//
//    wmc.add(pt);
//
//    add(wmc);
    add(new PureButtonGroup("btn-group", new PureButtonMarkup("a", "Hello Btn", new PureButtonStyle(UiSize.XSMALL, false, false))));
    
    
    List<IColumn<LocalUser, String>> columns = new ArrayList<IColumn<LocalUser, String>>();
    
    columns.add(new PropertyColumn<LocalUser, String>(new Model<String>("email"), "email", "email"));
    columns.add(new PropertyColumn<LocalUser, String>(new Model<String>("mobile"), "mobile"));
    
    DataTable<LocalUser, String> table = new DefaultDataTable<LocalUser, String>("datatable", columns, new JpaLocalUserProvider(), 10);
    
    add(table);

    add(new DebugBar("debug"));
    add(new HeaderResponseContainer("js-container", "js-container-decorator"));
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
