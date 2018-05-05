package com.tms.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * @param sParams 请求参数
     * @param url 请求地址
     * @return 响应文本
     */
    public static String doHttp(Map<String,Object> sParams, String url) {
        //封装请求参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<String> keys = sParams.keySet();
        for(String key : keys) {
            params.add(new BasicNameValuePair(key, StringUtils.isEmpty(sParams.get(key)) ? "" : sParams.get(key).toString()));
        }
        InputStream in = null;
        CloseableHttpResponse response = null;
        String responseText = "";
        try {
            get = new HttpGet(url+"?"+EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)));
            get.setHeader("Content-Type","application/json;charset=UTF-8");
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            if( entity != null) {
                in = entity.getContent();
                //转换为字节输入流
                String tmp = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(in, Consts.UTF_8));
                while((tmp = br.readLine()) != null){
                    responseText += tmp;
                }
                return responseText;
            }
        } catch (IOException e) {
            System.out.println("HttpGet 请求报错");
            e.printStackTrace();
        }finally{
            //关闭输入流，释放资源
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //消耗实体内容
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
