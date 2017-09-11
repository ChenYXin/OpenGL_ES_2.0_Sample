package com.donkor.demo.opengl;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * @author Donkor
 * 关于我：
 * CSDN博客：http://blog.csdn.net/donkor_
 * Android开发交流QQ群：537891203
 * 邮箱：donkor@yeah.net
 */ 
public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断设备是否支持OpenGl ES 2.0
        if (IsSupported()) {

            // 先建GLSurfaceView实例
            glSurfaceView = new GLSurfaceView(this);

            // 创建渲染器实例
            MyRenderer mRenderer = new MyRenderer();

            // 设置渲染器
            glSurfaceView.setRenderer(mRenderer);

            // 显示SurfaceView
            setContentView(glSurfaceView);
        }
    }

    private boolean IsSupported() {

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x2000;

        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));

        supportsEs2 = supportsEs2 || isEmulator;
        return supportsEs2;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (glSurfaceView != null) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glSurfaceView != null) {
            glSurfaceView.onResume();
        }
    }
}
