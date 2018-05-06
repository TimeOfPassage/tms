package com.tms.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PostToJSONUtils {
    public static <T> T toJSON(HttpServletRequest request, Class<T> clazz){
        InputStream in = null;
        BufferedReader reader = null;
        String message = "";
        try {
            in = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                message += tmp;
            }
            return JSONObject.parseObject(message, (Class<T>) clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
