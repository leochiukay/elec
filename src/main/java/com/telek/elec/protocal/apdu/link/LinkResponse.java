package com.telek.elec.protocal.apdu.link;

import java.util.Calendar;

import com.telek.elec.protocal.apdu.CommonCodecAPDU;
import com.telek.elec.protocal.apdu.codec.DecoderUtils;
import com.telek.elec.protocal.apdu.codec.EncoderUtils;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端预链接响应:
 * 81 00 80 07 E0 05 13 04 08 05 00 00 89 07 E0 05 13 04 08 05 01 02 5F 07 E0 05 13 04 08 05 02 02 DA
 * 81 —— [129] LINK-Response
 * 00 —— PIID
 * 80 —— 结果Result：可信，成功
 * 07 E0 05 13 04 08 05 00 00 89 —— 请求时间date_time：2016-05-19 08：05：00：137
 * 07 E0 05 13 04 08 05 01 02 5F —— 收到时间date_time：2016-05-19 08：05：01：607
 * 07 E0 05 13 04 08 05 02 02 DA —— 响应时间date_time：2016-05-19 08：05：02：730
 */
@Data
@Slf4j
public class LinkResponse extends CommonCodecAPDU implements Link {
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
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.result = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.requestTime = DecoderUtils.decodeDateTimeHex(hexString.substring(index, index += 20));
        this.receivedTime = DecoderUtils.decodeDateTimeHex(hexString.substring(index, index += 20));
        this.responseTime = DecoderUtils.decodeDateTimeHex(hexString.substring(index, index += 20));
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

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (receivedTime == null) {
            receivedTime = Calendar.getInstance();
        }
        if (requestTime == null) {
            requestTime = Calendar.getInstance();
        }
        if (responseTime == null) {
            responseTime = Calendar.getInstance();
        }
    }
}
