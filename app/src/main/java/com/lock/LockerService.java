package com.lock;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * 锁屏服务
 */
public class LockerService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerLockerReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "前台服务已销毁,广播已取消", Toast.LENGTH_SHORT).show();
        unregisterLockerReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public static void startService(Context context) {
        try {
            Intent intent = new Intent(context, LockerService.class);
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private LockerReceiver lockerReceiver;

    private void registerLockerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        lockerReceiver = new LockerReceiver();
        registerReceiver(lockerReceiver, filter);
    }

    private void unregisterLockerReceiver() {
        if (lockerReceiver == null) {
            return;
        }
        unregisterReceiver(lockerReceiver);
        lockerReceiver = null;
    }
}
