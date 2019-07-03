package com.telek.elec.protocal.apdu.connection;

import com.telek.elec.protocal.apdu.CommonCodecAPDU;
import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端断开链接请求
 */
@Data
@Slf4j
public class ReleaseRequest extends CommonCodecAPDU implements Release {

    public ReleaseRequest() {
        this.apduSequence = APDUSequence.RELEASE_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        return null;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        return;
    }
}
