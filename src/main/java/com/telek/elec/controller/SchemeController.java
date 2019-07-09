package com.telek.elec.controller;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.StorageTimeUnitType;
import com.telek.elec.protocal.data.model.*;
import com.telek.elec.protocal.data.service.model.Document;
import com.telek.elec.protocal.data.service.model.NormalCollectionScheme;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.DocumentOps;
import com.telek.elec.protocal.service.request.apdufactory.NormalCollectionSchemeOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
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
        NormalCollectionScheme scheme = new NormalCollectionScheme();
        scheme.setIndex(12);
        scheme.setDeep(256);
        NormalCollectionScheme.CollectWay collectWay = new NormalCollectionScheme.CollectWay();
        collectWay.setType(0);
        collectWay.setData(new Null());
        scheme.setWay(collectWay);
        List<CSD> csdList = new ArrayList<>();
        csdList.add(new CSD(new OAD(new OI(0x0010), 2, 0), null));
        csdList.add(new CSD(new OAD(new OI(0x2000), 2, 0), null));
        csdList.add(new CSD(new OAD(new OI(0x2001), 2, 0), null));
        scheme.setRecordList(csdList);
        scheme.setMs(new MS(1, null));
        scheme.setStorageTimeUnitType(StorageTimeUnitType.CURRENT_DAY_0000);
        list.add(scheme);
        CodecAPDU apdu = NormalCollectionSchemeOps.addArray(list);
        requestService.sendRequest(apdu, address);
        return "OK";
    }

    @PostMapping("/clear")
    public Object clear(String address) {
        CodecAPDU apdu = NormalCollectionSchemeOps.clear();
        requestService.sendRequest(apdu, address);
        return "OK";
    }
}
