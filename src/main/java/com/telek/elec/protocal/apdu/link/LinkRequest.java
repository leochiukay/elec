package com.telek.elec.protocal.apdu.link;

import java.util.Calendar;

import org.apache.commons.lang3.text.StrBuilder;

import com.telek.elec.protocal.apdu.Request;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.LinkType;
import com.telek.elec.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端发起预链接请求:
 * 01 00 00 00 B4 07 E0 05 13 04 08 05 00 00 A4
 */
@Data
@Slf4j
public class LinkRequest extends Link implements Request {
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
     *   month         unsigned，
     *   day_of_month  unsigned，
     *   day_of_week   unsigned，
     *   hour          unsigned，
     *   minute        unsigned，
     *   second        unsigned，
     *   milliseconds  long-unsigned
     */
    private Calendar requestTime;

    public LinkRequest() {
        this.apduSequence = APDUSequence.LINK_REQUEST;
    }

    @Override
    public String encodeThisToHex() {
        StrBuilder sb = new StrBuilder();
        // request id
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), 2));
        // 服务序号-优先级-ACD  PIID-ACD
        if (this.piid > 0xff) {
            this.piid = 0xff;
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), 2));
        // 请求类型  ENUMERATED
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.linkType.getCode()), 2));
        // 心跳周期  long-unsigned
        if (this.heartBeat > 0xffff) {
            this.heartBeat = 0xffff;
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.heartBeat), 4));
        // 请求时间
        Calendar cal = Calendar.getInstance();
        this.requestTime = cal;
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int millis = cal.get(Calendar.MILLISECOND);

        sb.append(StringUtils.subLastNumStr(Integer.toHexString(year), 4));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(month), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(day), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(week), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(hour), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(minutes), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(second), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(millis), 4));

        log.info(this.getClass().getSimpleName() + "--预连接请求APDU--" + sb.toString());

        return sb.toString();
    }
}
