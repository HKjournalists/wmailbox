package cn.com.jinwang.utilbase;

import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import cn.com.jinwang.assets.pure.PureButtonMarkup;
import cn.com.jinwang.components.base.PureButtonGroup;

public class TestPureButtonGroup {
  private WicketTester tester;

  @Before
  public void setUp() {
    tester = new WicketTester();
  }


  @Test
  public void testPagination() {
    PureButtonGroup pbg = new PureButtonGroup("pure-button-group", new PureButtonMarkup("a","Hello"));
    pbg.setRenderBodyOnly(true);
    tester.startComponentInPage(pbg);
    CharSequence cs = ComponentRenderer.renderComponent(pbg);
    String componentMarkup = cs.toString();

    System.out.println(componentMarkup);
  }

}
