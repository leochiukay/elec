package com.telek.elec.protocal;

import org.apache.tomcat.util.buf.HexUtils;

import com.telek.elec.cache.TempCache;
import com.telek.elec.netty.NettyStarter;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.codec.Encoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtocalSendHelper {

    public static Packet send2Service(String address, CodecAPDU apdu, boolean sync) throws Exception {
        String encodeStr = apdu.encode();
        Packet.SA sa = TempCache.serviceAddressInfo.get(address);
        //TODO 暂时不考虑分帧问题
        // 预连接与应用连接不需要考虑分帧的问题
//        if (apdu.getApduSequence() == APDUSequence.LINK_REQUEST
//                || apdu.getApduSequence() == APDUSequence.CONNECTION_REQUEST
//                || apdu.getApduSequence() == APDUSequence.RELEASE_REQUEST) {
        Encoder encoder = new Encoder();
        byte[] datas = encoder.encode(sa, HexUtils.fromHexString(encodeStr), apdu, -1);
        if (sync) {
            return NettyStarter.syncSend(address, datas);
        } else {
            NettyStarter.send(address, datas);
            return null;
        }
//        }
        // 根据服务器的接收帧最大尺寸
//        ConnectionResponse connectionResponse = TempCache.connectionInfo.get(address);
    }


}
