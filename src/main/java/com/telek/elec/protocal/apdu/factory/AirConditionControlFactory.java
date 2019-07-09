package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;

/**
 * 空调轮停控制oad/omd工厂类
 */
public class AirConditionControlFactory {

    public static final OI OI = new OI(0x8121);

    /**
     * 参数配合
     * structure
     * {
     *   轮停周期    long-unsigned，单位：分钟
     *   轮停占空比  unsigned，单位：%
     *   上限温度    long，单位：℃，换算-1
     * }
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static OAD params() {
        return new OAD(OI, 2, 0);
    }

    /**
     * 开始空调轮停控制
     * 方法127
     * @return null
     */
    public static OMD on() {
        return new OMD(OI, 127, 0);
    }

    /**
     * 停止空调轮停控制
     * 方法128
     * @return null
     */
    public static OMD off() {
        return new OMD(OI, 128, 0);
    }

}
