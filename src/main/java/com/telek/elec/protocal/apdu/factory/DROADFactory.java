package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;

/**
 * 需求 响应
 */
public class DROADFactory {

    public static final OI OI = new OI(0x8120);

    /**
     * 需求响应状态
     * 属性2
     * @return enum{未响应（0），正在响应（1）
     */
    public static OAD state() {
        return new OAD(OI, 2, 0);
    }

    /**
     * 需求响应参数
     * 属性3
     * @return =structure
     * {
     *   目标响应功率    double-long，单位：W，换算-1
     *   目标响应时间    long-unsigned，单位：分钟
     *   已响应功率    double-long，单位：W，换算-1
     *   已响应时间    long-unsigned，单位：分钟
     * }
     */
    public static OAD drParams() {
        return new OAD(OI, 3, 0);
    }

    /**
     * 需求下发
     * 方法127
     *    响应功率    double-long，单位：W，换算-1
     *    响应时间    long-unsigned，单位：分钟
     * @return null
     */
    public static OMD drPublish() {
        return new OMD(OI, 127, 0);
    }

    /**
     * 解除需求响应
     * 方法128
     * @return null
     */
    public static OMD releaseDR() {
        return new OMD(OI, 128, 0);
    }


}
