package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getSetRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.setRequestNormal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.TempEquipmentFactory;
import com.telek.elec.protocal.apdu.model.SetRequestData;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.Long;
import com.telek.elec.protocal.data.model.number.Unsigned;

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
    public static CodecAPDU temperature() {
        return getRequestNormal(TempEquipmentFactory.temperature());
    }

    /**
     * 自动控制状态
     * 属性3
     *
     * @return enum{未温度控制（0），温度控制（1）
     */
    public static CodecAPDU autoControlState() {
        return getRequestNormal(TempEquipmentFactory.autoControlState());
    }

    /**
     * 时段控制状态
     * 属性4
     *
     * @return enum{未温度控制（0），温度控制（1）
     */
    public static CodecAPDU periodControlState() {
        return getRequestNormal(TempEquipmentFactory.periodControlState());
    }

    /**
     * 温度阈值
     * 属性5
     *
     * @return
     */
    public static CodecAPDU tempThreshold() {
        return getRequestNormal(TempEquipmentFactory.tempThreshold());
    }

    /**
     * 设置温度阈值
     * 属性5
     *
     * @param up   温度上限
     * @param down 温度下限
     * @return
     */
    public static CodecAPDU setTempThreshold(String up, String down) {
        Structure structure = new Structure();
        List<AbsData> period = new ArrayList<>();
        Long ups = new Long(Short.parseShort(up));
        ups.setConversionCoefficient(-1);
        period.add(ups);
        Long downs = new Long(Short.parseShort(down));
        downs.setConversionCoefficient(-1);
        period.add(downs);
        structure.setDatas(period);
        return getSetRequestNormal(structure, TempEquipmentFactory.tempThreshold());
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
    public static CodecAPDU autoControlPeriod() {
        return getRequestNormal(TempEquipmentFactory.autoControlPeriod());
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
        requestData.setOad(TempEquipmentFactory.autoControlPeriod());
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
        requestData.setData(new Datas(array));
        return setRequestNormal(requestData);
    }

    /**
     * 打开
     * 方法127
     *
     * @return null
     */
    public static CodecAPDU on() {
        return getActionRequestNormal(TempEquipmentFactory.on());
    }

    /**
     * 关闭
     * 方法128
     *
     * @return null
     */
    public static CodecAPDU off() {
        return getActionRequestNormal(TempEquipmentFactory.off());
    }

    /**
     * 打开温度控制
     * 方法129
     *
     * @return null
     */
    public static CodecAPDU onTempControl() {
        return getActionRequestNormal(TempEquipmentFactory.onTempControl());
    }

    /**
     * 关闭温度控制
     * 方法130
     *
     * @return null
     */
    public static CodecAPDU offTempControl() {
        return getActionRequestNormal(TempEquipmentFactory.offTempControl());
    }

    /**
     * 打开时段控制
     * 方法131
     *
     * @return null
     */
    public static CodecAPDU onPeriodControl() {
        return getActionRequestNormal(TempEquipmentFactory.onPeriodControl());
    }

    /**
     * 关闭时段控制
     * 方法132
     *
     * @return null
     */
    public static CodecAPDU offPeriodControl() {
        return getActionRequestNormal(TempEquipmentFactory.offPeriodControl());
    }
}
