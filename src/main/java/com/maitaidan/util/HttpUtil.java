package com.maitaidan.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Crytis on 2016/3/13.
 * Just test.
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * post请求
     *
     * @param url     地址
     * @param params  参数
     * @param headers http头
     * @return 返回
     */
    @Deprecated
    public static String doPostWithHeaders(String url, Map<String, String> params, Map<String, String> headers) {

        return null;
    }

    /**
     * post请求获取返回的cookie
     * todo 超时时间
     *
     * @param url     地址
     * @param params  参数
     * @param headers http头
     * @return 返回一个map，暂时有2个 key：result和Set-Cookie
     */
    public static Map<String, String> doPostWithHeadersForCookie(String url, Map<String, String> params,
                                                                 Map<String, String> headers) {
        if (StringUtils.isBlank(url)) {
            logger.error("url是空");
            return null;
        }
        HashMap<String, String> result = Maps.newHashMap();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        ArrayList<NameValuePair> paramList = Lists.newArrayList();

        try {
            if (params != null) {
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "UTF-8");

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey().trim(), entry.getValue().trim());
                }
            }

            httpPost.setEntity(urlEncodedFormEntity);
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            Header[] cookieHeaders = httpResponse.getHeaders("Set-Cookie");
            StringBuilder setCookieSB = new StringBuilder();
            if (cookieHeaders.length > 0) {
                for (Header setCookieHeader : cookieHeaders) {
                    setCookieSB.append(setCookieHeader.getValue());
                }
            }
            result.put("Set-Cookie", setCookieSB.toString());
            result.put("result", EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException e) {
            logger.error("post error", e);
        }
        return result;
    }


    /**
     * Get 请求带有cookie
     *
     * @param url
     * @param cookie
     * @return
     */
    public static String doGetWithCookie(String url, Map<String, String> params, String cookie) {
        try {
            if (StringUtils.isBlank(url)) {
                return null;
            }


            if (params != null && !params.isEmpty()) {
                StringBuilder sb = new StringBuilder(url).append("?");
                for (String key : params.keySet()) {
                    sb.append(key).append("=").append(params.get(key)).append("&");
                }

                url = sb.deleteCharAt(sb.lastIndexOf("&")).toString();
            }
            logger.info("请求的url:{}", url);
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
            if (StringUtils.isNotBlank(cookie)) {
                httpGet.addHeader(new BasicHeader("Cookie", cookie));
            }
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 7Star/1.45.0.415 Safari/537.36");
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            return StringEscapeUtils.unescapeJava(EntityUtils.toString(closeableHttpResponse.getEntity()));
        } catch (IOException e) {
            logger.error("get error", e);
            return null;
        }

    }

    /**
     * Post 请求带有cookie
     *
     * @param url
     * @param cookie
     * @return
     */
    public static String doPostWithCookie(String url, Map<String, String> params, String cookie) {
        if (StringUtils.isBlank(url)) {
            logger.error("url是空");
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        ArrayList<NameValuePair> paramList = Lists.newArrayList();

        try {
            if (params != null) {
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "UTF-8");

            httpPost.setEntity(urlEncodedFormEntity);
            httpPost.addHeader("Cookie", cookie);
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);

            return StringEscapeUtils.unescapeJava(EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException e) {
            logger.error("post error", e);
            return null;
        }
    }
}
