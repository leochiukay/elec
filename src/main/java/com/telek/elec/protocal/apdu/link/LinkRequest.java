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
 * 预连接
 * H.1.1　登录
 * 01 00 00 00 B4 07 E0 05 13 04 08 05 00 00 A4
 * 01 —— [1] LINK-Request
 * 00 —— PIID-ACD
 * 00 —— 请求类型：建立连接（0）
 * 00 B4 —— 心跳周期：180s
 * 07 E0 05 13 04 08 05 00 00 A4 —— 请求时间date_time：2016-05-19 08：05：00：0164
 * CS CS —— 帧校验
 * 16 —— 结束符
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
     * 请求时间-date_time-20字节
     */
    private Calendar requestTime;

    public LinkRequest() {
        this.apduSequence = APDUSequence.LINK_REQUEST;
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
        int index = this.decodeHexExcludeCommonBeginIndex;
        int linkType = Integer.parseInt(hexString.substring(index, index += 2), 16);
        for (LinkType value : LinkType.values()) {
            int code = value.getCode();
            if (code == linkType) {
                this.linkType = value;
                break;
            }
        }
        this.heartBeat = Integer.parseInt(hexString.substring(index, index += 4), 16);
        this.requestTime = DecoderUtils.decodeDateTimeHex(hexString.substring(index, index + 20));
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (linkType == null) {
            throw new EncodeException("linkType is null");
        }
        if (heartBeat > 0xffff) {
            heartBeat = 0xffff;
        }
        if (requestTime == null) {
            requestTime = Calendar.getInstance();
        }
    }
}
