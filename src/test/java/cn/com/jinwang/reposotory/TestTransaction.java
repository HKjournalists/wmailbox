package cn.com.jinwang.reposotory;

import org.junit.Assert;
import org.junit.Test;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.utilbase.RandomUserGenerator;

public class TestTransaction extends JpaTestBase {
  
  /**
   * 这个测试之后，必须手动执行
   * 
   */
  @Test
  public void addUser() {
    LocalUser lu = RandomUserGenerator.randomUsers(1).get(0);
    luDao.save(lu);
    long num = luDao.countAll();
    Assert.assertEquals(1, num);
  }
}
