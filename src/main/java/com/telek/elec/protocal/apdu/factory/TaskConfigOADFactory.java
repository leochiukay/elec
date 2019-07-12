package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;

/**
 * 任务配置
 */
public class TaskConfigOADFactory {

    private static final OI oi = new OI(0x6012);

    /**
     * 方法127：Add（array 任务配置单元）
     * 添加或更新一组任务配置单元。
     * @return
     */
    public static OMD add() {
        return new OMD(oi, 127, 00);
    }

    /**
     * 方法128：Delete（array 任务ID）
     * 删除一组配置单元。
     * @return
     */
    public static OMD delete() {
        return new OMD(oi, 128, 00);
    }

    /**
     * 方法129：Clear()
     * 清空任务配置表。
     * @return
     */
    public static OMD clear() {
        return new OMD(oi, 129, 00);
    }

}
