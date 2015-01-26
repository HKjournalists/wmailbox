package cn.com.jinwang.utilbase;

import java.util.Properties;

import cn.com.jinwang.domain.LocalUser;
import cn.com.jinwang.domain.UserGroup;
import cn.com.jinwang.factory.RepositoryFactoryHolder;

public class SamplerCreator {

  private Properties properties;

  public SamplerCreator(Properties properties) {
    this.properties = properties;
  }

  public void createSamples() {
    createUsers();
    createGroups();
  }

  private void createGroups() {

    long num = RepositoryFactoryHolder.getUserGroupRepository().countAll();

    if (num > 0) {
      return;
    }
    UserGroup ugr1 = new UserGroup("root1");
    UserGroup ugr2 = new UserGroup("root2");

    ugr1.save();
    ugr2.save();

    UserGroup child11 = new UserGroup("child11");
    UserGroup child12 = new UserGroup("child12");
    UserGroup child21 = new UserGroup("child21");
    UserGroup child22 = new UserGroup("child22");

    child11.save();
    child12.save();
    child21.save();
    child22.save();

    RepositoryFactoryHolder.getUserGroupRepository().updateParent(ugr1, child11, child12);
    RepositoryFactoryHolder.getUserGroupRepository().updateParent(ugr2, child21, child22);

  }

  private void createUsers() {
    String sampleCfg = properties.getProperty("db.sampledata");

    if (sampleCfg == null || sampleCfg.isEmpty()) {
      ;
    } else {
      long usernum = RepositoryFactoryHolder.getLocalUserRepository().countAll();
      if (usernum < 50) {
        for (LocalUser lu : RandomUserGenerator.randomUsers(500)) {
          RepositoryFactoryHolder.getLocalUserRepository().save(lu);
        }
      }
    }
  }
}
