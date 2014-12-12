package cn.com.jinwang.interf;

import cn.com.jinwang.domain.LocalUser;

public interface HasCreator {
  LocalUser getCreator();

  void setCreator(LocalUser user);

  boolean amIOwner(long userid);
}
