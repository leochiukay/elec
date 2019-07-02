package com.telek.elec.protocal.constant;

import lombok.Getter;

/**
 * 各种请求响应序列号
 */
@Getter
public enum APDUSequence {
    LINK_REQUEST(0x1, "预连接请求"),
    LINK_RESPONSE(0x81, "预链接响应"),

    CONNECTION_REQUEST(0x2, "应用层连接请求"),
    CONNECTION_RESPONSE(0x82, "应用层连接响应"),

    RELEASE_REQUEST(0x3, "应用层断开连接请求"),
    RELEASE_RESPONSE(0x83, "应用层断开连接响应");



    APDUSequence(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    private int id;

    private String msg;


}
