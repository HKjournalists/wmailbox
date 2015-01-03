package cn.com.jinwang.components.datable;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;

public class JwHeadersToolbar<S> extends HeadersToolbar<S>{

  private static final long serialVersionUID = 1L;

  public <T> JwHeadersToolbar(DataTable<T, S> table, ISortStateLocator<S> stateLocator) {
    super(table, stateLocator);
  }

}
