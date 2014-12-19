package cn.com.jinwang.reposotory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.LocalUser.ActivityState;

import cn.com.jinwang.utilbase.SecUtil;

public class TestUserDao extends JpaTestBase {

  private String email = "jianglibo@gmail.com";
  private String mobile = "12345678901";

  @Before
  public void setup() {
    luDao.deleteAll();
  }

  @Test
  public void testInsertUser() {
    LocalUser lu = new LocalUser();
    lu.setActivityState(ActivityState.ACTIVE);
    lu.setEmail(email);
    lu.setMobile(mobile);
    luDao.save(SecUtil.setUserPwd(lu, "apassword"));

    Optional<LocalUser> luInStore = luDao.findByEmail(email);

    Assert.assertTrue( "user must in db.", luInStore.isPresent());
  }
}
