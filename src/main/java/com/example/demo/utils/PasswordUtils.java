package com.example.demo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils {

    private static String algorithmName = "md5";

    private static int hashIterations = 2;

    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(salt), hashIterations).toHex();
    }

    public static void main(String[] args) {
        System.out.println(PasswordUtils.encryptPassword("123456", "admin"));
    }
}
