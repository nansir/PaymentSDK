package com.hauwei.payment.sdk.serve.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Http 回调转换泛型
 * Created by zhuyinan on 2018/11/1.
 * Contact by 445181052@qq.com
 */
public abstract class DataCallback<T> implements IHttpCallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Type type = getType(this);
        T obj = gson.fromJson(result, type);
        onSuccess(obj);
    }

    @Override
    public void onError(String result) {
        onFailure(result);
    }

    //失败时候回调
    public abstract void onFailure(String msg);

    private static Type getType(Object obj) {
        Type types = obj.getClass().getGenericSuperclass();
        Type[] type_arr = ((ParameterizedType) types).getActualTypeArguments();
        return type_arr[0];
    }

    //成功时候回调
    public abstract void onSuccess(T t);
}
