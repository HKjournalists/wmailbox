package cn.com.jinwang.ptn;

import java.util.Iterator;
import java.util.List;

import cn.com.jinwang.domain.BaseDomain;

import com.google.common.collect.Lists;

public class ModelSaverFilters {
  
  private static List<ModelSaveFilter> filters = Lists.newArrayList();
  
  static {
    filters.add(new HasCreatedAtFilter());
  }
  
  public static <T extends BaseDomain> void filter(T m) {
    Iterator<ModelSaveFilter> filterIt = filters.iterator();
    while(filterIt.hasNext()) {
      filterIt.next().filter(m);
    }
  }
  
  public static void addFilter(ModelSaveFilter mf) {
    filters.add(mf);
  }
}
