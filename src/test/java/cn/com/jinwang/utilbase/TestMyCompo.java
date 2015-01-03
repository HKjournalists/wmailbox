package cn.com.jinwang.utilbase;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import cn.com.jinwang.learning.inherit.PagingNavigatorPage;
import cn.com.jinwang.pages.WicketApplicationPlain;
import cn.com.jinwang.reposotory.JpaTestBase;

public class TestMyCompo extends JpaTestBase{
  
  private WicketTester tester;

  @Before
  public void setUp() {
    // none arguments constructor will create mock webapplication.
    tester = new WicketTester(new WicketApplicationPlain());
  }
  
  @Test
  public void t() {
    tester.startPage(PagingNavigatorPage.class);
    String responseTxt = tester.getLastResponse().getDocument();
    System.out.println(responseTxt);
  }
}
