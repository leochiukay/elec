package com.telek.elec.protocal.unwrapper;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.constant.ProtocalConstant;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.util.ByteUtils;

import java.nio.ByteBuffer;

/**
 * @Auther: wll
 * @Date: 2019/7/3 00:15
 * @Description:
 */
public class HeadUnwrapper extends Unwrapper {
    @Override
    void decode(ByteBuffer in, Packet out) throws DecodeException {
        in.clear();
        if (in.get() != ProtocalConstant.START_FRAM) {
            throw new DecodeException();
        }
        // 长度
        byte[] length = new byte[2];
        in.get(length);
        out.setLength(((length[0] & 0xff) << 8) + length[1]);
        // 控制域功能码
        byte controlByte = in.get();
        Packet.Control control = new Packet.Control();
        control.setDir((controlByte >> 7) & 0x01);
        control.setPrm((controlByte >> 6) & 0x01);
        control.setBlock((controlByte >> 5) & 0x01);
        control.setFun(controlByte & 0x07);
        out.setControl(control);
        // 服务器地址SA
        byte sa0 = in.get();
        Packet.SA sa = new Packet.SA();
        sa.setType((sa0 >> 6) & 0x03);
        sa.setLogicAdd((sa0 >> 4) & 0x03);
        sa.setLength((sa0 & 0x0F) + 1);
        byte[] address = new byte[sa.getLength()];
        in.get(address);
        sa.setAddress(HexBin.encode(ByteUtils.reverse(address)));
        out.setSa(sa);
        // 客户机地址ca
        int ca = in.get();
        out.setCa(ca);
        //HCS
        byte[] hcs = new byte[2];
        in.get(hcs);
        out.setHcs(hcs);
        if (next != null) {
            next.decode(in, out);
        }
    }
}
