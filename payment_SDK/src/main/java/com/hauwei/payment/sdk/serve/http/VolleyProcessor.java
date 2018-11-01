package com.hauwei.payment.sdk.serve.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Volley处理程序
 * Created by zhuyinan on 2018/11/1.
 * Contact by 445181052@qq.com
 */
public class VolleyProcessor implements IHttpProcessor {

    RequestQueue mQueue;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, final Map<String, String> parameters, final IHttpCallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onError(volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return parameters;
            }
        };
        mQueue.add(stringRequest);
        mQueue.start();
    }

    @Override
    public void post(String url, final Map<String, String> parameters, final IHttpCallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onError(volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return parameters;
            }
        };
        mQueue.add(stringRequest);
        mQueue.start();
    }
}
