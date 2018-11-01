package com.hauwei.payment.sdk.serve.http;

import android.os.Handler;

import com.hauwei.payment.sdk.serve.http.IHttpCallBack;
import com.hauwei.payment.sdk.serve.http.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp处理程序
 * Created by zhuyinan on 2018/11/1.
 * Contact by 445181052@qq.com
 */
public class OkHttpProcessor implements IHttpProcessor {

    private OkHttpClient mOkHttpClient;

    private Handler mHandler;

    public OkHttpProcessor() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void get(String url, Map<String, String> parameters, final IHttpCallBack callback) {
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError("访问失败！");
                        }
                    });
                }
            }
        });
    }

    @Override
    public void post(String url, Map<String, String> parameters, final IHttpCallBack callback) {
        RequestBody requestBody = appendBody(parameters);
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("User-Agent", "a")
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {

                }
            }
        });
    }

    /**
     * 生产请求体
     * @param parameters
     * @return
     */
    private RequestBody appendBody(Map<String, String> parameters) {
        FormBody.Builder body = new FormBody.Builder();
        if (parameters == null || parameters.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            body.add(entry.getKey(), entry.getValue());
        }
        return body.build();
    }
}
