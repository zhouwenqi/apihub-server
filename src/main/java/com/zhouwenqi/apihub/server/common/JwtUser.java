package com.zhouwenqi.apihub.server.common;

import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * Created by zhouwenqi on 2018/6/5.
 */
public class JwtUser implements Serializable {
    // 用户名
    private String uid;
    // 用户id
    private String id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
