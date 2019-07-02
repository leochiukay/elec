package com.telek.elec.protocal.apdu.connection;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端超时断开通知客户端:
 */
@Data
@Slf4j
public class ReleaseNotification extends CodecAPDU implements Release {

    /**
     * 连接时间
     */
    private Calendar linkedTime;
    /**
     * 当前时间
     */
    private Calendar currentTime;


    public ReleaseNotification() {
        this.apduSequence = APDUSequence.RELEASE_NOTIFICATION;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        // 编码时间
        sb.append(EncoderUtils.encodeToDateTimeSHex(linkedTime));
        sb.append(EncoderUtils.encodeToDateTimeSHex(currentTime));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        this.linkedTime = DecoderUtils.decodeDateTimeSHex(hexString.substring(4, 18));
        this.currentTime = DecoderUtils.decodeDateTimeSHex(hexString.substring(18));
    }
}
