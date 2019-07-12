package com.telek.elec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.apdu.action.request.ActionRequestNormal;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.DR;

/**
 * 需求响应
 */
@Controller
@RequestMapping("/test/dr")
public class DRController {
    
    @Autowired
    private RequestService requestService;

    @RequestMapping("/state")
    public Object state(String address) {
        GetRequestNormal apdu = DR.state();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/params")
    public Object drParams(String address) {
        GetRequestNormal apdu = DR.drParams();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/publish")
    public Object drPublish(String address, double power, int minutes) {
        ActionRequestNormal apdu = DR.drPublish(power, minutes);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/release")
    public Object releaseDR(String address) {
        ActionRequestNormal apdu = DR.releaseDR();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

}
