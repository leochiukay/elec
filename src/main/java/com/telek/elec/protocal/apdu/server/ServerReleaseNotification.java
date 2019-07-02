package com.telek.elec.protocal.apdu.server;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.Request;
import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端超时断开通知客户端:
 */
@Data
@Slf4j
public class ServerReleaseNotification extends Server implements Request {

    private Calendar linkedTime;

    private Calendar currentTime;


    public ServerReleaseNotification() {
        this.apduSequence = APDUSequence.RELEASE_RESPONSE;
    }

    @Override
    public String encodeThisToHex() {
        return null;
    }
}
