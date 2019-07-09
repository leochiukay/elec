package com.telek.elec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.connection.ConnectionRequest;
import com.telek.elec.protocal.codec.Decoder;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.service.response.ResponseService;

import java.nio.ByteBuffer;

/**
 * @Auther: wll
 * @Date: 2019/7/9 13:56
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws DecodeException {
        String result ="68CC00C3054920040083131058B3820044575344322E3100313831313237312E300031373037303144463632303300000115FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0800080006080000001C200001300821DEE048E9A6BD114D15EE0406E20E8D9D8BE8A0515CC55FCA44C95DF6273F0DB77CF9360313C3C914E2F32300C44F40E4A03D4CCAC00183010E09661B01A49801B336EBDE87CF6BE30075FE5717BF1533BFDA62D1F16BA6F31D87594DFC09F623F1174B42FEAE67418D0CFD5C4A03BC0000EA9616";
        byte[] r = HexBin.decode(result);
        Decoder decoder = new Decoder();
        Packet packet = decoder.decode(ByteBuffer.wrap(r));
        ResponseService responseService = new ResponseService(packet);
        responseService.dealAndResponse();
    }
}
