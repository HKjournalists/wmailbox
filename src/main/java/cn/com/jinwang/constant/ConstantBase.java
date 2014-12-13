package cn.com.jinwang.constant;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class ConstantBase {

  public static String PASSWORD_HASHALGORITHM = Sha256Hash.ALGORITHM_NAME;

  public static int PASSWORD_HASH_ITERATIONS = 1024;
}
