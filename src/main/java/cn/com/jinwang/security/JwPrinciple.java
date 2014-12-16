package cn.com.jinwang.security;

import java.io.Serializable;

import cn.com.jinwang.domain.LocalUser;


public class JwPrinciple implements Serializable {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  private Long userId;

  private String nickname;

  private String mobile;

  private String email;

  private String figureurl;

  public JwPrinciple(LocalUser user) {
    this.setUserId(user.getId());
    this.nickname = user.getNickname();
    this.mobile = user.getMobile();
    this.email = user.getEmail();
    this.figureurl = user.getFigureurl();
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFigureurl() {
    return figureurl;
  }

  public void setFigureurl(String figureurl) {
    this.figureurl = figureurl;
  }


  public Long getUserId() {
    return userId;
  }


  public void setUserId(Long userId) {
    this.userId = userId;
  }



}
