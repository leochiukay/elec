package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.setRequestNormal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.StreetLampFactory;
import com.telek.elec.protocal.apdu.model.DataInfo;
import com.telek.elec.protocal.apdu.model.SetRequestData;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.basic.Array;
import com.telek.elec.protocal.data.model.basic.Structure;
import com.telek.elec.protocal.data.model.basic.number.Unsigned;

/**
 * 路灯
 */
public class StreetLamp {

    /**
     * 控制状态
     * 属性2
     *
     * @return enum
     */
    public static CodecAPDU controlState() {
        return getRequestNormal(StreetLampFactory.controlState());
    }

    /**
     * 自动控制状态
     * 属性3
     *
     * @return enum
     */
    public static CodecAPDU autoControlState() {
        return getRequestNormal(StreetLampFactory.autoControlState());
    }

    /**
     * 自动控制时段
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
    public static CodecAPDU autoControlPeriod() {
        return getRequestNormal(StreetLampFactory.autoControlPeriod());
    }

    /**
     * @Description: 设置自动控制时段(同上)
     * @auther: wll
     * @date: 22:25 2019/7/7
     * @param: [controlPeriod]
     * @return: com.telek.elec.protocal.apdu.set.request.SetRequestNormal
     */
    public static SetRequestNormal setAutoControlPeriod(List<Map<String, Short>> controlPeriodList) {
        SetRequestData requestData = new SetRequestData();
        requestData.setOad(StreetLampFactory.autoControlPeriod());
        List<AbsData> arrayDatas = new ArrayList<>();
        for (Map<String, Short> controlPeriod : controlPeriodList) {
            Structure structure = new Structure();
            List<AbsData> period = new ArrayList<>();
            period.add(new Unsigned(controlPeriod.get("sh")));
            period.add(new Unsigned(controlPeriod.get("sm")));
            period.add(new Unsigned(controlPeriod.get("eh")));
            period.add(new Unsigned(controlPeriod.get("em")));
            structure.setDatas(period);
            arrayDatas.add(structure);
        }
        Array array = new Array();
        array.setDatas(arrayDatas);
        requestData.setData(new DataInfo(array));
        return setRequestNormal(requestData);
    }

    /**
     * 打开
     * 方法127
     *
     * @return null
     */
    public static CodecAPDU on() {
        return getActionRequestNormal(StreetLampFactory.on());
    }

    /**
     * 关闭
     * 方法128
     *
     * @return null
     */
    public static CodecAPDU off() {
        return getActionRequestNormal(StreetLampFactory.off());
    }

    /**
     * 打开自动控制
     * 方法129
     *
     * @return null
     */
    public static CodecAPDU onAutoControl() {
        return getActionRequestNormal(StreetLampFactory.onAutoControl());
    }

    /**
     * 关闭自动控制
     * 方法130
     *
     * @return null
     */
    public static CodecAPDU offAutoControl() {
        return getActionRequestNormal(StreetLampFactory.offAutoControl());
    }

}
