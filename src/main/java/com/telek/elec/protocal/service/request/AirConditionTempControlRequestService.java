package com.telek.elec.protocal.service.request;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.AirConditionTempControlFactory;
import com.telek.elec.protocal.data.model.complex.OI;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 空调时段温控oad/omd工厂类
 */
public class AirConditionTempControlRequestService {

    public static final OI OI = new OI(0x8122);

    /**
     * 参数配置
     * =array 空调时段温控参数
     * 空调时段温控参数=structure
     * {
     *   温度阈值（同4311属性5）
     *   自动控制时段::=structure
     * {
     *    起始小时 unsigned，
     *    起始分钟 unsigned，
     *    结束小时 unsigned，
     *    结束分钟 unsigned
     * }
     * }
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static CodecAPDU params() {
        return RequestFactory.getRequestNormal(AirConditionTempControlFactory.params());
    }

    /**
     * 开始空调时段温控
     * 方法127
     * @return null
     */
    public static CodecAPDU on() {
        return RequestFactory.getActionRequestNormal(AirConditionTempControlFactory.on());
    }

    /**
     * 停止空调时段温控
     * 方法128
     * @return null
     */
    public static CodecAPDU off() {
        return RequestFactory.getActionRequestNormal(AirConditionTempControlFactory.off());
    }

}
