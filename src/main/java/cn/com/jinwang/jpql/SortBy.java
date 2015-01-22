package cn.com.jinwang.jpql;

public class SortBy {
  private boolean asc;

  private String direction;

  private String field;

  public SortBy(String ss) {
    if (ss.startsWith("-")) {
      setAsc(false);
      setField(ss.substring(1));
      setDirection("DESC");
    } else {
      setAsc(true);
      setField(ss);
      setDirection("ASC");
    }
  }
  
  public SortBy(String field, boolean ascending) {
      setAsc(ascending);
      setField(field);
      setDirection( ascending ? "ASC" : "DESC");
  }


  public String getString(String dn) {
    // a.publishedAt DESC
    return new StringBuilder(dn).append(".").append(getField()).append(" ").append(getDirection())
        .toString();
  }

  public boolean isAsc() {
    return asc;
  }

  public void setAsc(boolean asc) {
    this.asc = asc;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

}
