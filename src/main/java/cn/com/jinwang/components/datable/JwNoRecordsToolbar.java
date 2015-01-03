package cn.com.jinwang.components.datable;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;

public class JwNoRecordsToolbar extends NoRecordsToolbar {

  private static final long serialVersionUID = 1L;

  public JwNoRecordsToolbar(DataTable<?, ?> table) {
    super(table);
  }
}
