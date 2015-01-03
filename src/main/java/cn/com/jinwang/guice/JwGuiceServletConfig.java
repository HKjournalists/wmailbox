package cn.com.jinwang.guice;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import cn.com.jinwang.repository.LocalLoginLogJpaRepository;
import cn.com.jinwang.repository.LocalLoginLogRepository;
import cn.com.jinwang.repository.LocalRoleJpaRepository;
import cn.com.jinwang.repository.LocalRoleRepository;
import cn.com.jinwang.repository.LocalUserJpaRepository;
import cn.com.jinwang.repository.LocalUserRepository;
import cn.com.jinwang.repository.MixDomainPermissionJpaRepository;
import cn.com.jinwang.repository.MixDomainPermissionRepository;
import cn.com.jinwang.repository.UserGroupJpaRepository;
import cn.com.jinwang.repository.UserGroupRepository;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class JwGuiceServletConfig extends GuiceServletContextListener {

  public static Injector injector;


  public static EntityManager getEntityManager() {
    return injector.getInstance(EntityManager.class);
  }

  @Override
  protected Injector getInjector() {
    return setGlobalInjectorAndgetInjector("hsqldbfile", true);
  }


  public Injector setGlobalInjectorAndgetInjector(String jpaUnit, final boolean withShiroWebModule) {
    return injector = Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        super.configureServlets();
        install(new JpaPersistModule("hsqldbfile"));
        if (withShiroWebModule) {
          install(new JwShiroWebModule(getServletContext()));
        } else {
//          install(new ShiroModule());
        }
        filter("/*").through(PersistFilter.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put("applicationClassName", "cn.com.jinwang.pages.WicketApplicationPlain");
        params.put("filterMappingUrlPattern", "/*");
        filter("/*").through(SingletonWicketFilter.class, params);
      }
    }, new AbstractModule() {

      @Override
      protected void configure() {
        bind(LocalRoleJpaRepository.class).in(Singleton.class);
        bind(LocalUserJpaRepository.class).in(Singleton.class);
        bind(UserGroupJpaRepository.class).in(Singleton.class);
        bind(MixDomainPermissionJpaRepository.class).in(Singleton.class);
        bind(LocalLoginLogJpaRepository.class).in(Singleton.class);

        bind(LocalRoleRepository.class).to(LocalRoleJpaRepository.class);
        bind(LocalUserRepository.class).to(LocalUserJpaRepository.class);
        bind(UserGroupRepository.class).to(UserGroupJpaRepository.class);
        bind(MixDomainPermissionRepository.class).to(MixDomainPermissionJpaRepository.class);
        bind(LocalLoginLogRepository.class).to(LocalLoginLogJpaRepository.class);
      }
    });
  }
}
