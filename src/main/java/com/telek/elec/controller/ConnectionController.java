package com.telek.elec.controller;

import com.telek.elec.protocal.ProtocalSendHelper;
import com.telek.elec.protocal.apdu.connection.ConnectionRequest;
import com.telek.elec.protocal.apdu.connection.ReleaseRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wll
 * @Date: 2019/7/9 14:27
 * @Description:
 */
@RestController
@RequestMapping("/test/connect")
public class ConnectionController {
    @PostMapping("/connect")
    public Object connect(String address) throws Exception {
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
        ProtocalSendHelper.send2Service(address, connectionRequest, false);
        return "OK";
    }

    @PostMapping("/release")
    public Object release(String address) throws Exception {
        ReleaseRequest releaseRequest = new ReleaseRequest();
        releaseRequest.setPiid(00);
        ProtocalSendHelper.send2Service(address, releaseRequest, false);
        return "OK";
    }
}
