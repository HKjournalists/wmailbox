package cn.com.jinwang.reposotory;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.utilbase.RandomUserGenerator;

public class TestPersistMerge extends JpaTestBase {

  @Rule
  public ExpectedException removeUnattachedExp = ExpectedException.none();

  private LocalUser paddingUser;

  @After
  public void cleanUp() {
    if (paddingUser != null) {
      luDao.deleteById(paddingUser.getId());
    }
  }

//  @Test
//  public void removeDetached() {
//    removeUnattachedExp.expect(java.lang.IllegalArgumentException.class);
//    removeUnattachedExp.expectMessage("Entity must be managed to call remove");
//    LocalUser paddLocalUser = RandomUserGenerator.randomUsers(1).get(0);
//    luDao.save(paddLocalUser);
//    luDao.delete(paddLocalUser);
//  }

//  /**
//   * deleteById are attached.
//   */
//  @Test
//  public void removeDetachedById() {
//    removeUnattachedExp.expect(java.lang.IllegalArgumentException.class);
//    removeUnattachedExp.expectMessage("Entity must be managed to call remove");
//    LocalUser lu = RandomUserGenerator.randomUsers(1).get(0);
//    luDao.save(lu);
//    luDao.deleteById(lu.getId());
//  }

  @Test
  public void persist() {
    long countOrigin = luDao.countAll();

    LocalUser lu1, lu2;
    List<LocalUser> lus = RandomUserGenerator.randomUsers(1);
    lu1 = lus.get(0);
    Assert.assertEquals(0, lu1.getId());
    lu2 = luDao.save(lu1);
    // lu1 is attached.so id must not equal 0
    Assert.assertNotEquals(0, lu1.getId());
    Assert.assertSame(lu1, lu2);

    long countNew = luDao.countAll();
    Assert.assertEquals(countNew, countOrigin + 1);
    
    luDao.delete(lu2);
    
    countNew = luDao.countAll();
    Assert.assertEquals(countNew, countOrigin);
  }

}
