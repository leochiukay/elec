package com.telek.elec.protocal.apdu.client;

import com.telek.elec.protocal.apdu.Request;
import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端断开链接请求
 */
@Data
@Slf4j
public class ClientReleaseRequest extends Client implements Request {

    public ClientReleaseRequest() {
        this.apduSequence = APDUSequence.RELEASE_REQUEST;
    }

    @Override
    public String encodeThisToHex() {
        return super.encodeHexCommon();
    }
}
