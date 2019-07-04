package com.telek.elec.protocal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  APDUResType {

    GET_NORMAL(1, "读取一个对象属性请求", APDUType.GET),
    GET_NORMAL_LIST(2, "读取若干个对象属性请求", APDUType.GET),
    GET_RECORD(3, "读取一个记录型对象属性请求", APDUType.GET),
    GET_RECORD_LIST(4, "读取若干个记录型对象属性请求", APDUType.GET),
    GET_NEXT(5, "读取分帧响应的下一个数据块请求", APDUType.GET),
    GET_MD5(6, "读取一个对象属性的MD5值", APDUType.GET),

    SET_NORMAL(1, "设置一个对象属性请求", APDUType.SET),
    SET_NORMAL_LIST(2, "设置若干个对象属性请求", APDUType.SET),
    SET_GET_NORMAL_LIST(3, "设置后读取若干个对象属性请求", APDUType.SET),

    ACTION_NORMAL(1, "操作一个对象方法请求", APDUType.ACTION),
    ACTION_NORMAL_LIST(2, "操作若干个对象方法请求", APDUType.ACTION),
    ACTION_GET_NORMAL_LIST(3, "操作若干个对象方法后读取若干个对象属性请求", APDUType.ACTION);

    private int type;

    private String msg;

    private APDUType apduType;

    public static APDUResType getResByType(int type, APDUType apduType) {
        for (APDUResType value : APDUResType.values()) {
            if (value.getType() == type && value.getApduType().equals(apduType)) {
                return value;
            }
        }
        return null;
    }
}
