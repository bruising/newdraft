package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
  //主键
  private Integer userId;
  //昵称
  private String userName;
  //密码
  private String userPassword;
  //电话
  private String userPhone;
  //邮箱
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
