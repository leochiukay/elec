package com.telek.elec.protocal.apdu;

/**
 * 响应对象
 */
public interface Response {

    /**
     * 将二进制字符串解析为对象:供响应体使用
     * @param hexString
     */
    void decodeHexToThis(String hexString);

}
