package com.zhouwenqi.apihub.server.model.request;

import java.io.Serializable;

/**
 * 入参 - 验证
 * Created by zhouwenqi on 2018/9/27.
 */
public class ReqVerify implements Serializable {
    // email或帐号
    private String email;
    // 验证码
    private String verifyCode;
    // 验证码类型(0:注册新帐号,1:帐号验证)
    private Integer codeType;
    // 密码
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
