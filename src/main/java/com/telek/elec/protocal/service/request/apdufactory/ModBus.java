package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.ModBusOADFactory;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Comdcb;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OMD;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.service.RequestFactory;

/**
 * 发送请求的modbus service
 */
public class ModBus {
    /**
     * 属性2（设备对象列表，只读）::=array端口
     * 端口::=structure
     *      * {
     *      * 端口描述符 visible-string，
     *      * 端口参数   COMDCB，
     *      * 端口功能   enum{上行通信（0），抄表（1），级联（2），停用（3）}
     *      * }
     *
     * @return
     */
    public static GetRequestNormal deviceList() {
        // oad
        OAD oad = ModBusOADFactory.deviceList();
        return RequestFactory.getRequestNormal(oad);
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
    public static ActionRequestNormal portParam(OAD port, Comdcb portParam, Enums portFunction) {
        Datas<Structure> datas = new Datas<>(new Structure());
        Structure structure = datas.getData();
        structure.addData(port);
        structure.addData(portParam);
        structure.addData(portFunction);

        // omd
        OMD omd = ModBusOADFactory.portParam();
        // data
        return RequestFactory.getActionRequestNormal(omd, datas);
    }

}
