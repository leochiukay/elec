package com.telek.elec.protocal.apdu.link;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.LinkType;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端发起预链接请求:
 * 01 00 00 00 B4 07 E0 05 13 04 08 05 00 00 A4
 */
@Data
@Slf4j
public class LinkRequest extends CodecAPDU implements Link {
    /**
     * 类型-1字节
     * 登录     （0），
     * 心跳     （1），
     * 退出登录 （2）
     */
    private LinkType linkType;
    /**
     * 心跳周期-2字节
     */
    private int heartBeat;
    /**
     * 请求时间-年-2字节
     * year          long-unsigned，
     * month         unsigned，
     * day_of_month  unsigned，
     * day_of_week   unsigned，
     * hour          unsigned，
     * minute        unsigned，
     * second        unsigned，
     * milliseconds  long-unsigned
     */
    private Calendar requestTime;

    public LinkRequest() {
        this.apduSequence = APDUSequence.LINK_REQUEST;
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (this.heartBeat > 0xffff) {
            this.heartBeat = 0xffff;
        }
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        // 请求类型  ENUMERATED
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.linkType.getCode()), 2));
        // 心跳周期  long-unsigned
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.heartBeat), 4));
        // 请求时间
        sb.append(EncoderUtils.encodeToDateTimeHex(requestTime));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        int linkType = Integer.parseInt(hexString.substring(4, 6), 16);
        for (LinkType value : LinkType.values()) {
            int code = value.getCode();
            if (code == linkType) {
                this.linkType = value;
                break;
            }
        }
        this.heartBeat = Integer.parseInt(hexString.substring(6, 10), 16);
        this.requestTime = DecoderUtils.decodeDateTimeHex(hexString.substring(10));
    }
}
