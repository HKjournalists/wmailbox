package cn.com.jinwang.interf;

import java.util.List;

public interface Tree<T> {
  T getParent();

  void setParent(T parent);

  void setParentIds(String parentIds);

  void setParentIds();

  List<T> getChildren();

  List<T> getBreadCrumb();

  long getId();

  void setId(long id);
}
