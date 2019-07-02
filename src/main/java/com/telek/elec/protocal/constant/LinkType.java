package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 预连接类型
 */
@Getter
@AllArgsConstructor
public enum LinkType {

    LOGIN(0, "登录"),
    LOGOUT(2,"退出登录"),
    HEART_BEAT(1, "心跳");

    private int code;

    private String msg;
}
