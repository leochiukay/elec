package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.complex.OAD;
import com.telek.elec.protocal.data.model.complex.OI;
import com.telek.elec.protocal.data.model.complex.OMD;

/**
 * 温控设备工厂类
 */
public class TempEquipmentFactory {

    public static final OI OI = new OI(0x4311);

    /**
     * 温度
     * 属性2
     * @return long（单位：℃,换算：-1）
     */
    public static OAD temperature() {
        return new OAD(OI, 2, 0);
    }

    /**
     * 自动控制状态
     * 属性3
     * @return enum{未温度控制（0），温度控制（1）
     */
    public static OAD autoControlState() {
        return new OAD(OI, 3, 0);
    }

    /**
     * 时段控制状态
     * 属性4
     * @return enum{未温度控制（0），温度控制（1）
     */
    public static OAD periodControlState() {
        return new OAD(OI, 4, 0);
    }

    /**
     * 温度阈值
     * 属性5
     * @param up 温度上限
     * @param down 温度下限
     * @return
     */
    public static OAD tempThreshold(int up, int down) {
        return new OAD(OI, 5, 0);
    }

    /**
     * 自动控制时段
     * 属性6
     * @return structure
     * {
     *   起始小时 unsigned，
     *   起始分钟 unsigned，
     *   结束小时 unsigned，
     *   结束分钟 unsigned
     * }
     */
    public static OAD tempThreshold() {
        return new OAD(OI, 6, 0);
    }

    /**
     * 打开
     * 方法127
     * @return null
     */
    public static OMD on() {
        return new OMD(OI, 127, 0);
    }

    /**
     * 关闭
     * 方法128
     * @return null
     */
    public static OMD off() {
        return new OMD(OI, 128, 0);
    }

    /**
     * 打开温度控制
     * 方法129
     * @return null
     */
    public static OMD onTempControl() {
        return new OMD(OI, 129, 0);
    }

    /**
     * 关闭温度控制
     * 方法130
     * @return null
     */
    public static OMD offTempControl() {
        return new OMD(OI, 130, 0);
    }

    /**
     * 打开时段控制
     * 方法131
     * @return null
     */
    public static OMD onPeriodControl() {
        return new OMD(OI, 131, 0);
    }

    /**
     * 关闭时段控制
     * 方法132
     * @return null
     */
    public static OMD offPeriodControl() {
        return new OMD(OI, 132, 0);
    }
}
