package cn.com.jinwang.components.pure;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import cn.com.jinwang.components.base.BodyOnlyLabel;
import cn.com.jinwang.constant.PureCssName;

public class PureAjaxSubmitLink extends AjaxSubmitLink {

  private static final long serialVersionUID = 1L;

  public PureAjaxSubmitLink(String id, String label, Form<?> form) {
    super(id, form);
    initMe("button", label);
  }

  public PureAjaxSubmitLink(String id,String tagname, String label, Form<?> form) {
    super(id, form);
    initMe(tagname, label);
  }

  private void initMe(String tagname, String label) {
    add(AttributeModifier.append("class", Model.of(PureCssName.Button.PureButton)));
//    if (tagn == PureButtonMarkup.ButtonTagName.A) {
//      add(AttributeModifier.append("href", Model.of("#")));
//    }
    add(new BodyOnlyLabel("purebtnlabel", Model.of(label)));
  }
}

/**
 * 
 * class="button-success pure-button" href="#"><span wicket:id="purebtnlabel"></span></a>
 */
