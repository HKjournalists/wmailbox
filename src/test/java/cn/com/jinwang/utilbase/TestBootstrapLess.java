package cn.com.jinwang.utilbase;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.lesscss.LessException;

import cn.com.jinwang.constant.BootstrapCssNames;

public class TestBootstrapLess {



  @Test
  public void tOne() throws IOException, LessException {
    String lessFn = "alerts.less";
    File cssFile = BootstrapCssNames.CSS_SOURCE_PATH.resolve("alerts.css").toFile();
    if (cssFile.exists()) {
      cssFile.delete();
    }
    BootstrapLess.getInstance().lessOne(lessFn);

    Assert.assertTrue("alerts.less should compiled!", cssFile.exists());
  }
  
  @Test
  public void tAll() throws IOException, LessException {
    BootstrapLess.getInstance().lessAll();
  }
}
