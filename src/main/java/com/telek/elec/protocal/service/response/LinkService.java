package com.telek.elec.protocal.service.response;

import java.util.Calendar;

import com.telek.elec.cache.TempCache;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.ProtocalSendHelper;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.connection.ConnectionRequest;
import com.telek.elec.protocal.apdu.link.LinkRequest;
import com.telek.elec.protocal.apdu.link.LinkResponse;
import com.telek.elec.protocal.constant.LinkType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkService implements IResponseService {

    private Packet packet;
    private CodecAPDU apdu;

    @Override
    public void dealAndResponse() {
        LinkRequest linkRequest = (LinkRequest) this.apdu;
        LinkType linkType = linkRequest.getLinkType();
        String address = packet.getSa().getAddress();
        switch (linkType) {
            case LOGOUT:
                TempCache.linkedInfo.remove(address);
                TempCache.connectionInfo.remove(address);
                break;
            case LOGIN:
            case HEART_BEAT:
                log.error("address:" + address);

                TempCache.linkedInfo.put(address, linkRequest);
                break;
            default:
                log.error("link type 有误");
                return;
        }
        LinkResponse linkResponse = new LinkResponse();
        linkResponse.setResult(0x80);
        linkResponse.setRequestTime(linkRequest.getRequestTime());
        linkResponse.setReceivedTime(linkRequest.getRequestTime());
        linkResponse.setResponseTime(Calendar.getInstance());
        linkResponse.setPiid(0);

        try {
            ProtocalSendHelper.send2Service(packet.getSa().getAddress(), linkResponse, false);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("响应发送失败");
        }

        // 如果为登录，先发送建立应用链接请求
        if (LinkType.LOGIN.equals(linkType)) {
            // new Thread(new AutoConnection(packet)).start();
        }

    }
}

class AutoConnection implements Runnable {

    private Packet packet;

    public AutoConnection(Packet packet) {
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);

            ConnectionRequest connectionRequest = getConnectionRequest();

            ProtocalSendHelper.send2Service(packet.getSa().getAddress(), connectionRequest, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConnectionRequest getConnectionRequest() {
        ConnectionRequest connectionRequest = new ConnectionRequest();
        connectionRequest.setExpectVersion(22);
        connectionRequest.setProtocolConformance("FFFFFFFFC0000000");
        connectionRequest.setFunctionConformance("0001FFFE000000000000000000000000");
        connectionRequest.setSendMaxSize(2048);
        connectionRequest.setReceiveMaxSize(2048);
        connectionRequest.setWindowMaxSize(1);
        connectionRequest.setMaxApduSize(8000);
        connectionRequest.setExpectOverTime(7200);
        connectionRequest.setAuthObject(3);
        connectionRequest.setTimeStamp(32);
        connectionRequest.setPiid(54);
        return connectionRequest;
    }
}
