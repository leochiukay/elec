package com.telek.elec.protocal.unwrapper;

import com.telek.elec.protocal.Packet;

import java.nio.ByteBuffer;

/**
 * Created by PETER on 2015/3/26.
 */
public class DataUnwrapper extends Unwrapper {

    @Override
    void decode(ByteBuffer in, Packet out) {
        int dataLength = out.getLength() - 2 - 1 - 1 - out.getSa().getLength() - 1 - 2 - 2;
        byte[] dataBytes = new byte[dataLength];
        in.get(dataBytes);
        out.setData(dataBytes);
    }
}
