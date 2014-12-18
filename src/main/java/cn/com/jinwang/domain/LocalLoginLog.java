package cn.com.jinwang.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cn.com.jinwang.factory.RepositoryFactory;
import cn.com.jinwang.interf.HasCreatedAt;

import com.google.common.base.Optional;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

/**
 * 
 *
 */
@Entity
@Table(name = "LOCAL_LOGIN_LOG")
public class LocalLoginLog extends BaseDomain<LocalLoginLog> implements HasCreatedAt {


  private static final long serialVersionUID = 1L;

  public LocalLoginLog() {
    super();
  }

  @Expose
  private long superUserId;

  @Expose
  private String identity;

  @Expose
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  @Expose
  private Date createdAt;

  @Expose
  private boolean success;

  @Expose
  private String remoteip;

  @Transient
  @Expose
  private String captcha;

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getRemoteip() {
    return remoteip;
  }

  public void setRemoteip(String remoteip) {
    this.remoteip = remoteip;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }

  @Override
  public JsonObject toJsonObject(String jsonv) {
    return toJsonObject();
  }

  public long getSuperUserId() {
    return superUserId;
  }

  public void setSuperUserId(long superUserId) {
    this.superUserId = superUserId;
  }

  @Override
  public LocalLoginLog save() {
    return RepositoryFactory.getLocalLoginLogRepository().save(this);
  }

  @Override
  public Optional<LocalLoginLog> findById(long id) {
    return null;
  }

}
