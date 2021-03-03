package com.clw.util;

import java.util.Random;

/**
 * @Author: clw
 * @Description: 生成随机盐工具类
 * @Date: 2021/2/27 17:01
 */
public class SaltUtils {

    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println(salt);
    }
}
