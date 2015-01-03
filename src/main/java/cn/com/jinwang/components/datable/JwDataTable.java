package cn.com.jinwang.components.datable;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;

public class JwDataTable<T, S> extends DataTable<T, S>{

  private static final long serialVersionUID = 1L;

  public JwDataTable(String id, List<? extends IColumn<T, S>> columns,
      IDataProvider<T> dataProvider, long rowsPerPage) {
    super(id, columns, dataProvider, rowsPerPage);
  }

}
