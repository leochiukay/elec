package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.setRequestNormal;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.AirConditionControlOADFactory;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.Long;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 空调轮停控制oad/omd工厂类
 */
public class AirConditionControl {

    /**
     * 参数配置
     * structure
     * {
     *   轮停周期    long-unsigned，单位：分钟
     *   轮停占空比  unsigned，单位：%
     *   上限温度    long，单位：℃，换算-1
     * }
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static GetRequestNormal params() {
        return getRequestNormal(AirConditionControlOADFactory.params());
    }

    /**
     * 设置参数
     * structure
     * {
     *   轮停周期    long-unsigned，单位：分钟
     *   轮停占空比  unsigned，单位：%
     *   上限温度    long，单位：℃，换算-1
     * }
     * 属性2
     * @return enum{关闭（0），打开（1）
     */
    public static SetRequestNormal setParams(int cycle, int percent, int upTemp) {
        Datas<Structure> datas = new Datas<>(new Structure());
        Structure structure = datas.getData();
        structure.addData(new LongUnsigned(cycle));
        structure.addData(new Unsigned((short) percent));
        structure.addData(new Long((short) upTemp));
        return setRequestNormal(datas, AirConditionControlOADFactory.params());
    }

    /**
     * 开始空调轮停控制
     * 方法127
     * @return null
     */
    public static ActionRequestNormal on() {
        return RequestFactory.getActionRequestNormal(AirConditionControlOADFactory.on(), new Datas(new Null()));
    }

    /**
     * 停止空调轮停控制
     * 方法128
     * @return null
     */
    public static ActionRequestNormal off() {
        return RequestFactory.getActionRequestNormal(AirConditionControlOADFactory.off(), new Datas(new Null()));
    }

}
