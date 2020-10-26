package com.lock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.lock.swipbackhelper.SwipeBackHelper;
import com.lock.swipbackhelper.SwipeListener;

/**
 * 锁屏页面
 */
public class LockScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLockerWindow(getWindow());
        setContentView(R.layout.activity_lock_screen);
        SwipeBackHelper.onCreate(this);
        initView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    private void initView() {
        ImmersionBar.with(LockScreenActivity.this).hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();
        ImmersionBar.showStatusBar(getWindow());
        SwipeBackHelper.getCurrentPage(this)//get current instance
                .setSwipeBackEnable(true)
                .setSwipeEdge(200)
                .setSwipeEdgePercent(0.5f)
                .setSwipeSensitivity(0.5f)
                .setScrimColor(Color.TRANSPARENT)
                .setClosePercent(0.8f)
                .setSwipeRelateEnable(false)
                .setSwipeRelateOffset(500)
                .setDisallowInterceptTouchEvent(true)
                .addListener(new SwipeListener() {
                    @Override
                    public void onScroll(float percent, int px) {
                    }

                    @Override
                    public void onEdgeTouch() {
                    }

                    @Override
                    public void onScrollToClose() {
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * 显示在锁屏上面
     * @param window
     */
    private void setLockerWindow(Window window) {
        WindowManager.LayoutParams lp = window.getAttributes();
        if (Build.VERSION.SDK_INT > 18) {
            lp.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
        window.setAttributes(lp);
        window.getDecorView().setSystemUiVisibility(0x0);

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }

    public static void startActivity(Context context) {
        Intent screenIntent = getIntent(context);
        context.startActivity(screenIntent);
    }


    @NonNull
    private static Intent getIntent(Context context) {
        Intent screenIntent = new Intent();
        screenIntent.setClass(context, LockScreenActivity.class);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        screenIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        return screenIntent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
