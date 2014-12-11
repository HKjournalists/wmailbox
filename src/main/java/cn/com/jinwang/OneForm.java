
package cn.com.jinwang;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class OneForm extends WebPage {
    private static final long serialVersionUID = 1L;

     public OneForm() {
        IModel messageModel = new Model("Hello World!");
        add(new Label("message", messageModel));
        add(new MessageForm("messageInputForm", messageModel));
      }
     
      private final class MessageForm extends Form {
        public MessageForm(String id, IModel model) {
          super(id);
          add(new TextField<>("messageInput", model));
        }

        protected void onSubmit() {
          // nothing to do here as the model is automatically updated
        }
      }
}