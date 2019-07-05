package com.telek.elec.protocal.unwrapper;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.exeception.DecodeException;

import java.nio.ByteBuffer;

/**
 * @Auther: wll
 * @Date: 2019/7/3 10:13
 * @Description:
 */
public class TailUnwrapper extends Unwrapper {
    @Override
    void decode(ByteBuffer in, Packet out) throws DecodeException {
        byte[] fcs = new byte[2];
        in.get(fcs);
        out.setFcs(fcs);
    }
}
