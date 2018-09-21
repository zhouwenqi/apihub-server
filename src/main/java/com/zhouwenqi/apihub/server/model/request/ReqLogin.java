package com.zhouwenqi.apihub.server.model.request;

import com.zhouwenqi.apihub.server.model.RequestSource;

import java.io.Serializable;

/**
 * 入参 - 登录请求
 * Created by zhouwenqi on 2018/9/21.
 */
public class ReqLogin implements Serializable {
    // 帐号
    private String uid;
    // 密码
    private String pwd;
    // 请求来源
    private RequestSource requestSource;
    // 时间戳
    private Long timestamp;

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

    public RequestSource getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(RequestSource requestSource) {
        this.requestSource = requestSource;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
