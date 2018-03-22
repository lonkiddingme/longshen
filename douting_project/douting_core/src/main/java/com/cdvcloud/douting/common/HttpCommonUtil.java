package com.cdvcloud.douting.common;

import com.cdvcloud.rms.util.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 封装post请求
 *
 * @author Administrator
 */
@Component
public class HttpCommonUtil {
    private static final Logger logger = Logger.getLogger(HttpCommonUtil.class);
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送post请求
     *
     * @param url    请求地址
     * @param entity 请求参数
     * @return
     */
    public String doPost(String url, Map<String, Object> entity) {
        if (url == null || "".equals(url)) {
            logger.error("请求地址有误！" + url);
            return null;
        }
        if (null == entity || entity.isEmpty()) {
            logger.error("请求参数为有误！" + entity);
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(JSONUtils.toJson(entity), headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        formEntity = null;
        return result;
    }

    
    /**
     * 发送get请求
     *
     * @param url    请求地址
     * @param entity 请求参数
     * @return
     */
    public String doGet(String url, Map<String, Object> entity) {
        if (url == null || "".equals(url)) {
            logger.error("请求地址有误！" + url);
            return null;
        }
        if (null == entity || entity.isEmpty()) {
            logger.error("请求参数为有误！" + entity);
            return null;
        }
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        HttpEntity<String> formEntity = new HttpEntity<String>(JSONUtils.toJson(entity), headers);
        String result = restTemplate.getForObject(url, String.class);
//        formEntity = null;
        return result;
    }

    
    
    
    /**
     * 发送post请求
     *
     * @param url
     * @param jsonStr
     * @return 返回的字符串
     */
    public String doPost(String url, String jsonStr) {
        if (url == null || "".equals(url)) {
            logger.error("请求地址有误！" + url);
            return null;
        }
        if (null == jsonStr || jsonStr.isEmpty()) {
            logger.error("请求参数为有误！" + jsonStr);
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        String voiceDna = restTemplate.postForObject(url, formEntity, String.class);
        return voiceDna;
    }
}
