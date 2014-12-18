package cn.com.jinwang.guice;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class JwGuiceServletConfig extends GuiceServletContextListener {
  
  public static Injector injector;
  
  
  public static EntityManager getEntityManager() {
    return getEntityManager();
  }

  @Override
  protected Injector getInjector() {
    return getInject("hsqldbfile");
  }
  
  
  public Injector getInject(String jpaUnit) {
    return injector = Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        super.configureServlets();
        install(new JpaPersistModule("hsqldbfile"));
        filter("/*").through(PersistFilter.class);
      }
    });
  }
}
