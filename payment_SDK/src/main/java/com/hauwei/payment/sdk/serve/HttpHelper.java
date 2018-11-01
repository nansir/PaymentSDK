package com.hauwei.payment.sdk.serve;

import com.hauwei.payment.sdk.serve.http.DataCallback;
import com.hauwei.payment.sdk.serve.http.IHttpProcessor;

import java.util.Map;

/**
 * 网络助手
 * Created by zhuyinan on 2018/11/1.
 * Contact by 445181052@qq.com
 */
public class HttpHelper {

    //处理器
    private static IHttpProcessor iHttpProcessor;
    //网络实例
    private static HttpHelper instance;

    private HttpHelper() {

    }

    /**
     * 获取示例单例模式
     * @return
     */
    public static HttpHelper getInstance() {
        HttpHelper httpHelper = instance;
        if (httpHelper == null) {
            synchronized (HttpHelper.class) {
                if (httpHelper == null) {
                    httpHelper = new HttpHelper();
                    instance = httpHelper;
                }
            }
        }
        return instance;
    }

    /**
     * 初始网络处理器
     * @param processor
     */
    public void init(IHttpProcessor processor) {
        iHttpProcessor = processor;
    }

    /**
     * GET请求方法
     * @param url
     * @param parameters
     * @param callback
     */
    public void get(String url, Map<String, String> parameters, DataCallback callback) {
        if (iHttpProcessor == null) {
            throw new IllegalArgumentException("HttpProcessor was not initialized");
        } else {
            iHttpProcessor.get(url, parameters, callback);
        }
    }

    /**
     * POST请求方法
     * @param url
     * @param parameters
     * @param callback
     */
    public void post(String url, Map<String, String> parameters, DataCallback callback) {
        if (iHttpProcessor == null) {
            throw new IllegalArgumentException("HttpProcessor was not initialized");
        } else {
            iHttpProcessor.post(url, parameters, callback);
        }
    }
}
