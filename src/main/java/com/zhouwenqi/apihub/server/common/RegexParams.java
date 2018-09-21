package com.zhouwenqi.apihub.server.common;

/**
 * 正则匹配字符串
 * Created by zhouwenqi on 2018/1/27.
 */
public class RegexParams {
    // 手机号
    public static String REG_MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
    // 电话
    public static String REG_PHONE = "^0[\\d]{2,3}-[\\d]{7,8}$";
    // Email
    public static String REG_EMAIL = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
    // 密码
    public static String REG_PWD = "^[a-zA-Z0-9_-]{6,18}$";
    // INT数值
    public  static String REG_INT = "-?[0-9]+$";
}
