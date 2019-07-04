package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.complex.OAD;
import com.telek.elec.protocal.data.model.complex.OI;

/**
 * 获取不同oad的工厂类
 */
public class OADFactory {

    /**
     * 读取通讯地址：4001 02 00
     * @return
     */
    public static OAD postalAddress() {
        OI oi = new OI(0x4001);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * MBUS：F220
     * @return
     */
    public static OAD mbus() {
        OI oi = new OI(0xf220);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * Lora：F221
     * @return
     */
    public static OAD lora() {
        OI oi = new OI(0xf222);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 路灯控制：4310
     * @return
     */
    public static OAD streetLampControl() {
        OI oi = new OI(0x4310);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 温度设备控制：4311
     * @return
     */
    public static OAD tempeEquipmentControl() {
        OI oi = new OI(0x4311);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 充电桩控制：4312
     * @return
     */
    public static OAD chargingPileControl() {
        OI oi = new OI(0x4312);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 智能开关：4313
     * @return
     */
    public static OAD intelligentSwitch() {
        OI oi = new OI(0x4313);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 发电时间：2600
     * @return
     */
    public static OAD generationTime() {
        OI oi = new OI(0x2600);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 环境温度：2601
     * @return
     */
    public static OAD ambientTemperature() {
        OI oi = new OI(0x2601);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 组件温度：2602
     * @return
     */
    public static OAD componentTemperature() {
        OI oi = new OI(0x2602);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 湿度：2603
     * @return
     */
    public static OAD humidity() {
        OI oi = new OI(0x2603);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 辐射：2604
     * @return
     */
    public static OAD radiation() {
        OI oi = new OI(0x2604);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 风速：2605
     * @return
     */
    public static OAD windSpeed() {
        OI oi = new OI(0x2605);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 风向：2606
     * @return
     */
    public static OAD windDirection() {
        OI oi = new OI(0x2606);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 转速：2607
     * @return
     */
    public static OAD speed() {
        OI oi = new OI(0x2607);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 需求响应：8120
     * @return
     */
    public static OAD dr() {
        OI oi = new OI(0x8120);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 空调轮停控制：8121
     * @return
     */
    public static OAD wheelStopControlAirConditioning() {
        OI oi = new OI(0x8121);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 空调时段温控：8122
     * @return
     */
    public static OAD tempControlAirConditioningPeriod() {
        OI oi = new OI(0x8122);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

}
