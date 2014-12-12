package cn.com.jinwang.interf;

import java.util.List;

import cn.com.jinwang.domain.MixDomainPermission;


public interface HasPermissionString {
  String[] getPermissionString(String... actions);

  List<MixDomainPermission> createAllMixPermissions();

  MixDomainPermission getAddPermission();

}
