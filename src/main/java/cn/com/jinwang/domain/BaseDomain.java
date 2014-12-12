package cn.com.jinwang.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import cn.com.jinwang.interf.HasLongId;
import cn.com.jinwang.interf.HasToJson;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

@MappedSuperclass
public abstract class BaseDomain implements Serializable, HasLongId, HasToJson {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "appSeqM3958")
  @Column(name = "id")
  @Expose
  @TableGenerator(name = "appSeqM3958", table = "APP_SEQ_STORE", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "APP_M3958.APP_SHARED_PK", valueColumnName = "APP_SEQ_VALUE")
  private long id;

  @Version
  @Column(name = "version")
  private Integer version;

  public <T extends BaseDomain> String stringfyDomains(List<T> domains) {
    StringBuilder sb = new StringBuilder();
    for (T a : domains) {
      sb.append(a.getId());
      sb.append(",");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String toJson(String jsonv) {
    JsonObject jo = null;
    if (jsonv == null || jsonv.isEmpty()) {
      jo = toJsonObject();
    } else {
      jo = toJsonObject(jsonv);
    }
    return jo.toString();
  }

  public abstract JsonObject toJsonObject(String jsonv);

  public JsonObject toJsonObject() {
    return null;
  }
  
  public abstract void save();

}
