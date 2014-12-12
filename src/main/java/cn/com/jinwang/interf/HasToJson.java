package cn.com.jinwang.interf;

import com.google.gson.JsonObject;

public interface HasToJson {
  String toJson(String jsonv);

  JsonObject toJsonObject(String jsonv);

  JsonObject toJsonObject();

}
