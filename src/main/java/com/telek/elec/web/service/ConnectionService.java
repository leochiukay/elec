package com.telek.elec.web.service;

import com.telek.elec.cache.TempCache;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.connection.ConnectionRequest;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {
    @Autowired
    private RequestService requestService;

    public void connectionRequest(String address) {
        if (!TempCache.connectionInfo.containsKey(address)) {
            CodecAPDU apdu = new ConnectionRequest();
            Packet packet = requestService.syncSendRequest(apdu, address);
            new ResponseService(packet).dealAndResponse();
        }
    }
}
