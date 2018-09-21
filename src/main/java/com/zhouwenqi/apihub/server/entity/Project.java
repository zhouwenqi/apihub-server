package com.zhouwenqi.apihub.server.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouwenqi.apihub.server.common.IdGetSerialize;
import com.zhouwenqi.apihub.server.common.IdSetSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 * Created by zhouwenqi on 2018/6/7.
 */
@Document(collection = "project")
public class Project implements Serializable {

    @Id
    private ObjectId id;

    // 项目名称
    private String name;

    // 项目描述
    private String description;

    // 项目图标
    private String ico;

    // 项目状态
    private Integer status;

    // 创建人id
    private ObjectId userId;

    // 创建时间
    private Date createDate;

    // 修改时间
    private Date editDate;

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getId() {
        return id;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getUserId() {
        return userId;
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
