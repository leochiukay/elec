package com.telek.elec.protocal.service;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.get.request.GetRequestRecord;
import com.telek.elec.protocal.apdu.model.action.ActionRequestData;
import com.telek.elec.protocal.apdu.model.get.GetRequestRecordData;
import com.telek.elec.protocal.apdu.model.proxy.ProxyActionListItem;
import com.telek.elec.protocal.apdu.model.proxy.ProxyGetListItem;
import com.telek.elec.protocal.apdu.model.proxy.ProxySetListItem;
import com.telek.elec.protocal.apdu.model.set.SetRequestData;
import com.telek.elec.protocal.apdu.proxy.request.ProxyActionRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OMD;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.RSD;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.number.LongUnsigned;

public class RequestFactory {

    /**************************************************************************/
    /**************************PROXY****************************************/
    /**************************************************************************/

    public static ProxyActionRequestList proxyActionRequestList(String proxyAddress, OMD omd, Datas datas) {
        ProxyActionRequestList proxyActionRequestList = new ProxyActionRequestList();
        proxyActionRequestList.setTimeout(new LongUnsigned(180));
        List<ProxyActionListItem> actionListItems = new ArrayList<>();
        ProxyActionListItem proxyActionListItem = new ProxyActionListItem();
        proxyActionListItem.setTsa(new TSA(proxyAddress));
        proxyActionListItem.setTimeout(new LongUnsigned(180));
        List<ActionRequestData> actionRequestDataList = new ArrayList<>();
        ActionRequestData actionRequestData = new ActionRequestData();
        actionRequestData.setOmd(omd);
        actionRequestData.setData(datas);
        actionRequestDataList.add(actionRequestData);
        proxyActionListItem.setActionRequestDataList(actionRequestDataList);
        actionListItems.add(proxyActionListItem);
        proxyActionRequestList.setItems(actionListItems);
        return proxyActionRequestList;
    }

    /**
     *
     * @param proxyAddress 目标服务器地址
     * @return
     */
    public static ProxyGetRequestList proxyGetRequestList(String proxyAddress, OAD oad) {
        ProxyGetRequestList proxyGetRequestList = new ProxyGetRequestList();
        proxyGetRequestList.setTimeout(new LongUnsigned(180));
        List<ProxyGetListItem> listItems = new ArrayList<>();
        ProxyGetListItem proxyGetListItem = new ProxyGetListItem();
        proxyGetListItem.setTsa(new TSA(proxyAddress));
        proxyGetListItem.setTimeout(new LongUnsigned(180));
        List<OAD> oads = new ArrayList<>();
        oads.add(oad);
        proxyGetListItem.setOads(oads);
        listItems.add(proxyGetListItem);
        proxyGetRequestList.setItems(listItems);
        return proxyGetRequestList;
    }

    public static ProxySetRequestList proxySetRequestList(String proxyAddress, OAD oad, Datas datas) {
        ProxySetRequestList proxySetRequestList = new ProxySetRequestList();
        proxySetRequestList.setTimeout(new LongUnsigned(180));
        List<ProxySetListItem> items = new ArrayList<>();
        ProxySetListItem proxySetListItem = new ProxySetListItem();
        proxySetListItem.setTsa(new TSA(proxyAddress));
        proxySetListItem.setTimeout(new LongUnsigned(180));
        List<ProxySetListItem.ItemInfo> infos = new ArrayList<>();
        ProxySetListItem.ItemInfo itemInfo = new ProxySetListItem.ItemInfo();
        itemInfo.setDatas(datas);
        itemInfo.setOad(oad);
        infos.add(itemInfo);
        proxySetListItem.setInfos(infos);
        items.add(proxySetListItem);
        proxySetRequestList.setItems(items);
        return proxySetRequestList;
    }

    /**************************************************************************/
    /**************************ACTION****************************************/
    /**************************************************************************/
    /**
     * 根据omd获取操作请求
     * @param omd
     * @return
     */
    public static ActionRequestNormal getActionRequestNormal(OMD omd, Datas datas) {
        ActionRequestNormal actionRequestNormal = new ActionRequestNormal();

        ActionRequestData requestData = new ActionRequestData();
        requestData.setOmd(omd);
        requestData.setData(datas);

        actionRequestNormal.setActionRequestData(requestData);
        return actionRequestNormal;
    }

    /**************************************************************************/
    /**************************GET****************************************/
    /**************************************************************************/
    /**
     * 根据oad获取
     * @param oad
     * @return
     */
    public static GetRequestNormal getRequestNormal(OAD oad) {
        return new GetRequestNormal(oad, 0);
    }

    public static GetRequestRecord getRequestRecord(OAD oad, RSD rsd, RCSD rcsd) {
        GetRequestRecord getRequestRecord = new GetRequestRecord();
        GetRequestRecordData getRequestRecordData = new GetRequestRecordData();
        getRequestRecordData.setOad(oad);
        getRequestRecordData.setRsd(rsd);
        getRequestRecordData.setRcsd(rcsd);
        getRequestRecord.setGetRecord(getRequestRecordData);
        return getRequestRecord;
    }

    /**************************************************************************/
    /**************************SET****************************************/
    /**************************************************************************/
    /**
     * 获取set请求
     * @param oad
     * @return
     */
    public static SetRequestNormal setRequestNormal(Datas data, OAD oad) {
        SetRequestNormal setRequestNormal = new SetRequestNormal();
        setRequestNormal.setTimeStamp(0);
        setRequestNormal.setPiid(2);
        SetRequestData setRequestData = new SetRequestData();
        setRequestData.setOad(oad);
        setRequestData.setData(data);
        setRequestNormal.setSetData(setRequestData);
        return setRequestNormal;
    }

}
