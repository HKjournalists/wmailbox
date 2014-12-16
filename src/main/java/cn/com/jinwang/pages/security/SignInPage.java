package cn.com.jinwang.pages.security;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

public class SignInPage extends WebPage {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        StatelessForm form = new StatelessForm("form"){
            @Override
            protected void onSubmit() {
                if(Strings.isEmpty(username) || Strings.isEmpty(password))
                    return;
                
                boolean authResult = true;
                
                if(authResult)
                    continueToOriginalDestination();
            }
        };
        
        form.setDefaultModel(new CompoundPropertyModel(this));
        
        form.add(new TextField("username"));
        form.add(new PasswordTextField("password"));
        
        add(form);
    }
}
