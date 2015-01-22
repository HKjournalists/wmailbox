package cn.com.jinwang.components.datable;

import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import com.google.common.collect.Sets;

import cn.com.jinwang.domain.BaseDomain;

public class CheckBoxColumn<T extends BaseDomain<T>, S> extends AbstractColumn<T, S> {

  private static final long serialVersionUID = 1L;

  private Set<Long> selected = Sets.newHashSet();

  public CheckBoxColumn(Set<Long> selected, IModel<String> displayModel) {
    super(displayModel);
    this.selected = selected;
  }

  @Override
  public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
    cellItem.add(new CheckPanel(componentId, rowModel));
  }



  protected IModel<Boolean> newCheckBoxModel(final IModel<T> rowModel) {
    return new AbstractCheckBoxModel() {

      private static final long serialVersionUID = 1L;

      @Override
      public void unselect() {
        selected.remove(rowModel.getObject().getId());
      }

      @Override
      public void select() {
        selected.add(rowModel.getObject().getId());
      }

      @Override
      public boolean isSelected() {
        return selected.contains(rowModel.getObject().getId());
      }

      @Override
      public void detach() {
        rowModel.detach();
      }
    };
  }

  protected CheckBox newCheckBox(String id, IModel<Boolean> checkModel) {
    return new AjaxCheckBox("check", checkModel) {

      private static final long serialVersionUID = 1L;

      @Override
      protected void onUpdate(AjaxRequestTarget target) {
        target.add(this);
      }
    };
    // return new CheckBox("check", checkModel);
  }

  private class CheckPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public CheckPanel(String id, IModel<T> rowModel) {
      super(id);
      add(newCheckBox("check", newCheckBoxModel(rowModel)));
    }
  }
}
