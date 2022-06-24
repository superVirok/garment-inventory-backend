package com.wu.pojo;

import org.springframework.stereotype.Component;

@Component("Authorization")
public class Authorization {
    private Integer authenId;//权限id
    private Integer userId;//用户id
    private Integer userAuthorization; //用户管理权限 1/2 无/有
    private Integer goodsAuthorization; //商品管理权限 1/2 无/有
    private Integer wareAuthorization;//出入库管理权限 1/2 无/有
    private User user;//对应用户

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public  Authorization(){

    }

    public Authorization(Integer authenId, Integer userId, Integer userAuthorization, Integer goodsAuthorization, Integer wareAuthorization, User user) {
        this.authenId = authenId;
        this.userId = userId;
        this.userAuthorization = userAuthorization;
        this.goodsAuthorization = goodsAuthorization;
        this.wareAuthorization = wareAuthorization;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "authenId=" + authenId +
                ", userId=" + userId +
                ", userAuthorization=" + userAuthorization +
                ", goodsAuthorization=" + goodsAuthorization +
                ", wareAuthorization=" + wareAuthorization +
                ", user=" + user +
                '}';
    }

    public Integer getAuthenId() {
        return authenId;
    }

    public void setAuthenId(Integer authenId) {
        this.authenId = authenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAuthorization() {
        return userAuthorization;
    }

    public void setUserAuthorization(Integer userAuthorization) {
        this.userAuthorization = userAuthorization;
    }

    public Integer getGoodsAuthorization() {
        return goodsAuthorization;
    }

    public void setGoodsAuthorization(Integer goodsAuthorization) {
        this.goodsAuthorization = goodsAuthorization;
    }

    public Integer getWareAuthorization() {
        return wareAuthorization;
    }

    public void setWareAuthorization(Integer wareAuthorization) {
        this.wareAuthorization = wareAuthorization;
    }
}
