package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.factory.ChargingPipleOADFactory;
import com.telek.elec.protocal.apdu.proxy.request.ProxyActionRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.number.DoubleLong;
import com.telek.elec.protocal.service.RequestFactory;
import com.telek.elec.protocal.service.request.Utils;

/**
 * 充电桩
 */
public class ChargingPiple {


    /**
     * 状态
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static ProxyGetRequestList state(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, ChargingPipleOADFactory.state());
    }

    /**
     * 负荷控制状态
     * 属性3
     * @return =enum{未打开负荷控制（0），打开负荷控制（1）
     */
    public static ProxyGetRequestList loadControlState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, ChargingPipleOADFactory.loadControlState());
    }

    /**
     * 预约时间控制
     * 属性4
     * @return enum{未打开时间控制（0），打开时间控制（1）
     */
    public static ProxyGetRequestList appointControlPeriod(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, ChargingPipleOADFactory.appointControlPeriod());
    }

    /**
     * 负荷控制阈值
     * 属性5
     * @return 有功功率
     */
    public static ProxyGetRequestList loadControlThreshold(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, ChargingPipleOADFactory.loadControlThreshold());
    }

    /**
     * 设置负荷控制阈值
     * 属性5
     * @return 有功功率 double-long
     */
    public static ProxySetRequestList setLoadControlThreshold(String proxyAddress, int power) {
        Datas datas = new Datas(new DoubleLong(power));
        return RequestFactory.proxySetRequestList(proxyAddress, ChargingPipleOADFactory.loadControlThreshold(), datas);
    }

    /**
     * 自动控制时段
     * 属性6
     * @return 有功功率
     */
    public static ProxyGetRequestList autoControlPeriod(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, ChargingPipleOADFactory.autoControlPeriod());
    }

    /**
     * 设置自动控制时段
     * 属性6
     *
     * @return 有功功率
     */
    public static ProxySetRequestList autoControlPeriod(String proxyAddress, short beginHour, short beginMinute,
                                                        short endHour, short endMinute) {
        Datas<Array> datas = Utils.autoControlPeriod(beginHour, beginMinute, endHour, endMinute);
        return RequestFactory.proxySetRequestList(proxyAddress, ChargingPipleOADFactory.autoControlPeriod(), datas);
    }

    /**
     * 打开
     * 方法127
     * @return null
     */
    public static ProxyActionRequestList on(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, ChargingPipleOADFactory.on(), new Datas(new Null()));
    }

    /**
     * 关闭
     * 方法128
     * @return null
     */
    public static ProxyActionRequestList off(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, ChargingPipleOADFactory.off(), new Datas(new Null()));
    }

    /**
     * 打开负荷控制
     * 方法129
     * @return null
     */
    public static ProxyActionRequestList onLoadControl(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, ChargingPipleOADFactory.onLoadControl(), new Datas(new Null()));
    }

    /**
     * 关闭负荷控制
     * 方法130
     * @return null
     */
    public static ProxyActionRequestList offLoadControl(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, ChargingPipleOADFactory.offLoadControl(), new Datas(new Null()));
    }

    /**
     * 打开预约时段
     * 方法131
     * @return null
     */
    public static ProxyActionRequestList onAppointPeriod(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, ChargingPipleOADFactory.onAppointPeriod(), new Datas(new Null()));
    }

    /**
     * 关闭预约时段
     * 方法132
     * @return null
     */
    public static ProxyActionRequestList offAppointPeriod(String proxyAddress) {
        return RequestFactory.proxyActionRequestList(proxyAddress, ChargingPipleOADFactory.offAppointPeriod(), new Datas(new Null()));
    }


}
