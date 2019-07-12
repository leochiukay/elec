package com.telek.elec.protocal.service.request.apdufactory;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.factory.NormalCollectionSchemeOADFactory;
import com.telek.elec.protocal.apdu.model.action.ActionRequestData;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.service.model.NormalCollectionScheme;

import java.util.List;

/**
 * @Auther: wll
 * @Date: 2019/7/9 21:55
 * @Description:
 */
public class NormalCollectionSchemeOps {
    /**
     * @Description: 下装普通采集方案
     * @auther: wll
     * @date: 21:58 2019/7/9
     * @param: []
     * @return: com.telek.elec.protocal.apdu.CodecAPDU
     */
    public static CodecAPDU addArray(List<NormalCollectionScheme> schemeList) {
        Array array = new Array();
        for (NormalCollectionScheme scheme : schemeList) {
            array.addData(scheme);
        }
        ActionRequestNormal actionRequest = new ActionRequestNormal();
        actionRequest.setActionRequestData(new ActionRequestData(NormalCollectionSchemeOADFactory.addScheme(), new Datas(array)));
        return actionRequest;
    }

    public static CodecAPDU clear() {
        ActionRequestNormal actionRequest = new ActionRequestNormal();
        actionRequest.setActionRequestData(new ActionRequestData(NormalCollectionSchemeOADFactory.clear(), new Datas(new Null())));
        return actionRequest;
    }
}
