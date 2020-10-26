package com.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * 锁屏广播接收
 */
public class LockerReceiver extends BroadcastReceiver {

    private static final String TAG = "LockReceiver";

    public LockerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            Log.e(TAG, "onReceive---" + action);
            switch (action) {
                case Intent.ACTION_POWER_CONNECTED:
                case Intent.ACTION_SCREEN_ON:
                case Intent.ACTION_SCREEN_OFF:
                    LockScreenActivity.startActivity(context);
            }
        }
    }
}