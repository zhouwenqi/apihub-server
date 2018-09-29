package com.zhouwenqi.apihub.server.model.request;

import java.io.Serializable;

/**
 * 入参 - 注册用户
 * Created by zhouwenqi on 2018/9/25.
 */
public class ReqRegister implements Serializable {
    // 帐号
    private String uid;
    // 密码
    private String pwd;
    // 昵称
    private String nickName;
    // email
    private String email;
    // 联系方式
    private String phone;
    // 头像
    private String photo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
