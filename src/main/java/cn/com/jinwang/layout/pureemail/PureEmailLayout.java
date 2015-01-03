package cn.com.jinwang.layout.pureemail;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

import cn.com.jinwang.assets.bootstrap.NavsCss;
import cn.com.jinwang.assets.pure.PureCss;

public class PureEmailLayout extends WebPage {

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    response.render(CssHeaderItem.forReference(new PureCss()));
    response.render(CssHeaderItem.forReference(new NavsCss()));
  }

}
