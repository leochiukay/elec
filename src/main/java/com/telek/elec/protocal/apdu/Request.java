package com.telek.elec.protocal.apdu;

/**
 * 请求对象
 */
public interface Request {

    /**
     * 将当前对象封装成十六进制字符串，供请求体使用
     * @return
     */
    String encodeThisToHex();

}
