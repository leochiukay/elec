package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.proxyActionRequestList;

import com.telek.elec.protocal.apdu.factory.TempEquipmentOADFactory;
import com.telek.elec.protocal.apdu.proxy.request.ProxyActionRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.Long;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 温控设备
 */
public class TempEquipment {

    /**
     * 温度
     * 属性2
     *
     * @return long（单位：℃,换算：-1）
     */
    public static ProxyGetRequestList temperature(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, TempEquipmentOADFactory.temperature());
    }

    /**
     * 自动控制状态
     * 属性3
     *
     * @return enum{未温度控制（0），温度控制（1）
     */
    public static ProxyGetRequestList autoControlState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, TempEquipmentOADFactory.autoControlState());
    }

    /**
     * 时段控制状态
     * 属性4
     *
     * @return enum{未温度控制（0），温度控制（1）
     */
    public static ProxyGetRequestList periodControlState(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, TempEquipmentOADFactory.periodControlState());
    }

    /**
     * 温度阈值
     * 属性5
     *
     * @return
     */
    public static ProxyGetRequestList tempThreshold(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, TempEquipmentOADFactory.tempThreshold());
    }

    /**
     * 设置温度阈值
     * 属性5
     *
     * @param up   温度上限
     * @param down 温度下限
     * @return
     */
    public static ProxySetRequestList setTempThreshold(String proxyAddress, float up, float down) {
        Datas<Structure> datas = new Datas<>(new Structure());
        Structure structure = datas.getData();
        structure.addData(new Long((short) (up * 10)));
        structure.addData(new Long((short) (down * 10)));
        return RequestFactory.proxySetRequestList(proxyAddress, TempEquipmentOADFactory.tempThreshold(), datas);
    }

    /**
     * 自动控制时段
     * 属性6
     *
     * @return structure
     * {
     * 起始小时 unsigned，
     * 起始分钟 unsigned，
     * 结束小时 unsigned，
     * 结束分钟 unsigned
     * }
     */
    public static ProxyGetRequestList autoControlPeriod(String proxyAddress) {
        return RequestFactory.proxyGetRequestList(proxyAddress, TempEquipmentOADFactory.autoControlPeriod());
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
        Datas<Structure> datas = new Datas<>(new Structure());
        Structure structure = datas.getData();
        structure.addData(new Unsigned(beginHour));
        structure.addData(new Unsigned(beginMinute));
        structure.addData(new Unsigned(endHour));
        structure.addData(new Unsigned(endMinute));
        return RequestFactory.proxySetRequestList(proxyAddress, TempEquipmentOADFactory.autoControlPeriod(), datas);
    }

    /**
     * 打开
     * 方法127
     *
     * @return null
     */
    public static ProxyActionRequestList on(String proxyAddress) {
        return proxyActionRequestList(proxyAddress, TempEquipmentOADFactory.on(), new Datas(new Null()));
    }

    /**
     * 关闭
     * 方法128
     *
     * @return null
     */
    public static ProxyActionRequestList off(String proxyAddress) {
        return proxyActionRequestList(proxyAddress, TempEquipmentOADFactory.off(), new Datas(new Null()));
    }

    /**
     * 打开温度控制
     * 方法129
     *
     * @return null
     */
    public static ProxyActionRequestList onTempControl(String proxyAddress) {
        return proxyActionRequestList(proxyAddress, TempEquipmentOADFactory.onTempControl(), new Datas(new Null()));
    }

    /**
     * 关闭温度控制
     * 方法130
     *
     * @return null
     */
    public static ProxyActionRequestList offTempControl(String proxyAddress) {
        return proxyActionRequestList(proxyAddress, TempEquipmentOADFactory.offTempControl(), new Datas(new Null()));
    }

    /**
     * 打开时段控制
     * 方法131
     *
     * @return null
     */
    public static ProxyActionRequestList onPeriodControl(String proxyAddress) {
        return proxyActionRequestList(proxyAddress, TempEquipmentOADFactory.onPeriodControl(), new Datas(new Null()));
    }

    /**
     * 关闭时段控制
     * 方法132
     *
     * @return null
     */
    public static ProxyActionRequestList offPeriodControl(String proxyAddress) {
        return proxyActionRequestList(proxyAddress, TempEquipmentOADFactory.offPeriodControl(), new Datas(new Null()));
    }
}
