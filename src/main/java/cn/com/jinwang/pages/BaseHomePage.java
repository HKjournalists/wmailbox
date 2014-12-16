package cn.com.jinwang.pages;

import org.apache.wicket.Application;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;

import cn.com.jinwang.assets.pure.PureCss;
import cn.com.jinwang.pages.security.SignInPage;

public class BaseHomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  public BaseHomePage(final PageParameters parameters) {
    super(parameters);

    add(new Link("goToSignInPage") {

      @Override
      public void onClick() {
        setResponsePage(SignInPage.class);
      }
    });
    
    add(new HeaderResponseContainer("js-container", "js-container-decorator"));

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
