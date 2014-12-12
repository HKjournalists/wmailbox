package cn.com.jinwang.domain;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.shiro.authz.permission.WildcardPermission;

import cn.com.jinwang.interf.CanCopyProterties;

import com.google.common.base.Optional;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

/**
 * Entity implementation class for Entity:
 * AclPermission,其实这个不需要实现permission，因为可以直接在localuserrealm中产生wildcardpermission，就像role一样。
 * 
 */
@Entity
@Table(name = "MIX_DOMAIN_PERMISSION", uniqueConstraints = @UniqueConstraint(columnNames = {
    "simpleName", "actions", "targets"}))
public class MixDomainPermission extends BaseDomain
    implements
      CanCopyProterties<MixDomainPermission> {

  public static class NamedQueryNames {
    public static final String BY_ROLES = "findAclPermissionsByRoles";
    public static final String BY_ALL_FIELDS = "MixDomainPermissionByAllFields";
  }

  private static final long serialVersionUID = 1L;

  public MixDomainPermission() {}

  public MixDomainPermission(String simpleName, String actions, String targets) {
    super();
    this.simpleName = simpleName;
    this.actions = actions;
    this.targets = targets;
  }


  @Basic(optional = false)
  @Expose
  
  
  private String simpleName;

  @Basic(optional = false)
  @Expose
  
  
  private String actions;

  @Basic(optional = false)
  @Expose
  
  private String targets;

  public String getSimpleName() {
    return simpleName;
  }

  public void setSimpleName(String simpleName) {
    this.simpleName = simpleName;
  }

  public String getActions() {
    return actions;
  }

  public void setActions(String actions) {
    this.actions = actions;
  }

  public String getTargets() {
    return targets;
  }

  public void setTargets(String targets) {
    this.targets = targets;
  }

  public WildcardPermission toWildcardPermission() {
    StringBuilder sb = new StringBuilder(getSimpleName());
    sb.append(":");
    sb.append(getActions());
    sb.append(":");
    sb.append(getTargets());
    return new WildcardPermission(sb.toString());
  }

  @Override
  public JsonObject toJsonObject(String jsonv) {
    return toJsonObject();
  }

  public static Optional<MixDomainPermission> byAllFields(String simpleName, String actions,
      String targets) {
//    return StaticEntityManagerHolder.getMpRepo().byAllFields(simpleName, actions, targets);
	  return null;
  }

  public static Optional<MixDomainPermission> find(long id) {
//    return StaticEntityManagerHolder.getMpRepo().findById(id);
	  return null;
  }

  public static void delete(MixDomainPermission mp) {
//    List<LocalUser> lus = StaticEntityManagerHolder.getUserRepo().hasThisPermission(mp);
//    for (LocalUser lu : lus) {
//      lu.getPermissions().remove(mp);
//      StaticEntityManagerHolder.getUserRepo().save(lu);
//    }
//
//    List<UserGroup> ugs = StaticEntityManagerHolder.getUgRepo().hasThisPermission(mp);
//    for (UserGroup ug : ugs) {
//      ug.getPermissions().remove(mp);
//      StaticEntityManagerHolder.getUgRepo().save(ug);
//    }
//
//    StaticEntityManagerHolder.getMpRepo().delete(mp);
  }


  public void save() {
//    StaticEntityManagerHolder.getMpRepo().save(this);
  }

  @Override
  public void copyProperties(MixDomainPermission clientBean, String putString) {}

  //
  // //copy from org.apache.shiro.authz.permission.WildcardPermission
  // //TODO - JavaDoc methods
  //
  // /*--------------------------------------------
  // | C O N S T A N T S |
  // ============================================*/
  // protected static final String WILDCARD_TOKEN = "*";
  // protected static final String PART_DIVIDER_TOKEN = ":";
  // protected static final String SUBPART_DIVIDER_TOKEN = ",";
  // protected static final boolean DEFAULT_CASE_SENSITIVE = false;
  //
  // /*--------------------------------------------
  // | I N S T A N C E V A R I A B L E S |
  // ============================================*/
  // @Transient
  // private List<Set<String>> parts;
  //
  // /*--------------------------------------------
  // | C O N S T R U C T O R S |
  // ============================================*/
  // /**
  // * Default no-arg constructor for subclasses only - end-user developers instantiating Permission
  // instances must
  // * provide a wildcard string at a minimum, since Permission instances are immutable once
  // instantiated.
  // * <p/>
  // * Note that the WildcardPermission class is very robust and typically subclasses are not
  // necessary unless you
  // * wish to create type-safe Permission objects that would be used in your application, such as
  // perhaps a
  // * {@code UserPermission}, {@code SystemPermission}, {@code PrinterPermission}, etc. If you want
  // such type-safe
  // * permission usage, consider subclassing the {@link DomainPermission DomainPermission} class
  // for your needs.
  // */
  // protected WildcardPermission() {
  // }
  //
  // public WildcardPermission(String wildcardString) {
  // this(wildcardString, DEFAULT_CASE_SENSITIVE);
  // }
  //
  // public WildcardPermission(String wildcardString, boolean caseSensitive) {
  // setParts(wildcardString, caseSensitive);
  // }

  // protected void setParts(String wildcardString) {
  // setParts(wildcardString, DEFAULT_CASE_SENSITIVE);
  // }
  //
  // protected void setParts(String wildcardString, boolean caseSensitive) {
  // if (wildcardString == null || wildcardString.trim().length() == 0) {
  // throw new
  // IllegalArgumentException("Wildcard string cannot be null or empty. Make sure permission strings are properly formatted.");
  // }
  //
  // wildcardString = wildcardString.trim();
  //
  // List<String> parts = CollectionUtils.asList(wildcardString.split(PART_DIVIDER_TOKEN));
  //
  // this.parts = new ArrayList<Set<String>>();
  // for (String part : parts) {
  // Set<String> subparts = CollectionUtils.asSet(part.split(SUBPART_DIVIDER_TOKEN));
  // if (!caseSensitive) {
  // subparts = lowercase(subparts);
  // }
  // if (subparts.isEmpty()) {
  // throw new
  // IllegalArgumentException("Wildcard string cannot contain parts with only dividers. Make sure permission strings are properly formatted.");
  // }
  // this.parts.add(subparts);
  // }
  //
  // if (this.parts.isEmpty()) {
  // throw new
  // IllegalArgumentException("Wildcard string cannot contain only dividers. Make sure permission strings are properly formatted.");
  // }
  // }

  // private Set<String> lowercase(Set<String> subparts) {
  // Set<String> lowerCasedSubparts = new LinkedHashSet<String>(subparts.size());
  // for (String subpart : subparts) {
  // lowerCasedSubparts.add(subpart.toLowerCase());
  // }
  // return lowerCasedSubparts;
  // }

  // /*--------------------------------------------
  // | A C C E S S O R S / M O D I F I E R S |
  // ============================================*/
  // protected List<Set<String>> getParts() {
  // return this.parts;
  // }
  //
  // /*--------------------------------------------
  // | M E T H O D S |
  // ============================================*/
  //
  // public boolean implies(Permission p) {
  // // By default only supports comparisons with other WildcardPermissions
  // if (!(p instanceof MixDomainPermission)) {
  // return false;
  // }
  //
  // MixDomainPermission wp = (MixDomainPermission) p;
  //
  // List<Set<String>> otherParts = wp.getParts();
  //
  // int i = 0;
  // for (Set<String> otherPart : otherParts) {
  // // If this permission has less parts than the other permission, everything after the number of
  // parts contained
  // // in this permission is automatically implied, so return true
  // if (getParts().size() - 1 < i) {
  // return true;
  // } else {
  // Set<String> part = getParts().get(i);
  // if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)) {
  // return false;
  // }
  // i++;
  // }
  // }
  //
  // // If this permission has more parts than the other parts, only imply it if all of the other
  // parts are wildcards
  // for (; i < getParts().size(); i++) {
  // Set<String> part = getParts().get(i);
  // if (!part.contains(WILDCARD_TOKEN)) {
  // return false;
  // }
  // }
  //
  // return true;
  // }

  // public String toString() {
  // StringBuilder buffer = new StringBuilder();
  // for (Set<String> part : parts) {
  // if (buffer.length() > 0) {
  // buffer.append(":");
  // }
  // buffer.append(part);
  // }
  // return buffer.toString();
  // }
  //
  // public boolean equals(Object o) {
  // if (o instanceof MixDomainPermission) {
  // MixDomainPermission wp = (MixDomainPermission) o;
  // return parts.equals(wp.parts);
  // }
  // return false;
  // }
  //
  // public int hashCode() {
  // if(parts == null){
  // encodeParts();
  // }
  // return parts.hashCode();
  // }

}
