package cn.com.jinwang.reposotory;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.Assert;
import org.junit.Test;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.utilbase.RandomUserGenerator;

public class TestGuiceJpa extends JpaTestBase{

  @Test
  public void findUser() {
    final EntityManager em = getEntityManager();
    
    List<LocalUser> lus = em.createQuery("SELECT u FROM LocalUser u", LocalUser.class).getResultList();
    
    Assert.assertTrue(lus.size() > 0);
    save(RandomUserGenerator.randomUsers(1).get(0));
    Assert.assertSame(getEntityManager(), getEntityManager());
    Assert.assertSame(getEntityManager(), getEntityManager());
    Assert.assertSame(getEntityManager(), getEntityManager());
    Assert.assertSame(getEntityManager(), getEntityManager());
    
    new Thread(new Runnable() {
      
      @Override
      public void run() {
        Assert.assertSame(em, getEntityManager());
        
      }
    }).run();
  }
  
  private void save(LocalUser lu) {
    getEntityManager().persist(lu);
    getEntityManager().remove(lu);
  }
}
