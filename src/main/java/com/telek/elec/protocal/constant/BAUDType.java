package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wll
 * @Date: 2019/7/9 15:35
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum BAUDType {
    BAUD_300bps(0, "300bps"),
    BAUD_600bps(1, "600bps"),
    BAUD_1200bps(2, "1200bps"),
    BAUD_2400bps(3, "2400bps"),
    BAUD_4800bps(4, "4800bps"),
    BAUD_7200bps(5, "7200bps"),
    BAUD_9600bps(6, "9600bps"),
    BAUD_19200bps(7, "19200bps"),
    BAUD_38400bps(8, "38400bps"),
    BAUD_57600bps(9, "57600bps"),
    BAUD_115200bps(10, "115200bps"),
    BAUD_SELF_ADAPTION(255, "自适应");

    private int code;
    private String msg;
}
