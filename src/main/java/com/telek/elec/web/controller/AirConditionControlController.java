package com.telek.elec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.set.request.SetRequestNormal;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.AirConditionControl;

/**
 * 空调轮停
 *
 */
@Controller
@RequestMapping("/test/acc")
public class AirConditionControlController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/params")
    public Object params(String address) {
        GetRequestNormal apdu = AirConditionControl.params();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/setParams")
    public Object setParams(String address, int cycle, int percent, int upTemp) {
        SetRequestNormal apdu = AirConditionControl.setParams(cycle, percent, upTemp);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/on")
    public Object on(String address) {
        ActionRequestNormal apdu = AirConditionControl.on();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/off")
    public Object off(String address) {
        ActionRequestNormal apdu = AirConditionControl.off();
        requestService.sendRequest(apdu, address);
        return "ok";
    }


}
