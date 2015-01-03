package cn.com.jinwang.assets.bootstrap;

import org.apache.wicket.request.resource.CssResourceReference;

import cn.com.jinwang.constant.BootstrapCssNames;

public class NavsCss extends CssResourceReference{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public NavsCss() {
    super(NavsCss.class, BootstrapCssNames.NAVS);
  }

}
