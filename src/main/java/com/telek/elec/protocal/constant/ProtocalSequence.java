package com.telek.elec.protocal.constant;

/**
 * 各种请求响应序列号
 */
public interface ProtocalSequence {

    /**
     * 预链接请求
     */
    byte LINK_REQUEST = 0x1;
    /**
     * 预链接响应
     */
    int LINK_RESPONSE = 0x81;

    /**
     * 应用层连接请求
     */
    byte CONNECTION_REQUEST = 0x2;
    /**
     * 应用层连接响应
     */
    int CONNECTION_RESPONSE = 0x82;

    /**
     * 应用层连接请求
     */
    byte RELEASE_REQUEST = 0x3;
    /**
     * 应用层连接响应
     */
    int RELEASE_RESPONSE = 0x83;


}
