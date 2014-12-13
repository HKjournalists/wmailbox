package cn.com.jinwang.misc;

import java.util.Collections;
import java.util.List;

import cn.com.jinwang.domain.BaseDomain;
import cn.com.jinwang.interf.HasCreator;
import cn.com.jinwang.interf.Tree;

import com.google.common.collect.Lists;

public class DomainUtils {

  public static boolean amIOwner(HasCreator hasCreator, long userId) {
    if (hasCreator.getCreator() != null && hasCreator.getCreator().getId() == userId) {
      return true;
    }
    return false;
  }

  public static String[] getPermissionString(BaseDomain bd, String... actions) {
    String[] ps = new String[actions.length];
    for (int i = 0; i < ps.length; i++) {
      ps[i] = bd.getClass().getSimpleName() + ":" + actions[i] + ":" + bd.getId();
    }
    return ps;
  }

  public static String[] getStaticPermissionString(String simpleName, String... actions) {
    String[] ps = new String[actions.length];
    for (int i = 0; i < ps.length; i++) {
      ps[i] = simpleName + ":" + actions[i];
    }
    return ps;
  }

  public static <T extends BaseDomain> List<T> excludeIds(List<T> origin, String excludeIds) {
    List<T> result = Lists.newArrayList();
    String[] ss = excludeIds.split(",");
    for (T t : origin) {
      for (String s : ss) {
        if (!s.equals(String.valueOf(t.getId()))) {
          result.add(t);
        }
      }
    }
    return result;
  }

  public static <T extends Tree<T>> List<T> getBreadCrumb(T domain) {
    List<T> b = Lists.newArrayList();
    b.add(domain);
    T p = domain.getParent();
    while (p != null) {
      b.add(p);
      p = p.getParent();
    }
    Collections.reverse(b);
    return b;
  }

  public static <T extends Tree<T>> void setParentIds(T domain) {
    // [4,3,2,1]last is top
    List<T> parents = Lists.newArrayList();
    T p = domain.getParent();
    while (p != null) {
      parents.add(p);
      p = p.getParent();
    }
    java.util.Collections.reverse(parents);
    parents.add(domain);
    StringBuilder sb = new StringBuilder(",");
    for (T t : parents) {
      sb.append(t.getId()).append(",");
    }
    domain.setParentIds(sb.toString());
  }

  public static String[] getAllActions(Enum[] enus) {
    int l = enus.length;
    String[] acs = new String[l];

    for (int i = 0; i < l; i++) {
      acs[i] = enus[i].toString();
    }
    return acs;
  }

}
