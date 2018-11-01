package com.hauwei.payment.sdk.serve.http;

import java.util.Map;

/**
 * 网络处理器
 * Created by zhuyinan on 2018/11/1.
 * Contact by 445181052@qq.com
 */
public interface IHttpProcessor {
    //get请求方法
    void get(String url, Map<String, String> parameters, IHttpCallBack callback);
    //post请求方法
    void post(String url, Map<String, String> parameters, IHttpCallBack callback);
}
