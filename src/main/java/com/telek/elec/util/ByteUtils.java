package com.telek.elec.util;

public class ByteUtils {

    /**
     * 将16进制字符串组装成byte数组，每两个字符为一个字节,
     * 如果为奇数字符串，后面的一位舍去
     */
    public static byte[] hexStrToBytes(String hexStr) {
        int length = hexStr.length();
        byte[] bs = new byte[length / 2];
        int index = 0;
        int i = 0;
        while (index + 2 <= length) {
            String hex = hexStr.substring(index, index += 2);
            System.out.println(hex);
            bs[i++] = getIntLower8Bit(Integer.parseInt(hex, 16));
        }
        System.out.println(bs.length);
        return bs;
    }

    /**
     * 将byte数组转成16进制字符串，每个字节占用2个字符长度
     */
    public static String bytesToHexStr(byte[] bs) {
        if (bs == null || bs.length == 0) {
            return  "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            int i = b & 0xff;
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(i), 2));
        }
        return sb.toString();
    }

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
