package cn.com.jinwang.utilbase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.lesscss.LessCompiler;
import org.lesscss.LessException;

import com.google.common.io.Files;

public class BootstrapLess {

  private static LessCompiler lessCompiler = new LessCompiler();

  public static String lessBasePath = "node_modules/bootstrap/less";

  public static String cssBasePath = "node_modules/bootstrap/dist/css";
  
  public static String mixinBasePath = "node_modules/bootstrap/less/mixins";


  public static void lessOne(String lessfn) throws IOException, LessException {
    File lessFile = new File(lessBasePath, lessfn);
    File mixinFile = new File(mixinBasePath, lessfn);
    
    String variablesLess = Files.asCharSource(new File(lessBasePath, "variables.less"),
        StandardCharsets.UTF_8).read();
    StringBuffer sb = new StringBuffer(variablesLess);
    if (mixinFile.exists()) {
      Files.asCharSource(mixinFile, StandardCharsets.UTF_8).copyTo(sb);
    }
    Files.asCharSource(lessFile, StandardCharsets.UTF_8).copyTo(sb);
    
    File cssFile = new File(cssBasePath, Files.getNameWithoutExtension(lessfn) + ".css");
    
    Files.asCharSink(cssFile, StandardCharsets.UTF_8).write(lessCompiler.compile(sb.toString()));
  }
}
