package cn.com.jinwang.utilbase;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.lesscss.LessCompiler;
import org.lesscss.LessException;

import cn.com.jinwang.constant.BootstrapCssNames;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class BootstrapLess {

  private static LessCompiler lessCompiler = new LessCompiler();

  public static BootstrapLess INSTANCE;

  private static String prepander;

  private static List<String> errorFiles = Lists.newArrayList();

  private BootstrapLess() {}

  private static StringBuilder createMixins() throws IOException {
    StringBuilder sb = new StringBuilder();
    for (String f : BootstrapCssNames.MIXIN_SOURCE_PATH.toFile().list(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".less");
      }
    })) {
      Files.asCharSource(BootstrapCssNames.MIXIN_SOURCE_PATH.resolve(f).toFile(),
          StandardCharsets.UTF_8).copyTo(sb);
    }
    return sb;
  }

  public static BootstrapLess getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new BootstrapLess();
      StringBuilder sb = new StringBuilder();
      try {
        Files.asCharSource(BootstrapCssNames.LESS_SOURCE_PATH.resolve("variables.less").toFile(), StandardCharsets.UTF_8)
            .copyTo(sb);
        sb.append(createMixins());
        errorFiles.clear();
        prepander = sb.toString();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return INSTANCE;
  }

  public void lessOne(String lessfn) {
    StringBuilder sb = new StringBuilder(prepander);

    try {
      Files.asCharSource(BootstrapCssNames.LESS_SOURCE_PATH.resolve(lessfn).toFile(), StandardCharsets.UTF_8).copyTo(sb);
      File cssFile = BootstrapCssNames.CSS_SOURCE_PATH.resolve(Files.getNameWithoutExtension(lessfn) + ".css").toFile();

      Files.asCharSink(cssFile, StandardCharsets.UTF_8).write(lessCompiler.compile(sb.toString()));
      System.out.println(lessfn);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LessException e) {
      errorFiles.add(lessfn);
      System.out.println("file " + lessfn + " get LessException." + e.getMessage());
    }
  }

  public void lessAll() {
    for (String f : BootstrapCssNames.LESS_SOURCE_PATH.toFile().list(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".less");
      }
    })) {
      lessOne(f);
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("error files:");
    for (String ef : errorFiles) {
      System.out.println("-->" + ef);
    }
  }
}
