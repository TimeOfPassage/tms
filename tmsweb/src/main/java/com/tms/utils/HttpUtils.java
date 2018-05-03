package com.tms.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 用于Http请求
 */
public class HttpUtils {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static HttpGet get = null;

    private static HttpUtils httpUtils = null;

    private HttpUtils(){}

    /**
     *  双重锁检查机制，防止多线程环境下，单例模式失效
     * @return HttpUtils实例
     */
    public static HttpUtils getInstance() {
        if( httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * @param params 请求参数
     * @param url 请求地址
     * @return 响应文本
     */
    public static String doHttp(Map<String,Object> params, String url) {
        get = new HttpGet(url);
        get.setHeader("Content-Type","application/json;charset=UTF-8");
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String respTxt = EntityUtils.toString(entity,"UTF-8");
            return respTxt.toString();
        } catch (IOException e) {
            System.out.println("HttpGet 请求报错");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param params 请求参数
     * @param url 请求地址
     * @param encoding 响应编码
     * @return 响应文本
     */
    public static String doHttp(Map<String,Object> params, String url,String encoding) {
        get = new HttpGet(url);
        get.setHeader("Content-Type","application/json;charset=UTF-8");
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String respTxt = EntityUtils.toString(entity,encoding);
            return respTxt.toString();
        } catch (IOException e) {
            System.out.println("HttpGet 请求报错");
            e.printStackTrace();
        }
        return null;
    }
}
