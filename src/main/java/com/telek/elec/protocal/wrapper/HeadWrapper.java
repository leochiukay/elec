package com.telek.elec.protocal.wrapper;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.ProtocalConstant;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * Created by PETER on 2015/3/24.
 */
public class HeadWrapper extends Wrapper {

    @Override
    void encode(Packet in, Packet.SA sa, byte[] data, CodecAPDU apdu, int seq) throws EncodeException {
        // 1.设置帧长度域(长度域（2）+控制域（1）+SA头（1）+SA（N）+CA(1)+HCS(2)+DATA(N)+FCS)
        int length = 2 + 1 + 1 + sa.getLength() + 1 + 2 + data.length + 2;
        in.setLength(length);
        // 2.设置帧控制域
        Packet.Control control = new Packet.Control();
        //传输方向位：DIR=0表示此帧是由客户机发出的；DIR=1表示此帧是由服务器发出的
        control.setDir(0);
        //启动标志位：PRM=1表示此帧是由客户机发起的；PRM=0表示此帧是由服务器发起的。
        switch (apdu.getApduSequence()) {
            case LINK_RESPONSE:
                control.setFun(1);
                break;
            case REPORT_RESPONSE:
                control.setFun(3);
                break;
            case CONNECTION_REQUEST:
            case RELEASE_REQUEST:
            case GET_REQUEST:
            case SET_REQUEST:
            case ACTION_REQUEST:
            case PROXY_REQUEST:
                control.setFun(3);
                control.setPrm(1);
                break;
            default:
                throw new EncodeException();
        }
        // 分帧标志位
        if (seq == -1) {
            control.setBlock(0);
        } else {
            control.setBlock(1);
        }
        in.setControl(control);
        // 3.设置帧地址域
        in.setSa(sa);
        in.setCa(ProtocalConstant.CLIENT_ADDRESS);
        // 4.计算帧头校验
        in.calculateHcs();
        if (next != null) {
            next.encode(in, sa, data, apdu, seq);
        }
    }
}
