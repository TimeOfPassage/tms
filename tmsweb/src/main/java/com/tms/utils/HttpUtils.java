package com.tms.utils;

import ch.qos.logback.classic.Logger;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 用于Http请求
 */
public class HttpUtils {

    private static Logger logger = (Logger) LoggerFactory.getLogger(HttpUtils.class);

    private static HttpUtils httpUtils = null;

    private HttpUtils() {
    }

    /**
     * 双重锁检查机制，防止多线程环境下，单例模式失效
     *
     * @return HttpUtils实例
     */
    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * @param sParams 请求参数
     * @param url     请求地址
     * @return 响应文本
     */
    public static String doHttpGet(Map<String, Object> sParams, String url) {
        if (url == null) {
            logger.debug("http url can not be empty!");
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //封装请求参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<String> keys = sParams.keySet();
        for (String key : keys) {
            params.add(new BasicNameValuePair(key, StringUtils.isEmpty(sParams.get(key)) ? "" : sParams.get(key).toString()));
        }
        InputStream in = null;
        CloseableHttpResponse response = null;
        String responseText = "";
        try {
            HttpGet get = new HttpGet(url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)));
            get.setHeader("Content-Type", "application/json;charset=UTF-8");
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            logger.error("HttpGet 请求报错");
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /**
     * @param params 请求参数
     * @param url    请求地址
     * @return 响应文本
     */
    public static String doHttpPost(Map<String, Object> params, String url) {
        if (url == null) {
            logger.debug("http url can not be empty!");
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //15s连接不上 则超时
        httpClient.getConnectionManager().closeIdleConnections(15,TimeUnit.SECONDS);
        HttpPost post = new HttpPost(url);
        HttpResponse response = null;
        try {
            if (params != null) {
                StringEntity entity = new StringEntity(params.toString(),"UTF-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                post.setEntity(entity);
            }
            response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            logger.error("Http Post 请求失败！");
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }

        return null;
    }
}
