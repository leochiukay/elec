package com.telek.elec.web.controller;

import static com.telek.elec.web.constant.Const.PROXY_ADDRESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.proxy.request.ProxyGetRequestList;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.StreetLamp;

/**
 * 路灯控制
 * @Auther: wll
 * @Date: 2019/7/7 22:43
 * @Description:
 */
@RestController
@RequestMapping("/test/lamp")
public class StreetLampController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/state")
    public Object readState(String address) {
        ProxyGetRequestList proxyGetRequestList = StreetLamp.controlState(PROXY_ADDRESS);
        requestService.sendRequest(proxyGetRequestList, address);
        return "OK";
    }

    @PostMapping("/autoState")
    public Object readAC(String address) {
        CodecAPDU request = StreetLamp.autoControlState(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/autoPeriod")
    public Object readACPeriod(String address) {
        CodecAPDU request = StreetLamp.autoControlPeriod(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/setAutoPeriod")
    public Object setACPeriod(String address, int bh, int bm, int eh, int em) {
        CodecAPDU request = StreetLamp.setAutoControlPeriod(PROXY_ADDRESS, (short) bh, (short) bm, (short) eh, (short) em);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/on")
    public Object on(String address) {
        CodecAPDU request = StreetLamp.on(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/off")
    public Object off(String address) {
        CodecAPDU request = StreetLamp.off(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/onAutoControl")
    public Object onAC(String address) {
        CodecAPDU request = StreetLamp.onAutoControl(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/offAutoControl")
    public Object offAC(String address) {
        CodecAPDU request = StreetLamp.offAutoControl(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }
}
