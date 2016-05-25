package com.maitaidan.util;

import com.google.common.collect.Maps;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Crytis on 2016/3/13.
 * Just test.
 */
public class HttpUtilTest {

    @Test
    public void testDoPostWithHeadersForCookie() throws Exception {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("mobile", "17091007735");
        Map<String, String> result = HttpUtil.doPostWithHeadersForCookie("http://bao.qunar.com/vip/api/user/captcha", params, null);

        System.out.println(result);
    }

    @Test
    public void testLogin(){
        HashMap<String, String> params = Maps.newHashMap();
        params.put("mobile", "17091007735");
        params.put("captcha", "378669");
        Map<String, String> result = HttpUtil.doPostWithHeadersForCookie("http://bao.qunar.com/vip/api/user/login", params, null);

        System.out.println(result);
    }
}