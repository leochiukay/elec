package com.telek.elec.protocal.service.request;

import com.telek.elec.cache.TempCache;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.ProtocalSendHelper;
import com.telek.elec.protocal.apdu.CodecAPDU;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestService {

    /**
     * 发送请求
     *
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

    /**
     * 发送请求
     *
     * @param apdu
     * @param address
     */
    public Packet syncSendRequest(CodecAPDU apdu, String address) {
        try {
            return ProtocalSendHelper.send2Service(address, apdu, true);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求发送失败");
        }
        return null;
    }

    private void offerCommunicationQueue(CodecAPDU apdu, String address) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("发送到服务端:").append(address).append(",");

        TempCache.communicationQueue.offer(sbf.toString());
    }
}
