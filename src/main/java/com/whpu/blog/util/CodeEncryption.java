package com.whpu.blog.util;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @ClassName CodeEncryption
 * @Description: 数据库密码加密工具
 * @Author jy
 * @Date 2020/4/28
 **/
public class CodeEncryption {
    public static void main(String[] args) {
        try {
            String password = "yourPassword";
            String[] arr = ConfigTools.genKeyPair(512);

            // System.out.println("privateKey:" + arr[0]);
            System.out.println("publicKey:" + arr[1]);
            System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
