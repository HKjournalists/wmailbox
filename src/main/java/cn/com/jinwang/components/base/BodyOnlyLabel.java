package cn.com.jinwang.components.base;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class BodyOnlyLabel extends Label{

  public BodyOnlyLabel(String id, IModel<?> model) {
    super(id, model);
    setRenderBodyOnly(true);
  }

  private static final long serialVersionUID = 1L;

}
