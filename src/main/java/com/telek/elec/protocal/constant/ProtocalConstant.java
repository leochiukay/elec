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

}
