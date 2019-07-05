package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.CommonOADFactory;

/**
 * 发送请求的service
 */
public class Common {

    /**
     * 读取通讯地址：4001 02 00
     * @return
     */
    public static CodecAPDU postalAddress() {
        return getRequestNormal(CommonOADFactory.postalAddress());
    }

    /**
     * 获取发电时间
     */
    public static CodecAPDU generationTime() {
        return getRequestNormal(CommonOADFactory.generationTime());
    }

    /**
     * 环境温度：2601
     * @return
     */
    public static CodecAPDU ambientTemperature() {
        return getRequestNormal(CommonOADFactory.ambientTemperature());
    }

    /**
     * 组件温度：2602
     * @return
     */
    public static CodecAPDU componentTemperature() {
        return getRequestNormal(CommonOADFactory.componentTemperature());
    }

    /**
     * 湿度：2603
     * @return
     */
    public static CodecAPDU humidity() {
        return getRequestNormal(CommonOADFactory.humidity());
    }

    /**
     * 辐射：2604
     * @return
     */
    public static CodecAPDU radiation() {
        return getRequestNormal(CommonOADFactory.radiation());
    }

    /**
     * 风速：2605
     * @return
     */
    public static CodecAPDU windSpeed() {
        return getRequestNormal(CommonOADFactory.windSpeed());
    }

    /**
     * 风向：2606
     * @return
     */
    public static CodecAPDU windDirection() {
        return getRequestNormal(CommonOADFactory.windDirection());
    }

    /**
     * 转速：2607
     * @return
     */
    public static CodecAPDU speed() {
        return getRequestNormal(CommonOADFactory.speed());
    }
}
