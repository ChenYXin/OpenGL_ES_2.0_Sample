package com.donkor.demo.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Donkor on 2017/9/4.
 */

public class ToolUtils {
    /**
     * @param vertexes float 数组
     * @return 获取浮点形缓冲数据
     */
    public static FloatBuffer getFloatBuffer(float[] vertexes) {
        FloatBuffer buffer;
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexes.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        buffer = vbb.asFloatBuffer();
        //写入数组
        buffer.put(vertexes);
        //设置默认的读取位置
        buffer.position(0);
        return buffer;
    }
}