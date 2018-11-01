package com.hauwei.payment.sdk.serve.http;

/**
 * Http回调
 * Created by zhuyinan on 2018/11/1.
 * Contact by 445181052@qq.com
 */
public interface IHttpCallBack {
    //成功时候回调
    void onSuccess(String result);
    //失败时候回调
    void onError(String result);
}
