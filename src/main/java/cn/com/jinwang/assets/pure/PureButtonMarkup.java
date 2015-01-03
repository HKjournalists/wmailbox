package cn.com.jinwang.assets.pure;

import java.io.Serializable;

import cn.com.jinwang.utilbase.UiSize;

public class PureButtonMarkup implements Serializable{

  private static final long serialVersionUID = 1L;

  private PureButtonStyle style;
  
  private String tagname = "a";

  private String label = "";

  public PureButtonMarkup(String tagname, String label) {
    this.tagname = tagname;
    this.label = label;
    this.style = new PureButtonStyle();
  }

  public PureButtonMarkup(String tagname, String label, PureButtonStyle style) {
    this.tagname = tagname;
    this.label = label;
    this.style = style;
  }


  @Override
  public String toString() {
    StringBuilder tpl =
        new StringBuilder("<").append(tagname).append(getClassAttr()).append(getExtraAttr()).append(">")
            .append(label).append("</").append(tagname).append(">");
    return tpl.toString();
  }

  private String getExtraAttr() {
    if ("a".equals(tagname)) {
      return " href=\"#\"";
    }
    return "";
  }


  private String getClassAttr() {
    StringBuilder btnClass = new StringBuilder(" class=\"pure-button");
    if (style.getUiSize() != UiSize.NORMAL) {
      btnClass.append(" button-").append(style.getUiSize().value());
    }
    if (style.isActive()) {
      btnClass.append(" pure-button-active");
    }

    if (style.isDisabled()) {
      btnClass.append(" pure-button-disabled");
    }
    btnClass.append("\"");
    return btnClass.toString();
  }

}
