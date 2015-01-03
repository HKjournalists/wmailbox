package cn.com.jinwang.components.base;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class PurePagingNavigator extends PagingNavigator {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PurePagingNavigator(String id, IPageable pageable) {
    super(id, pageable);
  }
}
