package cn.com.jinwang.utilbase;

import org.junit.Assert;
import org.junit.Test;

import cn.com.jinwang.assets.pure.PureButtonMarkup;
import cn.com.jinwang.assets.pure.PureButtonStyle;

public class TestPureButtonMarkup {

  @Test
  public void tDefault() {
    PureButtonMarkup pb = new PureButtonMarkup("button", "Hello");
    Assert.assertEquals("<button class=\"pure-button\">Hello</button>", pb.toString());
  }
  
  @Test
  public void tTagName() {
    PureButtonMarkup pb = new PureButtonMarkup("a", "Hello");
    Assert.assertEquals("<a class=\"pure-button\" href=\"#\">Hello</a>", pb.toString());
  }
  
  @Test
  public void tSize() {
    PureButtonMarkup pb = new PureButtonMarkup("a", "Hello", new PureButtonStyle(UiSize.XSMALL));
    
    Assert.assertEquals("<a class=\"pure-button button-xsmall\" href=\"#\">Hello</a>", pb.toString());
  }


}
