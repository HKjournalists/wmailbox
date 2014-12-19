package cn.com.jinwang.reposotory;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.LocalUser.ActivityState;
import cn.com.jinwang.factory.RepositoryFactoryHolder;
import cn.com.jinwang.guice.JwGuiceServletConfig;
import cn.com.jinwang.repository.LocalUserRepository;
import cn.com.jinwang.utilbase.RandomStringGenerator;
import cn.com.jinwang.utilbase.SecUtil;

import com.google.common.collect.Lists;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

public abstract class JpaTestBase {

  protected static Injector injector;

  protected LocalUserRepository luDao;

  private String callOrderIdenty = "sdioio3ii23233";

  private List<String> callOrder = Lists.newArrayList();
  
  @BeforeClass
  public static void injectorSetup() {
    injector = new JwGuiceServletConfig().getInject("hsqldbfile");
    injector.getInstance(PersistService.class).start();
  }
  
  public static void injectorCleanUp() {
    injector.getInstance(PersistService.class).stop();
  }

  @Before
  public void jpaSetup() {
    luDao = RepositoryFactoryHolder.getLocalUserRepository();
    callOrder.add(callOrderIdenty);
  }

  @Test
  public void callOrder() {
    Assert.assertEquals(callOrderIdenty, callOrder.get(0));
  }

  public void createOneUser(String email, String password) {
    LocalUser lu = new LocalUser();
    lu.setActivityState(ActivityState.ACTIVE);
    lu.setEmail(email);
    lu.setMobile(RandomStringGenerator.randomNumeric(11));
    luDao.save(SecUtil.setUserPwd(lu, password));
  }
  
  public EntityManager getEntityManager() {
    return JwGuiceServletConfig.getEntityManager();
  }
}
