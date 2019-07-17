package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.AirConditionTempControlOADFactory;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.Long;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 空调时段温控oad/omd工厂类
 */
public class AirConditionTempControl {

    /**
     * 参数配置
     * =array 空调时段温控参数
     * 空调时段温控参数=structure
     * {
     *   温度阈值（参数）
     * 参数:=structure
     * {
     *   温度上限 long（单位：℃，换算：-1），
     *   温度下限 long（单位：℃，换算：-1）
     * }
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
    public static GetRequestNormal params() {
        return RequestFactory.getRequestNormal(AirConditionTempControlOADFactory.params());
    }

    /**
     * 参数配置
     * =array 空调时段温控参数
     * 空调时段温控参数=structure
     * {
     *   温度阈值（参数）
     * 参数:=structure
     * {
     *   温度上限 long（单位：℃，换算：-1），
     *   温度下限 long（单位：℃，换算：-1）
     * }
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
    public static SetRequestNormal params(float tempUp, float tempDown, short beginHour, short beginMinute,
                                          short endHour, short endMinute) {
        Datas<Array> datas = new Datas<>(new Array());
        Array array = datas.getData();
        // 顶层structure
        Structure structure = new Structure();
        array.addData(structure);

        // 温度阈值
        Structure tempTh = new Structure();
        tempTh.addData(new Long((short) (tempUp * 10)));
        tempTh.addData(new Long((short) (tempDown * 10)));
        structure.addData(tempTh);
        // 自动控制时段
        Structure autoControlPeriod = new Structure();
        autoControlPeriod.addData(new Unsigned(beginHour));
        autoControlPeriod.addData(new Unsigned(beginMinute));
        autoControlPeriod.addData(new Unsigned(endHour));
        autoControlPeriod.addData(new Unsigned(endMinute));
        structure.addData(autoControlPeriod);
        return RequestFactory.setRequestNormal(datas, AirConditionTempControlOADFactory.params());
    }

    /**
     * 开始空调时段温控
     * 方法127
     * @return null
     */
    public static ActionRequestNormal on() {
        return RequestFactory.getActionRequestNormal(AirConditionTempControlOADFactory.on(), new Datas(new Null()));
    }

    /**
     * 停止空调时段温控
     * 方法128
     * @return null
     */
    public static ActionRequestNormal off() {
        return RequestFactory.getActionRequestNormal(AirConditionTempControlOADFactory.off(), new Datas(new Null()));
    }

}
