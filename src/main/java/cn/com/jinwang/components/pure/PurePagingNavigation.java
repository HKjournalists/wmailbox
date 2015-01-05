package cn.com.jinwang.components.pure;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.collections.MicroMap;

public class PurePagingNavigation extends PagingNavigation {

  private static final long serialVersionUID = 1L;

  public PurePagingNavigation(String id, IPageable pageable) {
    super(id, pageable);
  }

  @Override
  protected void populateItem(final LoopItem loopItem) {
    // Get the index of page this link shall point to
    final long pageIndex = getStartIndex() + loopItem.getIndex();

    // Add a page link pointing to the page
    final AbstractLink link = newPagingNavigationLink("pageLink", pageable, pageIndex);
    link.add(new TitleAppender(pageIndex));
    loopItem.add(link);

    // Add a page number label to the list which is enclosed by the link
    String label = "";
    if (labelProvider != null) {
      label = labelProvider.getPageLabel(pageIndex);
    } else {
      label = String.valueOf(pageIndex + 1);
    }
    link.add(new Label("pageNumber", label));
  }

  /**
   * Appends title attribute to navigation links
   * 
   * @author igor.vaynberg
   */
  private final class TitleAppender extends Behavior {
    private static final long serialVersionUID = 1L;
    /** resource key for the message */
    private static final String RES = "PagingNavigation.page";
    /** page number */
    private final long page;

    /**
     * Constructor
     * 
     * @param page page number to use as the ${page} var
     */
    public TitleAppender(long page) {
      this.page = page;
    }

    /** {@inheritDoc} */
    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
      Map<String, String> vars = new MicroMap<String, String>("page", String.valueOf(page + 1));
      tag.put("title", PurePagingNavigation.this.getString(RES, Model.ofMap(vars)));
    }
  }

}
