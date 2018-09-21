package com.zhouwenqi.apihub.server.model;

/**
 * Created by zhouwenqi on 2018/9/21.
 */
public enum RoleLevel {
    MANAGER(0,"管理员"),
    BUILD(1,"后端开发"),
    FONTEND(2,"前端开发"),
    TEST(3,"测试人员");
    final int index;
    final String value;
    RoleLevel(int index,String value){
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
