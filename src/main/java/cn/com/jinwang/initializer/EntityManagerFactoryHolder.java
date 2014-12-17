package cn.com.jinwang.initializer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryHolder {

  public static EntityManagerFactory emf;

  private static String PERSISTENCE_UNIT_NAME = "hsqldbmem";

  public static EntityManagerFactory init() {
    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    return emf;
  }

  public static EntityManagerFactory init(String unitName) {
    emf = Persistence.createEntityManagerFactory(unitName);
    return emf;
  }

}
