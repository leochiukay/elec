package com.telek.elec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.AirConditionTempControl;

/**
 * 空调时段温控
 */
@Controller
@RequestMapping("/test/actc")
public class AirConditionTempControlController {
    
    @Autowired
    private RequestService requestService;

    @PostMapping("/params")
    public Object params(String address) {
        GetRequestNormal apdu = AirConditionTempControl.params();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/setParams")
    public Object setParams(String address, float tempUp, float tempDown, short bh, short bm,
                                          short eh, short em) {
        SetRequestNormal apdu = AirConditionTempControl.params(tempUp, tempDown, bh, bm, eh, em);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/on")
    public Object on(String address) {
        ActionRequestNormal apdu = AirConditionTempControl.on();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/off")
    public Object off(String address) {
        ActionRequestNormal apdu = AirConditionTempControl.off();
        requestService.sendRequest(apdu, address);
        return "ok";
    }


}
