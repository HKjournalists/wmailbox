package cn.com.jingwang.examples;

import org.apache.wicket.Application;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.devutils.DevUtilsPage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import cn.com.jinwang.assets.pure.PureCss;
import cn.com.jinwang.layout.pureemail.PureEmailLayout;
import cn.com.jinwang.pages.OneFormPage;
import cn.com.jinwang.pages.security.SignInPage;

public class ExAjaxCheckbox extends WebPage {
  private static final long serialVersionUID = 1L;

  // private AjaxCheckBox cb;
  //
  // private Label statusdiv;

  private Model<Boolean> checkStatus = Model.of(false);

  public ExAjaxCheckbox(final PageParameters parameters) {
    super(parameters);

    AjaxCheckBox cb;

    final StatusDiv statusdiv = new StatusDiv("statusdiv", checkStatus);

    statusdiv.setOutputMarkupId(true);
    add(statusdiv);

    add(cb = new AjaxCheckBox("cb", checkStatus) {

      private static final long serialVersionUID = 1L;

//      private Boolean getChecked() {
//        return getModelObject();
//      }

      @Override
      protected void onUpdate(AjaxRequestTarget target) {
        // target.appendJavaScript(";alert(" + getChecked() + ");");
        target.add(statusdiv);
      }
    });


    add(new HeaderResponseContainer("js-container", "js-container-decorator"));

  }


  public static class StatusDiv extends Label {

    private static final long serialVersionUID = 1L;

    public StatusDiv(String id, IModel<Boolean> model) {
      super(id, model);
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
      replaceComponentTagBody(markupStream, openTag, getDisplayString());
    }
    
    private String getDisplayString() {
       Boolean b = (Boolean) getDefaultModelObject();
       if (b) {
         return "はい";
       } else {
         return "いいえ";
       }
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
