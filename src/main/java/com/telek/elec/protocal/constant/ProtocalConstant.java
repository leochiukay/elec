package com.telek.elec.protocal.constant;

/**
 * 协议常量
 */
public interface ProtocalConstant {

    /**
     * 前导帧.
     */
    int PREFIX_FRAM = 0xFE;
    /**
     * 帧起始符.
     */
    byte START_FRAM = 0x68;
    /**
     * 编码末尾byte.
     */
    byte END_FRAM = 0x16;

    /**
     * 预链接-登录
     */
    byte LINK_LOGIN = 0x0;
    /**
     * 预链接-登出
     */
    byte LINK_LOGOUT = 0x2;
    /**
     * 预链接-心跳
     */
    byte LINK_HEART_BEAT = 0x1;
}
