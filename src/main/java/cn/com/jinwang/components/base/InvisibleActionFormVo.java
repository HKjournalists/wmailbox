package cn.com.jinwang.components.base;

import java.io.Serializable;

import cn.com.jinwang.utilbase.TableAction;

public class InvisibleActionFormVo implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private TableAction action = TableAction.NONE;
  private String clname;
  private String values;
  
  public InvisibleActionFormVo(String clname) {
    this.clname = clname;
  }

  public TableAction getAction() {
    return action;
  }

  public void setAction(TableAction action) {
    this.action = action;
  }

  public String getClname() {
    return clname;
  }

  public void setClname(String clname) {
    this.clname = clname;
  }

  public String getValues() {
    return values;
  }

  public void setValues(String values) {
    this.values = values;
  }

}
