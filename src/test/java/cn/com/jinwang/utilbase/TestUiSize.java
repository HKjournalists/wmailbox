package cn.com.jinwang.utilbase;

import org.junit.Test;

public class TestUiSize {

  @Test
  public void t() {
    for( UiSize us : UiSize.values()) {
      System.out.println(us.value());
    }
  }
}
