package com.wu.pojo;/*
 * Created by Virok on 2022/5/31
 */

import org.springframework.stereotype.Component;

@Component("User")

public class User {
    private Integer userId; //用户id
    private String userAccount;//用户账号
    private String userName;//用户姓名
    private String userPassword; //用户密码
    private String userPhone;//用户电话
    private String userEmail; //用户邮箱
    private int userRole;//用户角色，0代表管理员,1普通用户

    public User() {

    }

    public User(Integer userId, String userAccount, String userName, String userPassword, String userPhone, String userEmail, int userRole) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userAccount=" + userAccount +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
