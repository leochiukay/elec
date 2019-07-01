package com.telek.elec.util;

public class ByteUtils {

    /**
     * 获取int的低八位
     * @param i
     * @return
     */
    public static byte getIntLower8Bit(int i) {
        int i1 = i & 0xff;
        return (byte) i1;
    }

}
