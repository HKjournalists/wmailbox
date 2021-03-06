package cn.com.jinwang.components.base;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Base Section class
 *
 * @author miha
 */
public class Section<T> extends GenericPanel<T> {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param id component id
     */
    public Section(String id) {
        this(id, null);
    }

    /**
     * Construct.
     *
     * @param id    component id
     * @param model the data model
     */
    public Section(String id, IModel<T> model) {
        super(id, model);

        setOutputMarkupId(true);
        setMarkupId(getId());
    }

}
