package cn.com.jinwang.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class SelectDataModel {

  private Map<String, String> map;

  private String[] selected;

  public SelectDataModel(boolean ispair, String... pairs) {
    if (ispair) {
      initMe(pairs);
    } else {
      map = Maps.newLinkedHashMap();
      for (int i = 0; i < pairs.length; i++) {
        map.put(pairs[i], pairs[i]);
      }
    }
  }

  private void initMe(String... pairs) {
    if (pairs.length % 2 != 0) {
      throw new IllegalArgumentException();
    }
    map = Maps.newLinkedHashMap();
    for (int i = 0; i < pairs.length; i += 2) {
      map.put(pairs[i], pairs[i + 1]);
    }
  }

  public SelectDataModel(String... pairs) {
    initMe(pairs);
  }

  public Map<String, String> getMap() {
    return map;
  }

  public void setMap(Map<String, String> map) {
    this.map = map;
  }

  public List<Entry<String, String>> getOptions() {
    return new ArrayList<Map.Entry<String, String>>(getMap().entrySet());
  }

  public Optional<String> ifHasOption(String action, String returnValue) {
    if (this.selected == null) {
      return Optional.absent();
    }
    for (String k : selected) {
      if (k.equals(action)) {
        return Optional.of(returnValue);
      }
    }
    return Optional.absent();
  }

  public String[] getSelected() {
    return selected;
  }

  public SelectDataModel setSelected(String... selected) {
    this.selected = selected;
    return this;
  }

  public void clearSelected() {
    this.selected = null;
  }

}
