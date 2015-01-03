package cn.com.jinwang.assets.pure;

import java.io.Serializable;

import cn.com.jinwang.utilbase.UiSize;

public class PureButtonStyle implements Serializable{

  private static final long serialVersionUID = 1L;

  private UiSize uiSize = UiSize.NORMAL;

  private boolean disabled = false;

  private boolean active = false;
  
  public PureButtonStyle() { }
  
  
  public PureButtonStyle(UiSize uiSize) {
    this.uiSize = uiSize;
  }
  
  public PureButtonStyle(UiSize uiSize, boolean disabled, boolean active) {
    this.uiSize = uiSize;
    this.disabled = disabled;
    this.active = active;
  }

  public UiSize getUiSize() {
    return uiSize;
  }

  public void setUiSize(UiSize uiSize) {
    this.uiSize = uiSize;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
