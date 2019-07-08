package com.telek.elec.protocal.service.request.apdufactory;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.AirConditionTempControlFactory;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.basic.Array;
import com.telek.elec.protocal.data.model.basic.Structure;
import com.telek.elec.protocal.data.model.basic.number.Long;
import com.telek.elec.protocal.data.model.basic.number.Unsigned;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 空调时段温控oad/omd工厂类
 */
public class AirConditionTempControl {

    public static final OI OI = new OI(0x8122);

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
    public static CodecAPDU params(Long tempUp, Long tempDown, Unsigned beginHour, Unsigned beginMinute,
                                   Unsigned endHour, Unsigned endMinute) {
        Structure temp = new Structure();
        temp.setSize(2);
        List<AbsData> tempData = new ArrayList<>();
        tempData.add(tempUp);
        tempData.add(tempDown);
        temp.setDatas(tempData);

        Structure time = new Structure();
        time.setSize(4);
        List<AbsData> timeDatas = new ArrayList<>(4);
        timeDatas.add(beginHour);
        timeDatas.add(beginMinute);
        timeDatas.add(endHour);
        timeDatas.add(endMinute);
        time.setDatas(timeDatas);

        Structure param = new Structure();
        param.setSize(2);
        List<AbsData> paramStructures = new ArrayList<>(2);
        paramStructures.add(temp);
        paramStructures.add(time);
        param.setDatas(paramStructures);

        Array array = new Array();
        array.setSize(1);
        List<AbsData> arrayDatas = new ArrayList<>(1);
        arrayDatas.add(param);
        array.setDatas(arrayDatas);


        return RequestFactory.getSetRequestNormal(array, AirConditionTempControlFactory.params());
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
