package com.zhouwenqi.apihub.server.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouwenqi.apihub.server.common.IdGetSerialize;
import com.zhouwenqi.apihub.server.common.IdSetSerialize;
import com.zhouwenqi.apihub.server.model.HubStatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * Created by zhouwenqi on 2018/6/5.
 */
@JsonIgnoreProperties({"pwd"})
@Document(collection = "user")
public class User implements Serializable{
    @Id
    private ObjectId id;

    // 用户名
    @Indexed
    private String uid;

    // 密码
    private String pwd;

    // 昵称
    @Indexed
    private String nickName;

    // 头像
    private String photo;

    // 电子邮箱
    @Indexed
    private String email;

    // 联系电话
    @Indexed
    private String phone;

    // 用户状态
    @Indexed
    private HubStatus status;

    // 是否是系统管理员
    @Indexed
    private Boolean isAdmin;

    // 创建时间
    private Date createDate;

    // 编辑时间
    private Date editDate;

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getId() {
        return id;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setId(ObjectId id) {
        this.id = id;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public HubStatus getStatus() {
        return status;
    }

    public void setStatus(HubStatus status) {
        this.status = status;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
