package com.zhouwenqi.apihub.server.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouwenqi.apihub.server.common.IdGetSerialize;
import com.zhouwenqi.apihub.server.common.IdSetSerialize;
import com.zhouwenqi.apihub.server.model.RoleLevel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 成员
 * Created by zhouwenqi on 2018/6/7.
 */
@Document(collection = "member")
public class Member  implements Serializable {
    @Id
    private ObjectId id;
    // 项目id
    @Indexed
    private ObjectId projectId;

    // 用户id
    @Indexed
    private ObjectId userId;

    // 角色
    private RoleLevel roleLevel;

    // 积称
    @Indexed
    private String memberName;

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

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getProjectId() {
        return projectId;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setProjectId(ObjectId projectId) {
        this.projectId = projectId;
    }

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getUserId() {
        return userId;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public RoleLevel getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(RoleLevel roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
