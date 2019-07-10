package com.telek.elec.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.StorageTimeUnitType;
import com.telek.elec.protocal.data.model.CSD;
import com.telek.elec.protocal.data.model.MS;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.service.model.NormalCollectionScheme;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.NormalCollectionSchemeOps;

/**
 * 普通采集方案
 * @Auther: wll
 * @Date: 2019/7/9 23:59
 * @Description:
 */
@RestController
@RequestMapping("/test/scheme")
public class SchemeController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/add")
    public Object addDocument(String address) {
        List<NormalCollectionScheme> list = new ArrayList<>();
        NormalCollectionScheme scheme = getNormalCollectionScheme();
        list.add(scheme);
        CodecAPDU apdu = NormalCollectionSchemeOps.addArray(list);
        requestService.sendRequest(apdu, address);
        return "OK";
    }

    private NormalCollectionScheme getNormalCollectionScheme() {
        NormalCollectionScheme scheme = new NormalCollectionScheme();
        scheme.setIndex(1);
        scheme.setDeep(256);
        NormalCollectionScheme.CollectWay collectWay = new NormalCollectionScheme.CollectWay();
        collectWay.setType(0);
        collectWay.setData(new Null());
        scheme.setWay(collectWay);
        List<CSD> csdList = getCsds();
        scheme.setRecordList(csdList);
        scheme.setMs(new MS(1, null));
        scheme.setStorageTimeUnitType(StorageTimeUnitType.CURRENT_DAY_0000);
        return scheme;
    }

    private List<CSD> getCsds() {
        List<CSD> csdList = new ArrayList<>();
        csdList.add(new CSD(new OAD(new OI(0xF220), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0xF221), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0x4310), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0x4311), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0x4312), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0x4313), 2, 0)));
        int i = 0x2600;
        csdList.add(new CSD(new OAD(new OI(i), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(i += 1), 2, 0)));

        csdList.add(new CSD(new OAD(new OI(0x8120), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0x8121), 2, 0)));
        csdList.add(new CSD(new OAD(new OI(0x8122), 2, 0)));

        return csdList;
    }

    @PostMapping("/clear")
    public Object clear(String address) {
        CodecAPDU apdu = NormalCollectionSchemeOps.clear();
        requestService.sendRequest(apdu, address);
        return "OK";
    }
}
