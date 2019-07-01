package com.telek.elec.protocal.apdu.link;

import java.util.Calendar;

import com.telek.elec.protocal.constant.ProtocalSequence;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端预链接响应:
 * 81 00 80 07 E0 05 13 04 08 05 00 00 89 07 E0 05 13 04 08 05 01 02 5F 07 E0 05 13 04 08 05 02 02 DA
 */
@Data
@Slf4j
public class LinkResponse extends Link {
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
        this.id = ProtocalSequence.LINK_RESPONSE;
    }

    /**
     * 解码
     * @param byteStr
     */
    public void decodeByteStr(String byteStr) {
        log.info(this.getClass().getSimpleName() + "--预连接响应APDU--" + byteStr);

        if (byteStr.length() != 66) {
            log.error(this.getClass().getSimpleName() + "--预连接帧数据错误，长度不符合--" + byteStr);
            return;
        }

        String id = byteStr.substring(0, 2);
        if (Integer.parseInt(id, 16) != ProtocalSequence.LINK_RESPONSE) {
            log.error(this.getClass().getSimpleName() + "--帧数据错误，response ID错误--" + byteStr);
            return;
        }

        this.id = ProtocalSequence.LINK_RESPONSE;

        String piid = byteStr.substring(2, 4);
        this.piid = Integer.parseInt(piid, 16);

        String result = byteStr.substring(4, 6);
        this.result = Integer.parseInt(result, 16);

        String requestTimeStr = byteStr.substring(6, 26);
        this.requestTime = timeByteStrToCal(requestTimeStr);
        String receivedTimeStr = byteStr.substring(26, 46);
        this.receivedTime = timeByteStrToCal(receivedTimeStr);
        String responseTimeStr = byteStr.substring(46);
        this.responseTime = timeByteStrToCal(responseTimeStr);
    }

    /**
     * 通过时间byte字符串解析为calendar
     * @param s
     * @return
     */
    private Calendar timeByteStrToCal(String s) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(s.substring(0, 4), 16));
        cal.set(Calendar.MONTH, Integer.parseInt(s.substring(4, 6), 16) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s.substring(6, 8), 16));
        int week = Integer.parseInt(s.substring(8, 10), 16) + 1;
        if (week == 8) {
            week = 1;
        }
        cal.set(Calendar.DAY_OF_WEEK, week);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s.substring(10, 12), 16));
        cal.set(Calendar.MINUTE, Integer.parseInt(s.substring(12, 14), 16));
        cal.set(Calendar.SECOND, Integer.parseInt(s.substring(14, 16), 16));
        cal.set(Calendar.MILLISECOND, Integer.parseInt(s.substring(16), 16));
        return cal;
    }

}
