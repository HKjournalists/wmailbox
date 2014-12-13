package cn.com.jinwang.reposotory;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import cn.com.jinwang.initializer.EntityManagerFactoryHolder;

public abstract class JpaTestBase {

  protected EntityManager em = EntityManagerFactoryHolder.init().createEntityManager();

  private String callOrderIdenty = "sdioio3ii23233";

  private List<String> callOrder = Lists.newArrayList();

  @Before
  public void jpaSetup() {
    callOrder.add(callOrderIdenty);
  }

  @Test
  public void callOrder() {
    Assert.assertEquals(callOrderIdenty, callOrder.get(0));
  }

  public abstract void setupBeCalledInTransaction();
}
