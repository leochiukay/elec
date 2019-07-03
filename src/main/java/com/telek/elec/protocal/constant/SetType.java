package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 6.3.8.1　Set-Request数据类型
 */
@Getter
@AllArgsConstructor
public enum SetType {
    NORMAL(1, "设置一个对象属性请求"),
    NORMAL_LIST(2, "设置若干个对象属性请求"),
    SET_GET_NORMAL_LIST(3, "设置后读取若干个对象属性请求");

    private int type;

    private String msg;

}
