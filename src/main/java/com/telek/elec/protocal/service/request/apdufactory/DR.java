package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getActionRequestNormal;
import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.DROADFactory;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.DoubleLong;
import com.telek.elec.protocal.data.model.number.LongUnsigned;

/**
 * 需求 响应
 */
public class DR {

    /**
     * 需求响应状态
     * 属性2
     * @return enum{未响应（0），正在响应（1）
     */
    public static GetRequestNormal state() {
        return getRequestNormal(DROADFactory.state());
    }

    /**
     * 需求响应参数
     * 属性3
     * @return =structure
     * {
     *   目标响应功率    double-long，单位：W，换算-1
     *   目标响应时间    long-unsigned，单位：分钟
     *   已响应功率    double-long，单位：W，换算-1
     *   已响应时间    long-unsigned，单位：分钟
     * }
     */
    public static GetRequestNormal drParams() {
        return getRequestNormal(DROADFactory.drParams());
    }

    /**
     * 需求下发
     * 方法127
     *    响应功率    double-long，单位：W，换算-1
     *    响应时间    long-unsigned，单位：分钟
     * @return null
     */
    public static ActionRequestNormal drPublish(double power, int minutes) {
        Datas<Structure> datas = new Datas<>(new Structure());
        Structure structure = datas.getData();
        structure.addData(new DoubleLong((int) (power * 10)));
        structure.addData(new LongUnsigned(minutes));
        return getActionRequestNormal(DROADFactory.drPublish(), datas);
    }

    /**
     * 解除需求响应
     * 方法128
     * @return null
     */
    public static ActionRequestNormal releaseDR() {
        return getActionRequestNormal(DROADFactory.releaseDR(), new Datas(new Null()));
    }


}
