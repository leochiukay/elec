package com.telek.elec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.elec.protocal.apdu.proxy.request.ProxyActionRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.SmartSwitch;
import com.telek.elec.web.constant.Const;

/**
 * 智能插座
 */
@Controller
@RequestMapping("/test/ss")
public class SmartSwitchController {
    
    @Autowired
    private RequestService requestService;


    @PostMapping("/state")
    public Object switchState(String address) {
        ProxyGetRequestList apdu = SmartSwitch.switchState(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/autoCS")
    public Object autoControlState(String address) {
        ProxyGetRequestList apdu = SmartSwitch.autoControlState(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/autoCP")
    public Object autoControlPeriod(String address) {
        ProxyGetRequestList apdu = SmartSwitch.autoControlPeriod(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/setAutoCP")
    public Object autoControlPeriod(String address, short bh, short bm, short eh, short em) {
        ProxySetRequestList apdu = SmartSwitch.autoControlPeriod(Const.PROXY_ADDRESS, bh, bm, eh, em);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/on")
    public Object on(String address) {
        ProxyActionRequestList apdu = SmartSwitch.on(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/off")
    public Object off(String address) {
        ProxyActionRequestList apdu = SmartSwitch.off(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/onAC")
    public Object onAutoControl(String address) {
        ProxyActionRequestList apdu = SmartSwitch.onAutoControl(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @RequestMapping("/offAC")
    public Object offAutoControl(String address) {
        ProxyActionRequestList apdu = SmartSwitch.offAutoControl(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }


}
