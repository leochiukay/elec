package com.telek.elec.protocal.apdu.factory;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;

public class LoraFactory {

    private static final OI OI = new OI(0xf221, false);

    /**
     * 属性2（设备对象列表，只读）::=array端口
     * 端口::=structure
     * {
     *   端口描述符 visible-string，
     *   端口参数   COMDCB，
     *   端口功能   enum{上行通信（0），抄表（1），级联（2），停用（3）}
     * }
     * @return
     */
    public static OAD deviceList() {
        OAD oad = new OAD(OI, 0x02, 0x00, false);
        return oad;
    }

    /**
     * 方法127：配置端口（参数）
     * 参数::=structure
     * {
     *   端口号    OAD，
     *   端口参数  COMDCB，
     *   端口功能  enum
     * }
     * @return null
     */
    public static OMD offAppointPeriod() {
        return new OMD(OI, 127, 0, false);
    }


}
