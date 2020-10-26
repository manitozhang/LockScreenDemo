package com.lock;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LockerService.startService(this);
    }
}
