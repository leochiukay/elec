package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.AirConditionControlFactory;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 空调轮停控制oad/omd工厂类
 */
public class AirConditionControl {

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
    public static CodecAPDU params() {
        return getRequestNormal(AirConditionControlFactory.params());
    }

    /**
     * 开始空调轮停控制
     * 方法127
     * @return null
     */
    public static CodecAPDU on() {
        return RequestFactory.getActionRequestNormal(AirConditionControlFactory.on());
    }

    /**
     * 停止空调轮停控制
     * 方法128
     * @return null
     */
    public static CodecAPDU off() {
        return RequestFactory.getActionRequestNormal(AirConditionControlFactory.off());
    }

}
