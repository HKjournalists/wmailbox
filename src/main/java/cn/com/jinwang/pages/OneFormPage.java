package cn.com.jinwang.pages;

import org.apache.wicket.Application;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.resource.ResourceReference;

import cn.com.jinwang.assets.pure.PureCss;
import cn.com.jinwang.components.base.InvisibleActionFormPanel;
import cn.com.jinwang.components.base.PureAjaxSubmitLink;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.viewmodel.JpaLoadableModel;

public class OneFormPage extends WebPage {
  private static final long serialVersionUID = 1L;

  // Label label = new Label("name", new PropertyModel(person, "name"));

  /**
   * Class org.apache.wicket.model.CompoundPropertyModel is a particular kind of model which is
   * usually used in conjunction with another Wicket feature called model inheritance. With this
   * feature, when a component needs to use a model but none has been assigned to it, it will search
   * through the whole container hierarchy for a parent with an inheritable model. Inheritable
   * models are those which implement interface org.apache.wicket.model.IComponentInheritedModel and
   * CompoundPropertyModel is one of them. Once a CompoundPropertyModel has been inherited by a
   * component, it will behave just like a PropertyModel using the id of the component as property
   * expression. As a consequence, to make the most of CompoundPropertyModel we must assign it to
   * one of the containers of a given component, rather than directly to the component itself.
   * setDefaultModel(new CompoundPropertyModel(person)); Label label = new Label("spouse.name");
   * add(label);
   */


  /**
   * CompoundPropertyModel can save us a lot of boring coding if we choose the id of components
   * according to properties name. However it's also possible to use this type of model even if the
   * id of a component does not correspond to a valid property expression. The method bind(String
   * property) allows to create a property model from a given CompoundPropertyModel using the
   * provided parameter as property expression. For example if we want to display the spouse's name
   * in a label having "xyz" as id, we can write the following code: Person person = new
   * Person("John", "Smith"); CompoundPropertyModel compoundModel; setDefaultModel(compoundModel =
   * new CompoundPropertyModel(person)); add(new Label("xyz", compoundModel.bind("spouse.name")));
   */

  /**
   * Model is referred to as static model because the result of its method getObject is fixed and it
   * is not dynamically evaluated each time the method is called. In contrast, models like
   * PropertyModel and CompoundProperty Model are called dynamic models.
   */

  public OneFormPage() {
    add(new LocalUserForm("localuserform"));
    InvisibleActionFormPanel invisibleActionFormPanel = new InvisibleActionFormPanel("invisibleFormPanel", "aClassName");
    add(invisibleActionFormPanel);
    add(new PureAjaxSubmitLink("submitlink","a", "Hello", invisibleActionFormPanel.getForm()){});
    
    add(new HeaderResponseContainer("js-container", "js-container-decorator"));
  }

  @SuppressWarnings("serial")
  private class LocalUserForm extends Form<JpaLoadableModel<LocalUser>> {

    private JpaLoadableModel<LocalUser> luModel = new JpaLoadableModel<LocalUser>(new LocalUser());

    public LocalUserForm(String id) {
      super(id);
      setDefaultModel(new CompoundPropertyModel<JpaLoadableModel<LocalUser>>(luModel));

      add(new TextField<String>("nickname").add(new PropertyValidator<String>()));
      add(new TextField<String>("email").add(new PropertyValidator<String>()));
      add(new TextField<String>("mobile").add(new PropertyValidator<String>()));
      add(new PasswordTextField("password").add(new PropertyValidator<String>()));
      
      add(new FeedbackPanel("feedback"));
    }

    public final void onSubmit() {
      LocalUser lu = luModel.getObject();
      lu.save();
    }
  }
  
  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    Application app = Application.get();
    ResourceReference jqReference = app.getJavaScriptLibrarySettings().getJQueryReference();
    response.render(CssHeaderItem.forReference(new PureCss()));
    response.render(JavaScriptHeaderItem.forReference(jqReference));
  }
}
