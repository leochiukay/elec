package com.telek.elec.protocal.packet;

import com.telek.elec.protocal.constant.ProtocalConstant;

import lombok.Data;

/**
 * 尾帧
 */
@Data
public class TailPacket {

    /**
     * 帧校验-2字节
     */
    private int fcs;

    private final byte end = ProtocalConstant.END_FRAM;

}
