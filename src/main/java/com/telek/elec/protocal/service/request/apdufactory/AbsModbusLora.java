package com.telek.elec.protocal.service.request.apdufactory;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.ModBusFactory;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Comdcb;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.string.VisibleString;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OMD;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * modubsus和lora共有
 */
public abstract class AbsModbusLora {

    /**
     * 属性2（设备对象列表，只读）::=array端口
     * 端口::=structure
     * {
     * 端口描述符 visible-string，
     * 端口参数   COMDCB，
     * 端口功能   enum{上行通信（0），抄表（1），级联（2），停用（3）}
     * }
     *
     * @return
     */
    public static CodecAPDU deviceList(VisibleString desc, Comdcb portParam, Enums portFunction) {
        Structure structure = new Structure();
        structure.setSize(3);
        List<AbsData> datas = new ArrayList<>(3);
        datas.add(desc);
        datas.add(portParam);
        datas.add(portFunction);
//        structure.setDatas(datas);

        Array array = new Array();
        array.setSize(1);
        List<AbsData> arrayDatas = new ArrayList<>(1);
        arrayDatas.add(structure);
//        array.setDatas(arrayDatas);

        // oad
        OAD oad = ModBusFactory.deviceList();
        // data
        return RequestFactory.getSetRequestNormal(array, oad);
    }

    /**
     * 方法127：配置端口（参数）
     * 参数::=structure
     * {
     *   端口号    OAD，
     *   端口参数  COMDCB，
     *   端口功能  enum
     * }
     *
     * @return
     */
    public static CodecAPDU portParam(OAD port, Comdcb portParam, Enums portFunction) {
        Structure structure = new Structure();
        structure.setSize(3);
        List<AbsData> datas = new ArrayList<>(3);
        datas.add(port);
        datas.add(portParam);
        datas.add(portFunction);
//        structure.setDatas(datas);

        // omd
        OMD omd = ModBusFactory.portParam();
        // data
        return RequestFactory.getActionRequestNormal(omd, structure);
    }

}
