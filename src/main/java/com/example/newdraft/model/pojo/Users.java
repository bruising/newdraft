package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
  //主键id
  private  Integer  id;
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
}
