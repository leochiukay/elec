package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.SmartSwitchFactory;

/**
 * 智能开关
 */
public class SmartSwitch {

    /**
     * 状态
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static CodecAPDU switchState() {
        return getRequestNormal(SmartSwitchFactory.switchState());
    }

    /**
     * 自动控制状态
     * 属性3
     * @return =enum{未（0），打开（1）
     */
    public static CodecAPDU autoControlState() {
        return getRequestNormal(SmartSwitchFactory.autoControlState());
    }

    /**
     * 自动控制时段
     * 属性4
     * @return structure
     * {
     *   起始小时 unsigned，
     *   起始分钟 unsigned，
     *   结束小时 unsigned，
     *   结束分钟 unsigned
     * }
     */
    public static CodecAPDU autoControlPeriod() {
        return getRequestNormal(SmartSwitchFactory.autoControlPeriod());
    }

    /**
     * 打开
     * 方法127
     * @return null
     */
    public static CodecAPDU on() {
        return getActionRequestNormal(SmartSwitchFactory.on());
    }

    /**
     * 关闭
     * 方法128
     * @return null
     */
    public static CodecAPDU off() {
        return getActionRequestNormal(SmartSwitchFactory.off());
    }

    /**
     * 打开自动控制
     * 方法129
     * @return null
     */
    public static CodecAPDU onAutoControl() {
        return getActionRequestNormal(SmartSwitchFactory.onAutoControl());
    }

    /**
     * 关闭自动控制
     * 方法130
     * @return null
     */
    public static CodecAPDU offAutoControl() {
        return getActionRequestNormal(SmartSwitchFactory.offAutoControl());
    }


}
