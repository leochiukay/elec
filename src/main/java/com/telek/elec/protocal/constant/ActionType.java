package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 6.3.8.1　GET-Request数据类型
 */
@Getter
@AllArgsConstructor
public enum ActionType {
    NORMAL(1, "操作一个对象方法请求"),
    NORMAL_LIST(2, "操作若干个对象方法请求"),
    ACTION_GET_NORMAL_LIST(3, "操作若干个对象方法后读取若干个对象属性请求");

    private int type;

    private String msg;

}
