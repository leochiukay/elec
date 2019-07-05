package com.telek.elec.protocal.wrapper;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;

/**
 * @Auther: wll
 * @Date: 2019/7/2 23:56
 * @Description:
 */
public class TailWrapper extends Wrapper {
    @Override
    void encode(Packet in, Packet.SA sa, byte[] data, CodecAPDU apdu, int seq) {
        in.calculateFcs();
    }
}
