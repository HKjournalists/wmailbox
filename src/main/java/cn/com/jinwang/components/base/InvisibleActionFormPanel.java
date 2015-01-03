package cn.com.jinwang.components.base;

import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class InvisibleActionFormPanel extends Panel {

  private static final long serialVersionUID = 1L;
  
  private static String formId = "invisibleActionForm";
  
  private ActionForm af;

  public InvisibleActionFormPanel(String id, String clname) {
    super(id);
    add(af = new ActionForm(formId, clname));
  }

  public ActionForm getForm() {
    return af;
  }

  @SuppressWarnings("serial")
  private class ActionForm extends Form<Model<InvisibleActionFormVo>> {

    private Model<InvisibleActionFormVo> actionModel;

    public ActionForm(String id, String clname) {
      super(id);

      setDefaultModel(new CompoundPropertyModel<Model<InvisibleActionFormVo>>(actionModel =
          new Model<InvisibleActionFormVo>(new InvisibleActionFormVo(clname))));

      add(new HiddenField<String>("action").add(new PropertyValidator<String>()));
      add(new HiddenField<String>("values").add(new PropertyValidator<String>()));
      add(new HiddenField<String>("clname").add(new PropertyValidator<String>()));
    }

    public final void onSubmit() {
      @SuppressWarnings("unused")
      InvisibleActionFormVo lu = actionModel.getObject();
    }
  }
}
