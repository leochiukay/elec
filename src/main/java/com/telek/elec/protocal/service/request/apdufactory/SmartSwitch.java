package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.factory.SmartSwitchOADFactory;
import com.telek.elec.protocal.apdu.proxy.request.ProxyActionRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.service.RequestFactory;
import com.telek.elec.protocal.service.request.Utils;

/**
 * 智能开关
 */
public class SmartSwitch {

    /**
     * 状态
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static ProxyGetRequestList switchState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, SmartSwitchOADFactory.switchState());
    }

    /**
     * 自动控制状态
     * 属性3
     * @return =enum{未（0），打开（1）
     */
    public static ProxyGetRequestList autoControlState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, SmartSwitchOADFactory.autoControlState());
    }

    /**
     * 自动控制时段
     * 属性4
     * @return structure
     * {
     *   起始小时 unsigned，
     *   起始分钟 unsigned，
     *   结束小时 unsigned，
     *   结束分钟 unsigned
     * }
     */
    public static ProxyGetRequestList autoControlPeriod(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, SmartSwitchOADFactory.autoControlPeriod());
    }

    /**
     * 设置自动控制时段
     * 属性4
     * @return structure
     * {
     *   起始小时 unsigned，
     *   起始分钟 unsigned，
     *   结束小时 unsigned，
     *   结束分钟 unsigned
     * }
     */
    public static ProxySetRequestList autoControlPeriod(String proxyAddress, short beginHour, short beginMinute,
                                                        short endHour, short endMinute) {
        Datas<Array> datas = Utils.autoControlPeriod(beginHour, beginMinute, endHour, endMinute);
        return RequestFactory.proxySetRequestList(proxyAddress, SmartSwitchOADFactory.autoControlPeriod(), datas);
    }

    /**
     * 打开
     * 方法127
     * @return null
     */
    public static ProxyActionRequestList on(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, SmartSwitchOADFactory.on(), new Datas(new Null()));
    }

    /**
     * 关闭
     * 方法128
     * @return null
     */
    public static ProxyActionRequestList off(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, SmartSwitchOADFactory.off(), new Datas(new Null()));
    }

    /**
     * 打开自动控制
     * 方法129
     * @return null
     */
    public static ProxyActionRequestList onAutoControl(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, SmartSwitchOADFactory.onAutoControl(), new Datas(new Null()));
    }

    /**
     * 关闭自动控制
     * 方法130
     * @return null
     */
    public static ProxyActionRequestList offAutoControl(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, SmartSwitchOADFactory.offAutoControl(), new Datas(new Null()));
    }


}
