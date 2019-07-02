package com.telek.elec.protocal.apdu.server;

import com.telek.elec.protocal.apdu.Response;
import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端断开链接响应:
 */
@Data
@Slf4j
public class ServerReleaseResponse extends Server implements Response {

    /**
     * 是否成功-1字节
     */
    private int success;


    public ServerReleaseResponse() {
        this.apduSequence = APDUSequence.RELEASE_RESPONSE;
    }

    public void decodeHexToThis(String hexString) {
        log.info(this.getClass().getSimpleName() + "-应用层断开连接响应apdu-" + hexString);
        if (hexString.length() != 6) {
            log.info(this.getClass().getSimpleName() + "-应用层断开连接apdu长度不符合-" + hexString);
        }
        String id = hexString.substring(0, 2);
        if (Integer.parseInt(id, 16) != APDUSequence.RELEASE_RESPONSE.getId()) {
            log.info(this.getClass().getSimpleName() + "-应用层断开连接apdu id错误-" + hexString);
        }

        this.piid = Integer.parseInt(hexString.substring(2, 4), 16);
        this.success = Integer.parseInt(hexString.substring(4), 16);
    }
}
