package cn.com.jinwang.utilbase;

import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.model.IModel;

import cn.com.jinwang.domain.BaseTreeDomain;

public class LabeledFolder<T extends BaseTreeDomain<T>> extends Folder<T> {

  private static final long serialVersionUID = 1L;
  
  public LabeledFolder(String id, AbstractTree<T> tree, IModel<T> model) {
    super(id, tree, model);
    // TODO Auto-generated constructor stub
  }



}
