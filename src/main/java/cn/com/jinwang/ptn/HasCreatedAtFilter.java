package cn.com.jinwang.ptn;

import java.util.Date;

import cn.com.jinwang.domain.BaseDomain;
import cn.com.jinwang.interf.HasCreatedAt;

public class HasCreatedAtFilter implements ModelSaveFilter {

  @Override
  public <T extends BaseDomain> void filter(T m) {
    if (m instanceof HasCreatedAt) {
      HasCreatedAt hc = (HasCreatedAt)m;
      if(hc.getCreatedAt() == null) {
        hc.setCreatedAt(new Date());
      }
    }
  }
}
