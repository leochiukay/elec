package com.telek.elec.protocal.apdu.link;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端预链接响应:
 * 81 00 80 07 E0 05 13 04 08 05 00 00 89 07 E0 05 13 04 08 05 01 02 5F 07 E0 05 13 04 08 05 02 02 DA
 */
@Data
@Slf4j
public class LinkResponse extends CodecAPDU implements Link {
    /**
     * 结果-1字节
     * 时钟可信标志——用于表示响应方的时钟是否可信（准确），0：不可信，1：可信。
     * 结果bit0…结果bit2——二进制编码表示：0：成功，1：地址重复，2：非法设备，3：容量不足，其它值：保留。
     */
    private int result;
    /**
     * 请求时间-10字节
     */
    private Calendar requestTime;
    /**
     * 接收时间-10字节
     */
    private Calendar receivedTime;
    /**
     * 响应时间-10字节
     */
    private Calendar responseTime;

    public LinkResponse() {
        this.apduSequence = APDUSequence.LINK_RESPONSE;
    }

    /**
     * 解码
     *
     * @param hexString
     */
    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        String result = hexString.substring(4, 6);
        this.result = Integer.parseInt(result, 16);
        String requestTimeStr = hexString.substring(6, 26);
        this.requestTime = DecoderUtils.decodeDateTimeHex(requestTimeStr);
        String receivedTimeStr = hexString.substring(26, 46);
        this.receivedTime = DecoderUtils.decodeDateTimeHex(receivedTimeStr);
        String responseTimeStr = hexString.substring(46);
        this.responseTime = DecoderUtils.decodeDateTimeHex(responseTimeStr);
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(result), 2));
        sb.append(EncoderUtils.encodeToDateTimeHex(requestTime));
        sb.append(EncoderUtils.encodeToDateTimeHex(receivedTime));
        sb.append(EncoderUtils.encodeToDateTimeHex(responseTime));
        return sb.toString();
    }
}
