package com.donkor.demo.opengl;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

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

    public class MyRenderer implements GLSurfaceView.Renderer {

        // Surface创建的时候调用
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // 设置清屏颜色为红色
            gl.glClearColor(1f, 0f, 0f, 0f);

        }

        // Surface改变的的时候调用
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // 设置窗口大小
            gl.glViewport(0, 0, width, height);

        }

        // 在Surface上绘制的时候调用
        @Override
        public void onDrawFrame(GL10 gl) {

            // 清除屏幕
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        }
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