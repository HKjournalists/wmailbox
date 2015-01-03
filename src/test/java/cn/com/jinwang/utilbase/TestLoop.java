package cn.com.jinwang.utilbase;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import cn.com.jinwang.learning.inherit.MyPagingNavigator;

public class TestLoop {
  private WicketTester tester;

  @Before
  public void setUp() {
    // none arguments constructor will create mock webapplication.
    tester = new MyWicketTester();
  }

//  @Test
//  public void testMyloop() {
//    MyLoop myloop = new MyLoop("myloop", 5);
//    tester.startComponentInPage(myloop);
//    String componentMarkup = ComponentRenderer.renderComponent(myloop).toString();
//    System.out.println(componentMarkup);
//  }

  @Test
  public void testPagination() {
    MyPagingNavigator pn = new MyPagingNavigator("navigation", new IPageable() {

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
    tester.startComponentInPage(pn);
    String componentMarkup = ComponentRenderer.renderComponent(pn).toString();

    System.out.println(componentMarkup);
  }

  public static class MyWicketTester extends WicketTester {

    @Override
    protected String createPageMarkup(String componentId) {
      return "<html><head></head><body> <ul  class=\"pure-paginator\" wicket:id=\"" + componentId + "\">" +
    "<li><a  class=\"pure-button\" wicket:id=\"pageLink\" href=\"SearchCDPage.html\"> <span wicket:id=\"pageNumber\">1</span></a></li></ul></body></html>";
    }
  }

  public static class MyLoop extends Loop {

    public MyLoop(String id, int iterations) {
      super(id, iterations);
    }

    @Override
    protected void populateItem(LoopItem item) {
      item.add(AttributeModifier.append("style", "color:red"));
    }
  }
}
