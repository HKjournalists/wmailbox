package cn.com.jinwang.ptn;

import cn.com.jinwang.domain.BaseDomain;

public interface ModelSaveFilter {
  
  <T extends BaseDomain> void filter(T m);

}
