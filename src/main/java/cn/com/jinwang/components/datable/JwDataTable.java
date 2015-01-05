package cn.com.jinwang.components.datable;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;

public class JwDataTable<T, S> extends DataTable<T, S> {

  private static final long serialVersionUID = 1L;

  public JwDataTable(final String id, final List<? extends IColumn<T, S>> columns,
      final ISortableDataProvider<T, S> dataProvider, final int rowsPerPage) {
    super(id, columns, dataProvider, rowsPerPage);

    addTopToolbar(new HeadersToolbar<S>(this, dataProvider));
    addBottomToolbar(new NoRecordsToolbar(this));
    addBottomToolbar(new JwNavigationToolbar(this));
  }

}
