package com.telek.elec.protocal.service.request;

import com.telek.elec.ProtocalSendHelper;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.util.SpringBeanContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestService {

    ProtocalSendHelper sendHelper = SpringBeanContext.getBean(ProtocalSendHelper.class);

    /**
     * 发送请求
     * @param apdu
     * @param address
     */
    public void sendRequest(CodecAPDU apdu, String address) {
        try {
            sendHelper.send2Service(address, apdu, false);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求发送失败");
        }
    }

}
