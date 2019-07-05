package com.telek.elec.protocal.wrapper;


import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * Created by PETER on 2015/3/25.
 */
public class DataWrapper extends Wrapper {
    @Override
    void encode(Packet in, Packet.SA sa, byte[] data, CodecAPDU apdu, int seq) throws EncodeException {
        in.setData(data);
        if (next != null) {
            next.encode(in, sa, data, apdu, seq);
        }
    }
}
