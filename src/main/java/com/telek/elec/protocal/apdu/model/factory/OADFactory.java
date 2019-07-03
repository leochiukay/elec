package com.telek.elec.protocal.apdu.model.factory;

import com.telek.elec.protocal.apdu.model.OAD;

/**
 * 获取不同oad的工厂类
 */
public class OADFactory {

    /**
     * 读取通讯地址：4001 02 00
     * @return
     */
    public static OAD postalAddress() {
        OAD oad = new OAD(0x4001, 0x02, 0x00);
        return oad;
    }

    /**
     * MBUS：F220
     * @return
     */
    public static OAD mbus() {
        OAD oad = new OAD(0xf220, 0x02, 0x00);
        return oad;
    }

    /**
     * Lora：F221
     * @return
     */
    public static OAD lora() {
        OAD oad = new OAD(0xf222, 0x02, 0x00);
        return oad;
    }

    /**
     * 路灯控制：4310
     * @return
     */
    public static OAD streetLampControl() {
        OAD oad = new OAD(0x4310, 0x02, 0x00);
        return oad;
    }

    /**
     * 温度设备控制：4311
     * @return
     */
    public static OAD tempeEquipmentControl() {
        OAD oad = new OAD(0x4311, 0x02, 0x00);
        return oad;
    }

    /**
     * 充电桩控制：4312
     * @return
     */
    public static OAD chargingPileControl() {
        OAD oad = new OAD(0x4312, 0x02, 0x00);
        return oad;
    }

    /**
     * 智能开关：4313
     * @return
     */
    public static OAD intelligentSwitch() {
        OAD oad = new OAD(0x4313, 0x02, 0x00);
        return oad;
    }

    /**
     * 发电时间：2600
     * @return
     */
    public static OAD generationTime() {
        OAD oad = new OAD(0x2600, 0x02, 0x00);
        return oad;
    }

    /**
     * 环境温度：2601
     * @return
     */
    public static OAD ambientTemperature() {
        OAD oad = new OAD(0x2601, 0x02, 0x00);
        return oad;
    }

    /**
     * 组件温度：2602
     * @return
     */
    public static OAD componentTemperature() {
        OAD oad = new OAD(0x2602, 0x02, 0x00);
        return oad;
    }

    /**
     * 湿度：2603
     * @return
     */
    public static OAD humidity() {
        OAD oad = new OAD(0x2603, 0x02, 0x00);
        return oad;
    }

    /**
     * 辐射：2604
     * @return
     */
    public static OAD radiation() {
        OAD oad = new OAD(0x2604, 0x02, 0x00);
        return oad;
    }

    /**
     * 风速：2605
     * @return
     */
    public static OAD windSpeed() {
        OAD oad = new OAD(0x2605, 0x02, 0x00);
        return oad;
    }

    /**
     * 风向：2606
     * @return
     */
    public static OAD windDirection() {
        OAD oad = new OAD(0x2606, 0x02, 0x00);
        return oad;
    }

    /**
     * 转速：2607
     * @return
     */
    public static OAD speed() {
        OAD oad = new OAD(0x2607, 0x02, 0x00);
        return oad;
    }

    /**
     * 需求响应：8120
     * @return
     */
    public static OAD dr() {
        OAD oad = new OAD(0x8120, 0x02, 0x00);
        return oad;
    }

    /**
     * 空调轮停控制：8121
     * @return
     */
    public static OAD wheelStopControlAirConditioning() {
        OAD oad = new OAD(0x8121, 0x02, 0x00);
        return oad;
    }

    /**
     * 空调时段温控：8122
     * @return
     */
    public static OAD tempControlAirConditioningPeriod() {
        OAD oad = new OAD(0x8122, 0x02, 0x00);
        return oad;
    }

}
