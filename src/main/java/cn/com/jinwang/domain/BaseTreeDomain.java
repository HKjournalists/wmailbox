package cn.com.jinwang.domain;

import javax.persistence.MappedSuperclass;

import com.google.gson.annotations.Expose;

@MappedSuperclass
public abstract class BaseTreeDomain extends BaseDomain {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @Expose
  private String parentIds;

  public String getParentIds() {
    return parentIds;
  }

  public void setParentIds(String parentIds) {
    this.parentIds = parentIds;
  }
}
