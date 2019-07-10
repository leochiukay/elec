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
    ACTION_GET_NORMAL_LIST(3, "操作若干个对象方法后读取若干个对象属性请求", APDUType.ACTION),

    PROXY_GET_LIST(1, "请求代理读取若干个服务器的若干个对象属性", APDUType.PROXY),
    PROXY_GET_RECORD(2, "请求代理读取一个服务器的一个记录型对象属性", APDUType.PROXY),
    PROXY_SET_LIST(3, "请求代理设置若干个服务器的若干个对象属性", APDUType.PROXY),
    PROXY_SET_THEN_GET(4, "请求代理设置后读取若干个服务器的若干个对象属性", APDUType.PROXY),
    PROXY_ACTION_LIST(5, "请求代理操作若干个服务器的若干个对象方法", APDUType.PROXY),
    PROXY_ACTION_THEN_GET(6, "请求代理操作后读取若干个服务器的若干个对象方法和属性", APDUType.PROXY),
    PROXY_THRANS_COMMAND(7, "请求代理透明转发命令", APDUType.PROXY);

    private int type;

    private String msg;

    private APDUType apduType;

    /**
     * 通过type和apdu type获取res_type
     * @param type
     * @param apduType
     * @return
     */
    public static APDUResType decode(int type, APDUType apduType) {
        for (APDUResType value : APDUResType.values()) {
            if (value.getType() == type && value.getApduType().equals(apduType)) {
                return value;
            }
        }
        return null;
    }
}
