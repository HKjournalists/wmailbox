package cn.com.jinwang.utilbase;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

import cn.com.jinwang.constant.ConstantBase;
import cn.com.jinwang.domain.LocalUser;


public class SecUtil {

  public static LocalUser setUserPwd(LocalUser lu, String rawTextPassword) {
    RandomNumberGenerator rng = new SecureRandomNumberGenerator();
    ByteSource salt = rng.nextBytes();
    Sha256Hash hashedPassword =
        new Sha256Hash(rawTextPassword, salt, ConstantBase.PASSWORD_HASH_ITERATIONS);
    lu.setPasswordSalt(salt.toBase64());
    lu.setPassword(hashedPassword.toBase64());
    return lu;
  }
}
