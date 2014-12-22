package cn.com.jinwang.utilbase;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.lesscss.LessException;

public class TestBootstrapLess {



  @Test
  public void tOne() throws IOException, LessException {
    String lessFn = "alerts.less";
    File cssFile = new File(BootstrapLess.cssBasePath, "alerts.css");
    if (cssFile.exists()) {
      cssFile.delete();
    }
    BootstrapLess.lessOne(lessFn);

    Assert.assertTrue("alerts.less should compiled!", cssFile.exists());

  }
}
