package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


public class Users {
  //主键id
  private  Integer  id;
  //用户id
  private Integer userId;
  //昵称
  @NotBlank
  private String userName;
  //密码
  private String userPassword;
  //电话
  @NotBlank
  private String userPhone;
  //邮箱
  @NotBlank
  private String userEmail;
  //头像地址
  private String userPhoto;
  //微信凭证
  private String userWechart;
  //角色
  private Integer userRole;
  //状态
  private Integer userStatus;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPhoto() {
    return userPhoto;
  }

  public void setUserPhoto(String userPhoto) {
    this.userPhoto = userPhoto;
  }

  public String getUserWechart() {
    return userWechart;
  }

  public void setUserWechart(String userWechart) {
    this.userWechart = userWechart;
  }

  public Integer getUserRole() {
    return userRole;
  }

  public void setUserRole(Integer userRole) {
    this.userRole = userRole;
  }

  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  @Override
  public String toString() {
    return "Users{" +
            "id=" + id +
            ", userId=" + userId +
            ", userName='" + userName + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userPhone='" + userPhone + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userPhoto='" + userPhoto + '\'' +
            ", userWechart='" + userWechart + '\'' +
            ", userRole=" + userRole +
            ", userStatus=" + userStatus +
            '}';
  }
}
