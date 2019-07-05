package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.complex.OAD;
import com.telek.elec.protocal.data.model.complex.OI;

/**
 * 获取不同oad的工厂类
 */
public class CommonOADFactory {

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
     * 发电时间：2600
     * @return double-long-unsigned 单位：秒
     */
    public static OAD generationTime() {
        OI oi = new OI(0x2600);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 环境温度：2601
     * @return long，单位：℃，换算：-1
     */
    public static OAD ambientTemperature() {
        OI oi = new OI(0x2601);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 组件温度：2602
     * @return long，单位：℃，换算：-1
     */
    public static OAD componentTemperature() {
        OI oi = new OI(0x2602);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 湿度：2603
     * @return long-unsigned，单位：%RH，换算：-1
     */
    public static OAD humidity() {
        OI oi = new OI(0x2603);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 辐射：2604
     * @return long-unsigned，单位：W/m2
     */
    public static OAD radiation() {
        OI oi = new OI(0x2604);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 风速：2605
     * @return long-unsigned，单位：MJ/m2，换算：-3
     */
    public static OAD windSpeed() {
        OI oi = new OI(0x2605);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 风向：2606
     * @return long-unsigned
     */
    public static OAD windDirection() {
        OI oi = new OI(0x2606);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }

    /**
     * 转速：2607
     * @return long-unsigned，单位：RPM
     */
    public static OAD speed() {
        OI oi = new OI(0x2607);
        OAD oad = new OAD(oi, 0x02, 0x00);
        return oad;
    }
}
