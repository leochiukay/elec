package com.telek.elec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.proxy.request.ProxyActionRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.apdu.proxy.request.ProxySetRequestList;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.ChargingPiple;
import com.telek.elec.web.constant.Const;

/**
 * @Auther: wll
 * @Date: 2019/7/7 23:28
 * @Description:
 */
@RestController
@RequestMapping("/test/charging")
public class ChargingPipleController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/state")
    public Object state(String address) {
        ProxyGetRequestList apdu = ChargingPiple.state(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/lcs")
    public Object loadControlState(String address) {
        ProxyGetRequestList apdu = ChargingPiple.loadControlState(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/acp")
    public Object appointControlPeriod(String address) {
        ProxyGetRequestList apdu = ChargingPiple.appointControlPeriod(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/lct")
    public Object loadControlThreshold(String address) {
        ProxyGetRequestList apdu = ChargingPiple.loadControlThreshold(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/slct")
    public Object setLoadControlThreshold(String address, int power) {
        ProxySetRequestList apdu = ChargingPiple.setLoadControlThreshold(Const.PROXY_ADDRESS, power);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/autoCP")
    public Object autoControlPeriod(String address) {
        ProxyGetRequestList apdu = ChargingPiple.autoControlPeriod(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/setAutoCP")
    public Object autoControlPeriod(String address, short bh, short bm, short eh, short em) {
        ProxySetRequestList apdu = ChargingPiple.autoControlPeriod(Const.PROXY_ADDRESS, bh, bm, eh, em);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/on")
    public Object on(String address) {
        ProxyActionRequestList apdu = ChargingPiple.on(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/off")
    public Object off(String address) {
        ProxyActionRequestList apdu = ChargingPiple.off(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/olc")
    public Object onLoadControl(String address) {
        ProxyActionRequestList apdu = ChargingPiple.onLoadControl(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/offLC")
    public Object offLoadControl(String address) {
        ProxyActionRequestList apdu = ChargingPiple.offLoadControl(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/onAP")
    public Object onAppointPeriod(String address) {
        ProxyActionRequestList apdu = ChargingPiple.onAppointPeriod(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/offAP")
    public Object offAppointPeriod(String address) {
        ProxyActionRequestList apdu = ChargingPiple.offAppointPeriod(Const.PROXY_ADDRESS);
        requestService.sendRequest(apdu, address);
        return "ok";
    }

}
