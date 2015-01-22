package cn.com.jinwang.components.pure;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupCacheKeyProvider;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;

import cn.com.jinwang.assets.pure.PureButtonMarkup;


/**
 * 
 * @author jianglibo@gmail.com must inherite from panel, not WebMarkupContainer
 * 
 */

public class PureButtonGroup extends Panel
    implements
      IMarkupResourceStreamProvider,
      IMarkupCacheKeyProvider {

  private static final long serialVersionUID = 1L;
  
  private PureButtonMarkup[] buttons;

  public PureButtonGroup(String id, PureButtonMarkup...buttons) {
    super(id);
    this.buttons = buttons;
  }

  @Override
  public IResourceStream getMarkupResourceStream(MarkupContainer container, Class<?> containerClass) {
    StringResourceStream resourceStream = new StringResourceStream(new ButtonGroupMarkup(buttons).toString());

    return resourceStream;
  }

  /**
   * Avoid markup caching for this component
   */
  @Override
  public String getCacheKey(MarkupContainer arg0, Class<?> arg1) {
    return null;
  }

  public static class ButtonGroupMarkup {
    
    public PureButtonMarkup[] buttons;
    
    public ButtonGroupMarkup(PureButtonMarkup...buttons) {
      this.buttons = buttons;
    }

    private StringBuilder tpl = new StringBuilder(
        "<wicket:panel><div style=\"padding-top: .7em;padding-left: 1em;\">");
    
    @Override
    public String toString() {
      for(PureButtonMarkup pbm : buttons) {
        tpl.append(pbm.toString());
      }
      tpl.append("</div></wicket:panel>");
      return tpl.toString();
    }
  }

}
/*
 * 
    

 * <div> <style scoped>
 * 
 * .button-xsmall { font-size: 70%; }
 * 
 * .button-small { font-size: 85%; }
 * 
 * .button-large { font-size: 110%; }
 * 
 * .button-xlarge { font-size: 125%; }
 * 
 * </style>
 * 
 * <button class="button-xsmall pure-button">Extra Small Button</button> <button
 * class="button-small pure-button">Small Button</button> <button class="pure-button">Regular
 * Button</button> <button class="button-large pure-button">Large Button</button> <button
 * class="button-xlarge pure-button">Extra Large Button</button> </div>
 */
