package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.StreetLampOADFactory;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.service.RequestFactory;
import com.telek.elec.protocal.service.request.Utils;

/**
 * 路灯控制
 */
public class StreetLamp {

    /**
     * 控制状态
     * 属性2
     *
     * @return enum
     */
    public static ProxyGetRequestList controlState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, StreetLampOADFactory.controlState());
    }

    /**
     * 读取自动控制状态
     * 属性3
     *
     * @return enum
     */
    public static ProxyGetRequestList autoControlState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, StreetLampOADFactory.autoControlState());
    }

    /**
     * 读取自动控制时段
     * 属性4
     *
     * @return array::structure
     * {
     * 起始小时 unsigned，
     * 起始分钟 unsigned，
     * 结束小时 unsigned，
     * 结束分钟 unsigned
     * }
     */
    public static ProxyGetRequestList autoControlPeriod(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, StreetLampOADFactory.autoControlPeriod());
    }

    /**
     * @Description: 设置自动控制时段(同上)
     * @auther: wll
     * @date: 22:25 2019/7/7
     * @param: [controlPeriod]
     * @return: com.telek.elec.protocal.apdu.set.request.SetRequestNormal
     */
    public static ProxySetRequestList setAutoControlPeriod(String proxyAddress, short beginHour, short beginMinute,
                                                           short endHour, short endMinute) {
        Datas<Array> datas = Utils.autoControlPeriod(beginHour, beginMinute, endHour, endMinute);
        return RequestFactory.proxySetRequestList(proxyAddress, StreetLampOADFactory.autoControlPeriod(), datas);
    }

    /**
     * 打开
     * 方法127
     *
     * @return null
     */
    public static CodecAPDU on(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, StreetLampOADFactory.on(), new Datas(new Null()));
    }

    /**
     * 关闭
     * 方法128
     *
     * @return null
     */
    public static CodecAPDU off(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, StreetLampOADFactory.off(), new Datas(new Null()));
    }

    /**
     * 打开自动控制
     * 方法129
     *
     * @return null
     */
    public static CodecAPDU onAutoControl(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, StreetLampOADFactory.onAutoControl(), new Datas(new Null()));
    }

    /**
     * 关闭自动控制
     * 方法130
     *
     * @return null
     */
    public static CodecAPDU offAutoControl(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, StreetLampOADFactory.offAutoControl(), new Datas(new Null()));
    }

}
