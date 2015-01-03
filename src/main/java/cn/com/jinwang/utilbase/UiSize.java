package cn.com.jinwang.utilbase;

public enum UiSize {
  XSMALL("xsmall"),
  SMALL("small"),
  NORMAL("normal"),
  LARGE("large"),
  XLARGE("xlarge");
  
  private String size;
  
  UiSize(String size) {
    this.size = size;
  }
  
  public String value() {
    return size;
  }
  
}
