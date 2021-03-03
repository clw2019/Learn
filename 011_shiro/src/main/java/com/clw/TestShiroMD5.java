package com.clw;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestShiroMD5 {

    public static void main(String[] args) {
        // 创建一个md5算法,默认加密 1 次
        // setBytes 没有加密
        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes("123".getBytes());
        // 转成 16 进制
        String s = md5Hash.toHex();
        System.out.println(s);

        // 构造函数 进过MD5加密了
        Md5Hash md5Hash1 = new Md5Hash("123");
        System.out.println(md5Hash1.toHex());

        // MD5 + salt
        Md5Hash md5Hash2 = new Md5Hash("123", "x0*7ps");
        System.out.println(md5Hash2.toHex());

        // MD5 + salt + hash散列
        Md5Hash md5Hash3 = new Md5Hash("123", "x0*7ps", 1024);
        System.out.println(md5Hash3.toHex());

    }
}
