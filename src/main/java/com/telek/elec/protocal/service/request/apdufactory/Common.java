package com.telek.elec.protocal.service.request.apdufactory;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.factory.CommonOADFactory;
import com.telek.elec.protocal.apdu.get.request.GetRequestRecord;
import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.apdu.model.get.GetRequestRecordData;
import com.telek.elec.protocal.data.model.CSD;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.ROAD;
import com.telek.elec.protocal.data.model.RSD;

/**
 * 发送请求的service
 */
public class Common {

    public static GetRequestRecord getGetRequestRecord(Selector selector) {
        GetRequestRecord getRequestRecord = new GetRequestRecord();

        GetRequestRecordData getRequestRecordData = new GetRequestRecordData();
        getRequestRecordData.setOad(new OAD(new OI(0x6012), 03, 00));
        getRequestRecordData.setRsd(new RSD(selector));

        List<CSD> csds = new ArrayList<>();
        ROAD road = new ROAD();
        road.setOad(new OAD(new OI(0x5002), 02, 00));
        List<OAD> oads = new ArrayList<>();
        oads.add(CommonOADFactory.generationTime());
        oads.add(CommonOADFactory.ambientTemperature());
        oads.add(CommonOADFactory.componentTemperature());
        oads.add(CommonOADFactory.humidity());
        oads.add(CommonOADFactory.radiation());
        oads.add(CommonOADFactory.windSpeed());
        oads.add(CommonOADFactory.windDirection());
        oads.add(CommonOADFactory.speed());
        oads.add(CommonOADFactory.pitchAngle());
        road.setSequenceOfData(oads);
        CSD csd = new CSD(road);
        csds.add(csd);
        RCSD rcsd = new RCSD(csds);
        getRequestRecordData.setRcsd(rcsd);

        getRequestRecord.setGetRecord(getRequestRecordData);
        return getRequestRecord;
    }
}
