package cn.com.jinwang.assets.pure;

import org.apache.wicket.request.resource.CssResourceReference;

public class PureCss extends CssResourceReference{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PureCss() {
    super(PureCss.class, "pure-min.css");
  }

}
