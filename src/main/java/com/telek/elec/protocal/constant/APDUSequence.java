package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 各种请求响应序列号
 */
@Getter
@AllArgsConstructor
public enum APDUSequence {
    LINK_REQUEST(0x1, "预连接请求", APDUType.LINK),
    LINK_RESPONSE(0x81, "预链接响应", APDUType.LINK),

    CONNECTION_REQUEST(0x2, "应用层连接请求", APDUType.CONNECTION),
    CONNECTION_RESPONSE(0x82, "应用层连接响应", APDUType.CONNECTION),

    RELEASE_REQUEST(0x3, "应用层断开连接请求", APDUType.RELEASE),
    RELEASE_RESPONSE(0x83, "应用层断开连接响应", APDUType.RELEASE),
    RELEASE_NOTIFICATION(0x84, "应用层断开连接通知", APDUType.RELEASE),

    GET_REQUEST(0x5,"读取请求", APDUType.GET),
    GET_RESPONSE(0x85,"读取响应", APDUType.GET),

    SET_REQUEST(0x6,"设置请求", APDUType.SET),
    SET_RESPONSE(0x86,"设置请求", APDUType.SET),

    ACTION_REQUEST(0x7,"操作请求", APDUType.ACTION),
    ACTION_RESPONSE(0x87,"操作请求", APDUType.ACTION),

    REPORT_RESPONSE(0x08, "上报应答求", APDUType.REPORT),
    REPORT_NOTIFICATION(0x88, "上报通知", APDUType.REPORT),

    PROXY_REQUEST(0x09, "代理请求", APDUType.PROXY),
    PROXY_RESPONSE(0x89, "代理响应", APDUType.PROXY),

    ERROR_CLIENT(110, "客户端异常响应", APDUType.ERROR),
    ERROR_SERVER(238, "服务端异常响应", APDUType.ERROR);

    private int id;

    private String msg;

    private APDUType apduType;

    public static APDUSequence getByIdSequence(int apduSequence) {
        for (APDUSequence value : APDUSequence.values()) {
            if (value.getId() == apduSequence) {
                return value;
            }
        }
        return null;
    }
}
