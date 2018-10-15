package com.zhouwenqi.apihub.server.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouwenqi.apihub.server.common.IdGetSerialize;
import com.zhouwenqi.apihub.server.common.IdSetSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类
 * Created by zhouwenqi on 2018/10/12.
 */
@Document(collection = "category")
public class Category implements Serializable {
    @Id
    private ObjectId id;
    // 分类名称
    @Indexed
    private String name;
    // 分类描述
    private String description;
    // 主分类id
    @Indexed
    private ObjectId parentId;
    // 项目id
    @Indexed
    private ObjectId projectId;

    // 创建时间
    @Indexed
    private Date createDate;

    // 修改时间
    @Indexed
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

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getParentId() {
        return parentId;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getProjectId() {
        return projectId;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setProjectId(ObjectId projectId) {
        this.projectId = projectId;
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
