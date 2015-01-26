package cn.com.jinwang.domain;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

@MappedSuperclass
public abstract class BaseTreeDomain<T extends BaseTreeDomain<T>> extends BaseDomain<T> {

  private static final long serialVersionUID = 1L;

  @Expose
  private String parentIds;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  @OrderBy("position ASC")
  private List<T> children = Lists.newArrayList();


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parentId")
  private T parent;

  public String getParentIds() {
    return parentIds;
  }

  public void setParentIds(String parentIds) {
    this.parentIds = parentIds;
  }

  public T getParent() {
    return parent;
  }
  
  public List<T> getChildren() {
    return children;
  }

  public void setChildren(List<T> children) {
    this.children = children;
  }

  public void setParent(T parent) {
    this.parent = parent;
    List<T> parents = Lists.newArrayList();
    T p = this.getParent();
    while (p != null) {
      parents.add(p);
      p = p.getParent();
    }
    java.util.Collections.reverse(parents);
    parents.add((T) this);
    StringBuilder sb = new StringBuilder(",");
    for (T t : parents) {
      sb.append(t.getId()).append(",");
    }
    this.setParentIds(sb.toString());
  }
}
