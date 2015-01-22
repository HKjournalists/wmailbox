package cn.com.jinwang.viewmodel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.model.IModel;

public class TableAjaxCheckBox extends AjaxCheckBox {

  public TableAjaxCheckBox(String id, IModel<Boolean> model) {
    super(id, model);
  }

  private static final long serialVersionUID = 1L;

  @Override
  protected void onUpdate(AjaxRequestTarget target) {

  }

  public static class TableAjaxChecBoxModel extends AbstractCheckBoxModel {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isSelected() {
      return false;
    }

    @Override
    public void select() {

    }

    @Override
    public void unselect() {}
  }
}
