package cn.com.jinwang.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BootstrapCssNames {
  
  private static String node_modules = "node_modules";
  private static String bootstrap = "bootstrap";
  
  public static Path CSS_SOURCE_PATH = Paths.get(node_modules, bootstrap, "dist", "css" );
  public static Path LESS_SOURCE_PATH = Paths.get(node_modules, bootstrap, "less");
  
  public static Path MIXIN_SOURCE_PATH = Paths.get(node_modules, bootstrap, "less", "mixins");
  
  
  public static String ALERTS = "alerts.css";
  public static String BADGES = "badges.css";
  public static String BREADCRUMBS = "breadcrumbs.css";
  public static String BUTTON_GROUPS = "button-groups.css";
  public static String BUTTONS = "buttons.css";
  public static String CAROUSEL = "carousel.css";
  public static String CLOSE = "close.css";
  public static String CODE = "code.css";
  public static String COMPONENT_ANIMATIONS = "component-animations.css";
  public static String DROPDOWNS = "dropdowns.css";
  public static String FORMS = "froms.css";
  public static String GLYPHICONS = "glyphicons.css";
  public static String GRID = "grid.css";
  public static String JUMBOTRON = "jumbotron.css";
  public static String LABELS = "labels.css";
  public static String LIST_GROUP = "list-group.css";
  public static String MEDIA = "media.css";
  public static String MODALS = "modals.css";
  public static String NAVS = "navs.css";
  public static String NORMALIZE = "normalize.css";

  public static String PAGER = "pager.css";
  public static String POPOVERS = "popovers.css";
  public static String PRINT = "print.css";
  public static String PROGRESS_BAR = "progress-bar.css";
  public static String RESPONSIVE_EMBED = "responsive-embed.css";
  public static String RESPONSIVE_UTILITIES = "responsive-utilities.css";
  public static String SCAFFOLDING = "scaffolding.css";
  public static String TABLES = "tables.css";
  
  public static String THUMBNAILS = "thumbnails.css";
  public static String TOOLTIPS = "tooltips.css";
  public static String TYPE = "type.css";
  public static String UTILITIES = "utilities.css";
  public static String VARIABLES = "variables.css";
  public static String WELLS = "wells.css";
}
