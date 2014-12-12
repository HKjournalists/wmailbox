package cn.com.jinwang.interf;

import java.util.Set;

import cn.com.jinwang.domain.LocalUser;


public interface HasSharedUsers {
  public Set<LocalUser> getSharedUsers();

  public void setSharedUsers(Set<LocalUser> sharedUsers);
  
  public void save();
  
}
