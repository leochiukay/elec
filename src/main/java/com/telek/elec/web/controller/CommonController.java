package com.telek.elec.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.apdu.get.request.GetRequestRecord;
import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.apdu.model.get.GetRequestRecordData;
import com.telek.elec.protocal.apdu.model.selector.Selector5;
import com.telek.elec.protocal.apdu.model.selector.Selector7;
import com.telek.elec.protocal.data.model.CSD;
import com.telek.elec.protocal.data.model.MS;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.ROAD;
import com.telek.elec.protocal.data.model.RSD;
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.model.date.DateTimeS;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.Common;

@Controller
@RequestMapping("/test/comm")
public class CommonController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/test")
    public Object test(String address) {
        GetRequestRecord getRequestRecord = new GetRequestRecord();

        GetRequestRecordData getRecord = new GetRequestRecordData();
        getRecord.setOad(new OAD(new OI(0x6012), 03, 00));

        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.JANUARY, 11, 0, 0, 0);
        Selector5 selector5 = new Selector5();
        selector5.setDateTimeS(new DateTimeS(cal));
        selector5.setMs(new MS(1, null));
        getRecord.setRsd(new RSD(selector5));

        List<CSD> csds = new ArrayList<>();
        ROAD road = new ROAD();
        road.setOad(new OAD(new OI(0x5004), 02, 00));
        List<OAD> oadList = new ArrayList<>();
        oadList.add(new OAD(new OI(0x0010), 02, 00));
        oadList.add(new OAD(new OI(0x0020), 02, 00));
        road.setSequenceOfData(oadList);

        CSD csd = new CSD(road);
        csds.add(csd);
        getRecord.setRcsd(new RCSD(csds));

        getRequestRecord.setGetRecord(getRecord);
        getRequestRecord.setPiid(5);

        requestService.sendRequest(getRequestRecord, address);
        return "ok";
    }

    private static Calendar BEGIN;

    private static Calendar END;

    private static Selector SELECTOR7;

    static {
        BEGIN = Calendar.getInstance();
        BEGIN.set(2019, Calendar.JULY, 16, 1, 0, 0);
        END = Calendar.getInstance();
        END.set(2019, Calendar.JULY, 16, 1, 30, 0);
        SELECTOR7 = new Selector7(new DateTimeS(BEGIN), new DateTimeS(END), new TI((byte) 1, 15), new MS(1, null));
    }

    /**
     * 获取发电时间
     */
    @PostMapping("/all")
    public Object all(String address) {
        GetRequestRecord getRequestRecord = Common.getGetRequestRecord(SELECTOR7);
        requestService.sendRequest(getRequestRecord, address);
        return "ok";
    }
}
