package com.zhouwenqi.apihub.server.model;

/**
 * 请求参数body类型
 * Created by zhouwenqi on 2018/10/16.
 */
public enum  RequestBodyType {
    FormData(0,"form-data"),
    XwwwFormUrlencoded(1,"x-www-form-urlencoded"),
    Raw(2,"raw");
    final int index;
    final String value;
    RequestBodyType(int index,String value){
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
