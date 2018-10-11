package com.zhouwenqi.apihub.server.model;

/**
 * 用户状态
 *
 * Created by zhouwenqi on 2018/9/21.
 */
public enum HubStatus {
    ON(0,"正常"),
    DISABLED(1,"暂时禁用"),
    DELETE(2,"永久删除");
    final int index;
    final String value;
    HubStatus(int index,String value){
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
