package cn.com.jinwang.security;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.AbstractFactory;

import cn.com.jinwang.constant.ConstantBase;
import cn.com.jinwang.factory.RepositoryFactoryHolder;

public class JpaUserRealmFactory extends AbstractFactory<AuthorizingRealm>{

  @Override
  protected AuthorizingRealm createInstance() {
    HashedCredentialsMatcher hc = new HashedCredentialsMatcher(ConstantBase.PASSWORD_HASHALGORITHM);
    hc.setHashIterations(ConstantBase.PASSWORD_HASH_ITERATIONS);
    return new JpaUserRealm(RepositoryFactoryHolder.getLocalUserRepository(), hc);
  }

}
