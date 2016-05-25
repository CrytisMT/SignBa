package com.maitaidan.util;

import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by Crytis on 2016/3/14.
 * 23:05
 */
public class LoginUtilTest {

    @Test
    public void testEncryptUsername() throws Exception {
        System.out.println(LoginUtil.encryptUsername("crytis"));

    }

    @Test
    public void testGenerateCookie() throws Exception {
        System.out.println(LoginUtil.generateCookie("crytis"+new Date().toString()));
    }
}