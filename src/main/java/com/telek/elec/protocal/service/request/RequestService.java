package com.telek.elec.protocal.service.request;

import com.telek.elec.protocal.ProtocalSendHelper;
import com.telek.elec.protocal.apdu.CodecAPDU;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestService {

    /**
     * 发送请求
     * @param apdu
     * @param address
     */
    public void sendRequest(CodecAPDU apdu, String address) {
        try {
            ProtocalSendHelper.send2Service(address, apdu, false);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求发送失败");
        }
    }

}
