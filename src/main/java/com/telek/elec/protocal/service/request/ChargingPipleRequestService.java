package com.telek.elec.protocal.service.request;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.ChargingPipleFactory;

/**
 * 充电桩
 */
public class ChargingPipleRequestService {


    /**
     * 状态
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static CodecAPDU state() {
        return getRequestNormal(ChargingPipleFactory.state());
    }

    /**
     * 负荷控制状态
     * 属性3
     * @return =enum{未打开负荷控制（0），打开负荷控制（1）
     */
    public static CodecAPDU loadControlState() {
        return getRequestNormal(ChargingPipleFactory.loadControlState());
    }

    /**
     * 预约时间控制
     * 属性4
     * @return enum{未打开时间控制（0），打开时间控制（1）
     */
    public static CodecAPDU appointControlPeriod() {
        return getRequestNormal(ChargingPipleFactory.appointControlPeriod());
    }

    /**
     * 负荷控制阈值
     * 属性5
     * @return 有功功率
     */
    public static CodecAPDU loadControlThreshold() {
        return getRequestNormal(ChargingPipleFactory.loadControlThreshold());
    }

    /**
     * 自动控制时段
     * 属性6
     * @return 有功功率
     */
    public static CodecAPDU autoControlPeriod() {
        return getRequestNormal(ChargingPipleFactory.autoControlPeriod());
    }

    /**
     * 打开
     * 方法127
     * @return null
     */
    public static CodecAPDU on() {
        return getActionRequestNormal(ChargingPipleFactory.on());
    }

    /**
     * 关闭
     * 方法128
     * @return null
     */
    public static CodecAPDU off() {
        return getActionRequestNormal(ChargingPipleFactory.off());
    }

    /**
     * 打开负荷控制
     * 方法129
     * @return null
     */
    public static CodecAPDU onLoadControl() {
        return getActionRequestNormal(ChargingPipleFactory.onLoadControl());
    }

    /**
     * 关闭负荷控制
     * 方法130
     * @return null
     */
    public static CodecAPDU offLoadControl() {
        return getActionRequestNormal(ChargingPipleFactory.offLoadControl());
    }

    /**
     * 打开预约时段
     * 方法131
     * @return null
     */
    public static CodecAPDU onAppointPeriod() {
        return getActionRequestNormal(ChargingPipleFactory.onAppointPeriod());
    }

    /**
     * 关闭预约时段
     * 方法132
     * @return null
     */
    public static CodecAPDU offAppointPeriod() {
        return getActionRequestNormal(ChargingPipleFactory.offAppointPeriod());
    }


}
