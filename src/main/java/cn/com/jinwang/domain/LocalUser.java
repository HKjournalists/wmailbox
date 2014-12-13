package cn.com.jinwang.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import cn.com.jinwang.interf.HasCreatedAt;
import cn.com.jinwang.misc.SelectDataModel;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Entity implementation class for Entity: LocalUser alter table local_user add nickname
 * varchar(255) default null;
 */
@Entity
@Table(name = "LOCAL_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"mobile"})})
public class LocalUser extends BaseDomain implements HasCreatedAt {

  private static final long serialVersionUID = 1L;

  public static enum ActivityState {
    ACTIVE, FORBIDDEN;
    public static SelectDataModel getSelectDataModel() {
      return new SelectDataModel(false, ACTIVE.toString(), FORBIDDEN.toString());
    }
  }

  @Expose(serialize = false)
  @Column(nullable = false)
  private String password;

  @Expose
  private boolean enableAccessTk;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ActivityState getActivityState() {
    return activityState;
  }

  public void setActivityState(ActivityState activityState) {
    this.activityState = activityState;
  }

  private String passwordSalt;

  @Expose
  @Enumerated(STRING)
  private ActivityState activityState;

  @Expose
  private String nickname;

  @Expose
  private String figureurl;

  @Expose
  @Column(unique = true)
  private String email;

  @Expose
  @Column(unique = true)
  private String mobile;

  @Expose
  @Temporal(TIMESTAMP)
  private Date createdAt = new Date();

  @Expose
  @Temporal(TIMESTAMP)
  private Date pwdUpdatedAt = new Date();

  @Expose
  @Temporal(TIMESTAMP)
  private Date lockOutAt = new Date();

  private String readedBc;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "LOCAL_USER_LOCAL_ROLE", joinColumns = @JoinColumn(name = "LOCAL_USER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"))
  private Set<LocalRole> roles = Sets.newHashSet();

  @ManyToMany
  @JoinTable(name = "LOCAL_USER_MIXPERMISSION", joinColumns = @JoinColumn(name = "LOCAL_USER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "MIXPERMISSION_ID", referencedColumnName = "id"))
  private Set<MixDomainPermission> permissions = Sets.newHashSet();

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<UserGroup> groups = Sets.newHashSet();

  public LocalUser() {
    super();
  }

  @PreUpdate
  public void mobileEmptyToNull() {
    if (getMobile() != null && getMobile().trim().isEmpty()) {
      setMobile(null);
    }
  }

  public Set<LocalRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<LocalRole> roles) {
    this.roles = roles;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Set<MixDomainPermission> getPermissions() {
    return permissions;
  }

  // public List<MixDomainPermission> getPermissions(QueryPageInfo qpif,
  // String qstr) {
  // return
  // StaticEntityManagerHolder.getMpRepo().myPermissions(qpif.getStart(),
  // qpif.getEnd(),
  // this, qstr);
  // }
  //
  // public long countPermissions(String qstr) {
  // return StaticEntityManagerHolder.getMpRepo().countMyPermissions(this,
  // qstr);
  // }

  public void setPermissions(Set<MixDomainPermission> permissions) {
    this.permissions = permissions;
  }

  // public Set<DiskFilePath> getDiskfilePathes() {
  // return diskfilePathes;
  // }
  //
  // public void setDiskfilePathes(Set<DiskFilePath> diskfilePathes) {
  // this.diskfilePathes = diskfilePathes;
  // }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getFigureurl() {
    return figureurl;
  }

  public void setFigureurl(String figureurl) {
    this.figureurl = figureurl;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Set<UserGroup> getGroups() {
    return groups;
  }

  public void setGroups(Set<UserGroup> groups) {
    this.groups = groups;
  }

  // public List<NameValueValue> getNameValueValues() {
  // return nameValueValues;
  // }
  //
  // public boolean isLocakOuted(PasswordValidateService pvs) {
  // return pvs.isLockOuted(this);
  // }
  //
  // public List<NameValueValue> getNameValueValues(String name) {
  // List<NameValueValue> result = Lists.newArrayList();
  // for (NameValueValue nv : getNameValueValues()) {
  // if (name.equals(nv.getName())) {
  // result.add(nv);
  // }
  // }
  // return result;
  // }
  //
  //
  // public List<NameValueValue> getNameValueValues(String name, String value)
  // {
  // List<NameValueValue> result = Lists.newArrayList();
  // for (NameValueValue nv : getNameValueValues()) {
  // if (name.equals(nv.getName()) && value.equals(nv.getValue())) {
  // result.add(nv);
  // }
  // }
  // return result;
  // }
  //
  // public String getNameValueValuesAsJson(String name) {
  // Set<NameValueValue> result = Sets.newHashSet();
  // for (NameValueValue nv : getNameValueValues()) {
  // if (name.equals(nv.getName())) {
  // result.add(nv);
  // }
  // }
  // return StaticEntityManagerHolder.getGson().toJson(result,
  // AppTypeToken.nameValuePairSetTypeToken);
  // }
  //
  // public String getNameValueValuesAsJson(String name, String value) {
  // Set<NameValueValue> result = Sets.newHashSet();
  // for (NameValueValue nv : getNameValueValues()) {
  // if (name.equals(nv.getName()) && value.equals(nv.getValue())) {
  // result.add(nv);
  // }
  // }
  // return StaticEntityManagerHolder.getGson().toJson(result,
  // AppTypeToken.nameValuePairSetTypeToken);
  // }
  //
  // public void updateUniqueTwo(NameValueValue nvv) {
  // List<NameValueValue> old = getNameValueValues();
  // List<NameValueValue> newv = Lists.newArrayList();
  // for (NameValueValue n : old) {
  // if (n.getName().equals(nvv.getName()) &&
  // n.getValue().equals(nvv.getValue())) {
  // ;
  // } else {
  // newv.add(n);
  // }
  // }
  // newv.add(nvv);
  // setNameValueValues(newv);
  // }
  //
  // public void updateUniqueTwo(Optional<NameValueValue> nvvOp) {
  // updateUniqueTwo(nvvOp.get());
  // }
  //
  // public void setNameValueValues(List<NameValueValue> nameValueValues) {
  // this.nameValueValues = nameValueValues;
  // }

  public Set<String> getRoleNames() {
    Set<String> rns = Sets.newHashSet();
    for (LocalRole lr : this.getRoles()) {
      rns.add(lr.getName());
    }
    return rns;
  }

  @Override
  public JsonObject toJsonObject(String jsonv) {
    JsonObject jo = toJsonObject();
    // if (jsonv != null) {
    // if ("v".equals(jsonv)){
    // jo.add("roles",
    // StaticEntityManagerHolder.getGson().toJsonTree(getRoleNames(),
    // AppTypeToken.stringListTypeToken));
    // }
    // }
    return jo;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  public boolean isEnableAccessTk() {
    return enableAccessTk;
  }

  public void setEnableAccessTk(boolean enableAccessTk) {
    this.enableAccessTk = enableAccessTk;
  }

  public String getReadedBc() {
    return readedBc;
  }

  public void setReadedBc(String readedBc) {
    this.readedBc = readedBc;
  }

  public void save() {
    // StaticEntityManagerHolder.getUserRepo().save(this);
  }

  public static Optional<LocalUser> find(long id) {
    // return StaticEntityManagerHolder.getUserRepo().findById(id);
    return null;
  }

  public static void delete(LocalUser user) {
    // StaticEntityManagerHolder.getUserRepo().delete(user);
  }

  public Date getPwdUpdatedAt() {
    return pwdUpdatedAt;
  }

  public void setPwdUpdatedAt(Date pwdUpdatedAt) {
    this.pwdUpdatedAt = pwdUpdatedAt;
  }

  public Date getLockOutAt() {
    return lockOutAt;
  }

  public void setLockOutAt(Date lockOutAt) {
    this.lockOutAt = lockOutAt;
  }

  public void JoinGroup(UserGroup ug) {
    getGroups().add(ug);
    save();
  }

  public void leaveGroup(UserGroup ug) {
    getGroups().remove(ug);
    save();
  }

  public void JoinGroup(List<UserGroup> ugs) {
    getGroups().addAll(ugs);
    save();
  }

  public void leaveGroup(List<UserGroup> ugs) {
    getGroups().removeAll(ugs);
    save();
  }

  public void JoinGroup(Optional<UserGroup> ugOp) {
    JoinGroup(ugOp.get());
  }

  public void leaveGroup(Optional<UserGroup> ugOp) {
    leaveGroup(ugOp.get());
  }

  public void addRole(LocalRole role) {
    getRoles().add(role);
    save();
  }

  public void removeRole(LocalRole role) {
    getRoles().remove(role);
    save();
  }

  public void addRole(List<LocalRole> roles) {
    getRoles().addAll(roles);
    save();
  }

  public void removeRole(List<LocalRole> roles) {
    getRoles().removeAll(roles);
    save();
  }

  public void addPermissions(List<MixDomainPermission> mps) {
    getPermissions().addAll(mps);
    save();
  }

  public boolean hasRole(LocalRole role) {
    return getRoles().contains(role);
  }
}
