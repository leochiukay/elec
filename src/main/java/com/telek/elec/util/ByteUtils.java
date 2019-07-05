package com.telek.elec.util;

public class ByteUtils {

    /**
     * 获取int的低八位
     *
     * @param i
     * @return
     */
    public static byte getIntLower8Bit(int i) {
        int i1 = i & 0xff;
        return (byte) i1;
    }

    /**
     * @param data
     * @return
     * @Modifier:Administrator
     * @Date：2017年8月23日上午9:49:08
     * @Describe:reverse byte数组高低位反转
     */
    public static byte[] reverse(byte[] data) {
        if (data != null && data.length > 0) {
            byte[] temp = new byte[data.length];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[data.length - 1 - i];
            }
            return temp;
        }
        return new byte[0];
    }
}
