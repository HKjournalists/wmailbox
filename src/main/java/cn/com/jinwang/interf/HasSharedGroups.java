package cn.com.jinwang.interf;

import java.util.Set;

import cn.com.jinwang.domain.UserGroup;

public interface HasSharedGroups {
  public Set<UserGroup> getSharedGroups();

  public void setSharedGroups(Set<UserGroup> sharedGroups);

  void save();
}
