package com.telek.elec.protocal.packet;

import lombok.Data;

/**
 * 帧结构
 */
@Data
public class Packet {

    private HeadPacket headPacket;

    private DataPacket dataPacket;

    private TailPacket tailPacket;

}
