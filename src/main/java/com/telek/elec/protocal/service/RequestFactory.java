package com.telek.elec.protocal.service;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.model.ActionRequestData;
import com.telek.elec.protocal.apdu.model.DataInfo;
import com.telek.elec.protocal.apdu.model.SetRequestData;
import com.telek.elec.protocal.apdu.read.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.basic.Null;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OMD;

public class RequestFactory {

    /**
     * 根据omd获取操作请求
     * @param omd
     * @return
     */
    public static ActionRequestNormal getActionRequestNormal(OMD omd, AbsData data) {
        ActionRequestNormal actionRequestNormal = new ActionRequestNormal();
        actionRequestNormal.setTimeStamp(0);

        ActionRequestData requestData = new ActionRequestData();
        requestData.setOmd(omd);
        requestData.setData(new DataInfo(data));
        actionRequestNormal.setActionRequestData(requestData);
        return actionRequestNormal;
    }

    /**
     * 根据omd获取操作请求
     * @param omd
     * @return
     */
    public static ActionRequestNormal getActionRequestNormal(OMD omd) {
        ActionRequestNormal actionRequestNormal = new ActionRequestNormal();
        actionRequestNormal.setTimeStamp(0);

        ActionRequestData requestData = new ActionRequestData();
        requestData.setOmd(omd);
        requestData.setData(new DataInfo(new Null()));
        actionRequestNormal.setActionRequestData(requestData);
        return actionRequestNormal;
    }

    /**
     * 根据oad获取
     * @param oad
     * @return
     */
    public static GetRequestNormal getRequestNormal(OAD oad) {
        return new GetRequestNormal(oad, 0);
    }

    /**
     * 获取sete请求
     * @param oad
     * @return
     */
    public static SetRequestNormal getSetRequestNormal(AbsData data, OAD oad) {
        SetRequestNormal setRequestNormal = new SetRequestNormal();
        setRequestNormal.setTimeStamp(0);
        setRequestNormal.setPiid(2);
        SetRequestData setRequestData = new SetRequestData();
        setRequestData.setOad(oad);
        DataInfo dataInfo = new DataInfo();
        dataInfo.setData(data);
        setRequestData.setData(dataInfo);
        setRequestNormal.setSetData(setRequestData);
        return setRequestNormal;
    }

    public static SetRequestNormal setRequestNormal(SetRequestData requestData) {
        SetRequestNormal setRequestNormal = new SetRequestNormal();
        setRequestNormal.setSetData(requestData);
        setRequestNormal.setPiid(2);
        setRequestNormal.setTimeStamp(0);
        return setRequestNormal;
    }
}
