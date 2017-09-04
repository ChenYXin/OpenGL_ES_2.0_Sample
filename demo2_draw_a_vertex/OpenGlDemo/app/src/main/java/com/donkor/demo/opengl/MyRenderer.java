package com.donkor.demo.opengl;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Donkor on 2017/9/4.
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    //顶点数组
    private float[] mArrayVertex = { 0f, 0f, 0f };

    // 缓冲区
    private FloatBuffer mBuffer;

    MyRenderer() {
        //获取浮点形缓冲数据
        mBuffer = ToolUtils.getFloatBuffer(mArrayVertex);
    }

    // Surface创建的时候调用
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 设置清屏颜色为黑色（rgba）
        gl.glClearColor(0f, 0f, 0f, 0f);
    }

    // Surface改变的的时候调用
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置OpenGL场景的大小
        gl.glViewport(width / 4, width / 2, width / 2, height / 2);
    }

    // 在Surface上绘制的时候调用
    @Override
    public void onDrawFrame(GL10 gl) {

        // 清除屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // 允许设置顶点 // GL10.GL_VERTEX_ARRAY顶点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // 设置顶点
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mBuffer);

        //设置点的颜色为红色
        gl.glColor4f(1f, 0f, 0f, 0f);

        //设置点的大小
        gl.glPointSize(100f);

        // 绘制点
        gl.glDrawArrays(GL10.GL_POINTS, 0, 1);

        // 禁止顶点设置
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}