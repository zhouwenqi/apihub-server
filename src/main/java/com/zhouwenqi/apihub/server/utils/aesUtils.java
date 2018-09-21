package com.zhouwenqi.apihub.server.utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * aes加密
 * Created by zhouwenqi on 2018/2/7.
 */
public class aesUtils {
    private static final String PASSWORD_CRYPT_KEY = "api.hub";
    /**
     * 将字节数组转为二进制字符串
     * @param bytes 字节数组
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes,int radix){
        return new BigInteger(1,bytes).toString(radix);
    }

    /**
     * 将字节数组进行 base 64 编码
     * @param bytes 字节数组
     * @return 编码后的字符串
     */
    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 对字符串进行base 64解码成字节数组
     * @param text 字符串
     * @return 解码后的字节数组
     * @throws Exception
     */
    public static byte[] base64Decode(String text) throws Exception{
        return new BASE64Decoder().decodeBuffer(text);
    }

    /**
     * 获取字节数组的md5值
     * @param bytes 字节数组
     * @return md5值
     * @throws Exception
     */
    public static byte[] md5(byte[] bytes) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        return messageDigest.digest();
    }

    /**
     * 获取字符串的md5值
     * @param text 字符串
     * @return md5加密后的字符数组
     * @throws Exception
     */
    public static byte[] md5(String text) throws Exception{
        return null == text ? null :md5(text.getBytes());
    }

    /**
     * 对字符串md5加密后再进行base64加密
     * @param text 等加密的字符串
     * @return 双重加密后的字符串
     * @throws Exception
     */
    public static String md5Encrypt(String text) throws Exception{
        return null == text ? null : base64Encode(md5(text));
    }

    /**
     * AES 加密
     * @param content 等加密的内容
     * @return 加密后的字节数组
     * @throws Exception
     */
    public static byte[] encryptToBytes(String content) throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(PASSWORD_CRYPT_KEY.getBytes());
        keyGenerator.init(128,secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(keyGenerator.generateKey().getEncoded(),"AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES 加密
     * @param content 等加密的内容
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String content) throws Exception{
        String result = base64Encode(encryptToBytes(content));
        if(result.indexOf("+")!=-1){
            result = result.replace("+","-");
        }
        return result;
    }

    /**
     * AES 解密
     * @param bytes 等解密的字节数组
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByBytes(byte[] bytes) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(PASSWORD_CRYPT_KEY.getBytes());
        keyGenerator.init(128,secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(keyGenerator.generateKey().getEncoded(),"AES"));
        byte[] decrtyptByte = cipher.doFinal(bytes);
        return new String(decrtyptByte);
    }

    /**
     * AES 解码
     * @param text 待解码的字符串
     * @return 解码后的字符串
     * @throws Exception
     */
    public static String decrypt(String text) throws Exception{
        if(text.contains("-")){
            text = text.replace("-","+");
        }
        return null==text ? null : decryptByBytes(base64Decode(text));
    }
}
