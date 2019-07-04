package com.telek.elec.protocal.apdu.connection;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端断开链接请求
 */
@Data
@Slf4j
public class ReleaseRequest extends CodecAPDU implements Release {

    public ReleaseRequest() {
        this.apduSequence = APDUSequence.RELEASE_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        return "";
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        return;
    }
}
