package com.zhouwenqi.apihub.server.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 * Created by zhouwenqi on 2018/9/26.
 */
@Document(collection = "userVerify")
public class UserVerify implements Serializable {
    @Id
    private ObjectId id;
    // 用户id
    private ObjectId userId;
    // Email
    @Indexed
    private String email;
    // 验证码
    @Indexed
    private String code;
    // 验证码类型(0:注册新帐号,1:帐号验证)
    private Integer codeType;
    // 到期时间
    @Indexed
    private Date expireDate;
    // 创建时间
    private Date createDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
