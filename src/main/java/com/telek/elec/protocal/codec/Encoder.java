package com.telek.elec.protocal.codec;


import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.ProtocalConstant;
import com.telek.elec.protocal.wrapper.WrapperChain;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by PETER on 2015/3/14.
 */
public class Encoder {
    // 编码链
    private WrapperChain wrapperChain = new WrapperChain();

    public byte[] encode(Packet.SA sa, byte[] data, CodecAPDU apdu, int seq) throws Exception {
        Packet packet = new Packet();
        wrapperChain.encode(packet, sa, data, apdu, seq);
        byte[] datas = packet.getBaos().toByteArray();
        byte[] result = new byte[datas.length + 2];
        result[0] = ProtocalConstant.START_FRAM;
        System.arraycopy(datas, 0, result, 1, datas.length);
        result[result.length - 1] = ProtocalConstant.END_FRAM;
        return result;
    }
}
