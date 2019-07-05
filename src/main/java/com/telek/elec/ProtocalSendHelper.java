package com.telek.elec;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.cache.TempCache;
import com.telek.elec.netty.NettyStarter;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.connection.ConnectionRequest;
import com.telek.elec.protocal.apdu.link.LinkResponse;
import com.telek.elec.protocal.codec.Encoder;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProtocalSendHelper {
    @Autowired
    private NettyStarter nettyStarter;

    public void sendLinkResponse(String address) throws Exception {
        LinkResponse linkResponse = new LinkResponse();
        linkResponse.setResult(1);
        linkResponse.setReceivedTime(Calendar.getInstance());
        linkResponse.setRequestTime(Calendar.getInstance());
        linkResponse.setReceivedTime(Calendar.getInstance());
        send2Service(address, linkResponse, false);
    }

    public void sendConnectionRequest(String address) {
        ConnectionRequest connectionRequest = new ConnectionRequest();

    }

    private void send2Service(String address, CodecAPDU apdu, boolean sync) throws Exception {
        String encodeStr = apdu.encode();
        Packet.SA sa = TempCache.serviceAddressInfo.get(address);
        //TODO 暂时不考虑分帧问题
        // 预连接与应用连接不需要考虑分帧的问题
//        if (apdu.getApduSequence() == APDUSequence.LINK_REQUEST
//                || apdu.getApduSequence() == APDUSequence.CONNECTION_REQUEST
//                || apdu.getApduSequence() == APDUSequence.RELEASE_REQUEST) {
        Encoder encoder = new Encoder();
        byte[] datas = encoder.encode(sa, HexBin.decode(encodeStr), apdu, -1);
        if (sync) {
            //TODO
            // nettyStarter.syncSend(address, 0, datas);
        } else {
            nettyStarter.send(address, datas);
        }
//        }
        // 根据服务器的接收帧最大尺寸
//        ConnectionResponse connectionResponse = TempCache.connectionInfo.get(address);
    }


}
