package com.hauwei.payment.sdk.admin;

import android.content.Context;

/**
 * Created by zhuyinan on 2018/10/31.
 * Contact by 445181052@qq.com
 */
public class PaymentTask {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;
    }
}
