package cn.com.jinwang.learning.inherit;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PagingNavigatorPage extends WebPage {
  private static final long serialVersionUID = 1L;

  public PagingNavigatorPage(final PageParameters parameters) {
    super(parameters);
    
    MyPagingNavigator pn = new MyPagingNavigator("mynv", new IPageable() {

      private long curp;

      @Override
      public void setCurrentPage(long page) {
        this.curp = page;
      }

      @Override
      public long getPageCount() {
        return 10;
      }

      @Override
      public long getCurrentPage() {
        return curp;
      }
    });
    
    add(pn);

  }
}
