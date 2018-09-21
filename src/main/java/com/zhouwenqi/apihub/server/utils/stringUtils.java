package com.zhouwenqi.apihub.server.utils;

import java.util.Random;

/**
 * 字符串常用方法
 * Created by zhouwenqi on 2018/6/6.
 */
public class stringUtils {

    /**
     * 获取随机验证码
     * @param length 验证码长度
     * @return
     */
    public static String getVerifyCode(int length){
        StringBuffer stringBuffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<length;i++){
            sb.append(stringBuffer.charAt(random.nextInt(stringBuffer.length())));
        }
        return sb.toString();
    }
}
