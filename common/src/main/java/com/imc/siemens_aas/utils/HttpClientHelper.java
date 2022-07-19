package com.imc.siemens_aas.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * HttpClient工具
 */
@Slf4j
public class HttpClientHelper {
    /**
     * 无参get请求
     */
    public static String doGetByNotParam(String url) {

        // 1 获取httpClient客户端
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 2 创建请求
        HttpGet httpGet = new HttpGet(url);

        // 3 发送请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);

            // 4 响应状态码 响应信息
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("响应状态码为: {} ", statusCode);

            // 响应信息
            HttpEntity httpEntity = httpResponse.getEntity();
            log.info("响应内容为: {} ", EntityUtils.toString(httpEntity));

            //释放资源
            EntityUtils.consume(httpEntity);
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.info("请求异常, 错误信息为: {} ", e.getMessage());
            return null;
        }
    }

    /**
     * 有参GET请求 添加请求配置
     * @param param 格式 "key=1234545&id=123123123
     */
    public static String doGetByParam(String url, String param) {

        //  获取httpClient客户端
        HttpClient httpClient = HttpClientBuilder.create().build();
        //  创建GET请求
        HttpGet httpGet = new HttpGet(url + "?" + param);

        // 配置请求信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000) // 连接超时时间
                .setConnectionRequestTimeout(5000) //请求超时时间
                .setSocketTimeout(5000) // 读写超时时间
                .setRedirectsEnabled(true) // 是否重定向 默认true开启
                .build();
        httpGet.setConfig(requestConfig);

        //  发送请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);

            // 4 响应状态码 响应信息
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("响应状态码为: {} ", statusCode);

            // 响应信息
            HttpEntity httpEntity = httpResponse.getEntity();
            log.info("响应内容为: {} ", EntityUtils.toString(httpEntity));

            //释放资源
            EntityUtils.consume(httpEntity);
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.info("请求异常, 错误信息为: {} ", e.getMessage());
            return null;
        }
    }

    /**
     * 无参POST请求
     */
    public static String doPostByNotParam(String url) {

        //  获取httpClient客户端
        HttpClient httpClient = HttpClientBuilder.create().build();
        //  创建请求
        HttpPost httpPost = new HttpPost(url);

        //  发送请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);

            //  响应状态码 响应信息
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("响应状态码为: {} ", statusCode);

            // 响应信息
            HttpEntity httpEntity = httpResponse.getEntity();
            log.info("响应内容为: {} ", EntityUtils.toString(httpEntity));

            //释放资源
            EntityUtils.consume(httpEntity);
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            log.info("请求异常, 错误信息为: {} ", e.getMessage());
            return null;
        }
    }

    /**
     * 有参POST请求
     */
    public static String doPostByParam(String url, String reqBody, int timeout) {
        // post请求
        HttpClient httpClient = null;
        HttpPost postMethod = null;
        HttpResponse response = null;
        String responseContent = null;

        try {
            // 获取http客户端
            httpClient = HttpClients.createDefault();
            postMethod = new HttpPost(url);

            // 配置请求信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000) // 连接超时时间
//                    .setConnectionRequestTimeout(5000) //请求超时时间
//                    .setSocketTimeout(5000) // 读写超时时间
                    .setConnectionRequestTimeout(timeout) //请求超时时间
                    .setSocketTimeout(timeout) // 读写超时时间
                    .setRedirectsEnabled(true) // 是否重定向 默认true开启
                    .build();
            postMethod.setConfig(requestConfig);

            // 设置请求头
            postMethod.addHeader("Content-Type", "application/json;charset=utf8");
            // 封装请求体
            postMethod
                    .setEntity(new StringEntity(reqBody, Charset.forName("UTF-8")));

            // 发送请求
            response = httpClient.execute(postMethod);

            // 获取响应结果
            int statusCode = response.getStatusLine().getStatusCode();

            // 响应对象
            HttpEntity httpEntity = response.getEntity();

            // 响应的字符串
            responseContent = EntityUtils.toString(httpEntity, "UTF-8");

            //释放资源
            EntityUtils.consume(httpEntity);
            return responseContent;
        } catch (IOException e) {
            log.info("请求异常, 错误信息为: {} ", e.getMessage());
            return null;
        }
    }
}
