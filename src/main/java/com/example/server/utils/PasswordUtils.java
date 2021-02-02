package com.example.server.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author hanbin
 */
@Component
public class PasswordUtils {

    /**
     * 加密密码，生成加密后字符串
     * @param password 明文密码
     * @param salt 加密盐
     * @return string
     */
    public static String encryption(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    /**
     * 生成加密后的密码
     * @param password 用户输入密码
     * @return map
     */
    public static Map<String, String> generalPassword(String password) {
        String salt = getRandomString(6);
        String md5 = encryption(password, salt);
        Map<String, String> result = new HashMap<>(3);
        result.put("password", md5);
        result.put("salt", salt);
        return result;
    }

    /**
     * 验证用户密码是否正确
     * @param password 密码
     * @param salt 加密盐
     * @param md5 用户数据密码
     * @return true/false
     */
    public static Boolean verify(String password, String salt, String md5) {
        String newPass = encryption(password, salt);
        return md5.equals(newPass);
    }

    /**
     * length用户要求产生字符串的长度
     * @param length 长度
     * @return 返回
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


}
