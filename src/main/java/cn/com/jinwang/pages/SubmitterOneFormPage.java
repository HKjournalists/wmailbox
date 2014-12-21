package cn.com.jinwang.pages;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;

import cn.com.jinwang.assets.pure.PureCss;
import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.viewmodel.JpaLoadableModel;

public class SubmitterOneFormPage extends WebPage {
  private static final long serialVersionUID = 1L;

  // Label label = new Label("name", new PropertyModel(person, "name"));

  /**
   * A form can have any number of submitting components and we can specify which one among them is
   * the default one by calling the Form's method setDefaultButton(IFormSubmittingComponent
   * component). The default submitter is the one that will be used when user presses 'Enter' key in
   * a field of the form. In order to make the default button work, Wicket will add to our form a
   * hidden <div> tag containing a text field and a submit button with some JavaScript code to
   * trigger it:
   * 
   * Just like Wicket forms, interface IFormSubmitter defines methods onSubmit and onError. These
   * two methods have the priority over the namesake methods of the form, meaning that when a form
   * is submitted with an IFormSubmitter, the onSubmit of the submitter is called before the one of
   * the form. Similarly, if validation errors occurs during the first step of form processing,
   * submitter's method onError is called before the form's one.
   */
  
  private FeedbackPanel fdp;

  public SubmitterOneFormPage() {

    fdp = new FeedbackPanel("feedback");
    fdp.setOutputMarkupId(true);
    Form<?> form = new LocalUserForm("localuserform");
    add(form);

    Button submitButton = new Button("submitButton", Model.of("AjaxButton"));
    submitButton.add(new AjaxFormSubmitBehavior1(form, "click") {});
    add(submitButton);

    add(new HeaderResponseContainer("js-container", "js-container-decorator"));
  }
  
  private class AjaxFormSubmitBehavior1 extends AjaxFormSubmitBehavior {

    public AjaxFormSubmitBehavior1(Form<?> form, String event) {
      super(form, event);
    }
    
    @Override
    protected void onSubmit(AjaxRequestTarget target) {
      super.onSubmit(target);
    }
    
    @Override
    protected void onError(AjaxRequestTarget target) {
      super.onError(target);
      target.add(fdp);
    }
    
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

      add(new Button("submit1", Model.of("First submitter")));
      Button secondSubmitter;
      add(secondSubmitter = new Button("submit2", Model.of("Second submitter")));

      setDefaultButton(secondSubmitter);

      add(fdp);
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
