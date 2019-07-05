package com.telek.elec.protocal.service.request;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.StreetLampFactory;

/**
 * 路灯
 */
public class StreetLampRequestService {

    /**
     * 控制状态
     * 属性2
     * @return enum
     */
    public static CodecAPDU controlState() {
        return getRequestNormal(StreetLampFactory.controlState());
    }

    /**
     * 自动控制状态
     * 属性3
     * @return enum
     */
    public static CodecAPDU autoControlState() {
        return getRequestNormal(StreetLampFactory.autoControlState());
    }

    /**
     * 自动控制时段
     * 属性4
     * @return  array::structure
     *          {
     *              起始小时 unsigned，
     *              起始分钟 unsigned，
     *              结束小时 unsigned，
     *              结束分钟 unsigned
     *          }
     */
    public static CodecAPDU autoControlPeriod() {
        return getRequestNormal(StreetLampFactory.autoControlPeriod());
    }

    /**
     * 打开
     * 方法127
     * @return null
     */
    public static CodecAPDU on() {
        return getActionRequestNormal(StreetLampFactory.on());
    }

    /**
     * 关闭
     * 方法128
     * @return null
     */
    public static CodecAPDU off() {
        return getActionRequestNormal(StreetLampFactory.off());
    }

    /**
     * 打开自动控制
     * 方法129
     * @return null
     */
    public static CodecAPDU onAutoControl() {
        return getActionRequestNormal(StreetLampFactory.onAutoControl());
    }

    /**
     * 关闭自动控制
     * 方法130
     * @return null
     */
    public static CodecAPDU offAutoControl() {
        return getActionRequestNormal(StreetLampFactory.offAutoControl());
    }

}
