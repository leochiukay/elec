package com.telek.elec.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telek.elec.protocal.service.request.RequestService;

/**
 * 读取操作
 */
@Service
public class ReadService {
    @Autowired
    private RequestService requestService;

    public void mbusProperties(String address) {
        /*GetRequestNormal request = new GetRequestNormal();
        request.setOad(CommonOADFactory.mbus());
        request.setPiid(1);
        Packet packet = requestService.syncSendRequest(request, address);
        new ResponseService(packet).dealAndResponse();*/
    }


    /**
     * 读取发电时间
     *
     * @return
     */
    public long generationTime() {
        return 0;
    }


}
