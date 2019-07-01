package com.telek.elec.protocal.packet;

import com.telek.elec.protocal.constant.ProtocalConstant;

import lombok.Data;

/**
 * 头帧
 */
@Data
public class HeadPacket {

    /**
     * 帧头
     */
    private final byte starter = ProtocalConstant.START_FRAM;
    /**
     * 长度域-2字节
     */
    private int length;
    /**
     * 控制域
     */
    private int control;
    /**
     * 地址域-sa
     */
    private int sa;
    /**
     * 地址域-ca
     */
    private int ca;
    /**
     * 帧头校验-2字节
     */
    private int hcs;

}
