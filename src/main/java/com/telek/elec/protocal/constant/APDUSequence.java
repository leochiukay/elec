package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 各种请求响应序列号
 */
@Getter
@AllArgsConstructor
public enum APDUSequence {
    LINK_REQUEST(0x1, "预连接请求"),
    LINK_RESPONSE(0x81, "预链接响应"),

    CONNECTION_REQUEST(0x2, "应用层连接请求"),
    CONNECTION_RESPONSE(0x82, "应用层连接响应"),

    RELEASE_REQUEST(0x3, "应用层断开连接请求"),
    RELEASE_RESPONSE(0x83, "应用层断开连接响应"),
    RELEASE_NOTIFICATION(0x84, "应用层断开连接通知"),

    GET_REQUEST(0x5,"读取请求"),
    GET_RESPONSE(0x85,"读取响应"),

    SET_REQUEST(0x6,"设置请求"),
    SET_RESPONSE(0x86,"设置请求"),

    ACTION_REQUEST(0x7,"操作请求"),
    ACTION_RESPONSE(0x87,"操作请求"),

    REPORT_RESPONSE(0x08, "上报应答求"),
    REPORT_NOTIFICATION(0x88, "上报通知"),

    PROXY_REQUEST(0x09, "代理请求"),
    PROXY_RESPONSE(0x89, "代理响应");

    private int id;

    private String msg;

}
