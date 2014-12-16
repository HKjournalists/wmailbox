package cn.com.jinwang.pages;

import org.apache.wicket.ResourceBundles;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see cn.com.jinwang.Start#main(String[])
 */
public class WicketApplicationPlain extends WebApplication {
  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<? extends WebPage> getHomePage() {
    return BaseHomePage.class;
  }



  /**
   * @see org.apache.wicket.Application#init()
   */
  @Override
  public void init() {
    super.init();
    getMarkupSettings().setStripWicketTags(true);

    configureResourceBundles();

    // Configure Shiro
    AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
    getSecuritySettings().setAuthorizationStrategy(authz);
    
    setHeaderResponseDecorator(new JavaScriptToBucketResponseDecorator("js-container-decorator"));

    // getSecuritySettings().setUnauthorizedComponentInstantiationListener(
    // new ShiroUnauthorizedComponentListener(LoginPage.class, UnauthorizedPage.class, authz));
    //
    // mountPage("account/login", LoginPage.class);
    // mountPage("account/logout", LogoutPage.class);
    // mountPage("admin", RequireAdminRolePage.class);
    // mountPage("view", RequireViewPermissionPage.class);
    // mountPage("auth", RequireAuthPage.class);
  }



  private void configureResourceBundles() {
    ResourceBundles bundles = getResourceBundles();
    bundles.addCssBundle(WicketApplicationPlain.class, "pure-min.css");
  }
  
  static class JavaScriptToBucketResponseDecorator implements IHeaderResponseDecorator 
  {

      private String bucketName;

      public JavaScriptToBucketResponseDecorator(String bucketName) {
          this.bucketName = bucketName;
      }

      @Override
      public IHeaderResponse decorate(IHeaderResponse response) {
          return new JavaScriptFilteredIntoFooterHeaderResponse(response, bucketName);
      }

  }
}
