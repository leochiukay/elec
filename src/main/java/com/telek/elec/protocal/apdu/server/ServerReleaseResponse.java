package com.telek.elec.protocal.apdu.server;

import com.telek.elec.protocal.constant.ProtocalSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端断开链接响应:
 */
@Data
@Slf4j
public class ServerReleaseResponse extends Server {
    
    private int success;


    public ServerReleaseResponse() {
        this.id = ProtocalSequence.RELEASE_RESPONSE;
    }

    public void decodeByteStr(String byteStr) {
        log.info(this.getClass().getSimpleName() + "-应用层断开连接响应apdu-" + byteStr);
        if (byteStr.length() != 6) {
            log.info(this.getClass().getSimpleName() + "-应用层断开连接apdu长度不符合-" + byteStr);
        }
        String id = byteStr.substring(0, 2);
        if (Integer.parseInt(id, 16) != ProtocalSequence.RELEASE_RESPONSE) {
            log.info(this.getClass().getSimpleName() + "-应用层断开连接apdu id错误-" + byteStr);
        }

        this.piid = Integer.parseInt(byteStr.substring(2, 4), 16);
        this.success = Integer.parseInt(byteStr.substring(4), 16);
    }
}
