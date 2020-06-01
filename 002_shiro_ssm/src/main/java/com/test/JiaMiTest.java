package com.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.UUID;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/1 18:32
 */
public class JiaMiTest {
    public static void main(String[] args) {
        String str1 = "123";
        System.out.println(new Md5Hash(str1).toBase64());
        System.out.println(new Md5Hash(str1).toString());
        System.out.println("============");
        String salt = UUID.randomUUID().toString();
        System.out.println(salt);
        String salt2 = UUID.randomUUID().toString();
        System.out.println(salt2);
        String s1 = new Md5Hash(str1, salt, 10000).toBase64();
        String s2 = new Sha256Hash(str1, salt2, 10000).toBase64();
        System.out.println(s1);
        System.out.println(s2);
    }
}
