package cn.com.jinwang.utilbase;

import java.util.List;

import com.google.common.collect.Lists;

import cn.com.jinwang.domain.LocalUser;

public class RandomUserGenerator {

  public static List<LocalUser> randomUsers(int num) {
    List<LocalUser> lus = Lists.newArrayList();
    for (int i = 0; i < num; i++) {
      LocalUser lu = new LocalUser();
      lu.setEmail(RandomStringGenerator.randomAlphabetic(16) + "@g.cn");
      lu.setNickname(RandomStringGenerator.randomAlphabetic(16));
      lu.setMobile(RandomStringGenerator.randomNumeric(11));
      lu = SecUtil.setUserPwd(lu, "123456");
      lus.add(lu);
    }
    return lus;
  }
}
