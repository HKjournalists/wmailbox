package cn.com.jinwang.pages;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import cn.com.jinwang.pages.BaseHomePage;
import cn.com.jinwang.pages.WicketApplicationPlain;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
  private WicketTester tester;

  @Before
  public void setUp() {
    tester = new WicketTester(new WicketApplicationPlain());
  }

  @Test
  public void homepageRendersSuccessfully() {
    // start and render the test page
    tester.startPage(BaseHomePage.class);

    // assert rendered page class
    tester.assertRenderedPage(BaseHomePage.class);
  }
}
