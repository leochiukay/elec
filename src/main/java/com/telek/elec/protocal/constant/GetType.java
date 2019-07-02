package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 6.3.8.1　GET-Request数据类型
 */
@Getter
@AllArgsConstructor
public enum GetType {
    NORMAL(1, "读取一个对象属性请求"),
    NORMAL_LIST(2, "读取若干个对象属性请求"),
    RECORD(3, "读取一个记录型对象属性请求"),
    RECORD_LIST(4, "读取若干个记录型对象属性请求"),
    NEXT(5, "读取分帧响应的下一个数据块请求"),
    MD5(6, "读取一个对象属性的MD5值");

    private int type;

    private String msg;

}
