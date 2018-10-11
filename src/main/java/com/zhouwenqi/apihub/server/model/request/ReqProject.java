package com.zhouwenqi.apihub.server.model.request;

import com.zhouwenqi.apihub.server.model.HubStatus;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * 入参 - 项目
 * Created by zhouwenqi on 2018/9/29.
 */
public class ReqProject implements Serializable {
    // 项目id
    private String id;
    // 用户id
    private String userId;
    // 项目状态
    private HubStatus status;
    // 项目名称
    private String name;
    // 项目描述
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HubStatus getStatus() {
        return status;
    }

    public void setStatus(HubStatus status) {
        this.status = status;
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
}
