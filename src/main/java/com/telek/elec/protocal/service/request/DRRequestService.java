package com.telek.elec.protocal.service.request;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.DRFactory;

/**
 * 需求 响应
 */
public class DRRequestService {

    /**
     * 需求响应状态
     * 属性2
     * @return enum{未响应（0），正在响应（1）
     */
    public static CodecAPDU state() {
        return getRequestNormal(DRFactory.state());
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
    public static CodecAPDU drParams() {
        return getRequestNormal(DRFactory.drParams());
    }

    /**
     * 需求下发
     * 方法127
     *    响应功率    double-long，单位：W，换算-1
     *    响应时间    long-unsigned，单位：分钟
     * @return null
     */
    public static CodecAPDU drPublish(long responsLoad, long time) {
        return getActionRequestNormal(DRFactory.drPublish(responsLoad, time));
    }

    /**
     * 解除需求响应
     * 方法128
     * @return null
     */
    public static CodecAPDU releaseDR() {
        return getActionRequestNormal(DRFactory.releaseDR());
    }


}
