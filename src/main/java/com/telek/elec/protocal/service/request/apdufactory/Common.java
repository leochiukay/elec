package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.factory.CommonOADFactory;
import com.telek.elec.protocal.apdu.get.request.GetRequestRecord;
import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.RSD;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 发送请求的service
 */
public class Common {

    /**
     * 获取发电时间
     */
    public static GetRequestRecord generationTime(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.generationTime(), new RSD(selector), new RCSD());
    }

    /**
     * 环境温度：2601
     * @return
     */
    public static GetRequestRecord ambientTemperature(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.ambientTemperature(), new RSD(selector), new RCSD());
    }

    /**
     * 组件温度：2602
     * @return
     */
    public static GetRequestRecord componentTemperature(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.componentTemperature(), new RSD(selector), new RCSD());
    }

    /**
     * 湿度：2603
     * @return
     */
    public static GetRequestRecord humidity(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.humidity(), new RSD(selector), new RCSD());
    }

    /**
     * 辐射：2604
     * @return
     */
    public static GetRequestRecord radiation(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.radiation(), new RSD(selector), new RCSD());
    }

    /**
     * 风速：2605
     * @return
     */
    public static GetRequestRecord windSpeed(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.windSpeed(), new RSD(selector), new RCSD());
    }

    /**
     * 风向：2606
     * @return
     */
    public static GetRequestRecord windDirection(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.windDirection(), new RSD(selector), new RCSD());
    }

    /**
     * 转速：2607
     * @return
     */
    public static GetRequestRecord speed(Selector selector) {
        return RequestFactory.getRequestRecord(CommonOADFactory.speed(), new RSD(selector), new RCSD());
    }
}
