package cn.com.jinwang.learning.inherit;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class MyPagingNavigator extends PagingNavigator{

  public MyPagingNavigator(String id, IPageable pageable) {
    super(id, pageable);
  }
}
