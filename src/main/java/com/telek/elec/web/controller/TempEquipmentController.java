package com.telek.elec.web.controller;

import static com.telek.elec.web.constant.Const.PROXY_ADDRESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.TempEquipment;

/**
 *
 * 温控设备
 * @Auther: wll
 * @Date: 2019/7/7 23:05
 * @Description:
 */
@RestController
@RequestMapping("/test/te")
public class TempEquipmentController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/temp")
    public Object temperature(String address) {
        CodecAPDU request = TempEquipment.temperature(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/acState")
    public Object acState(String address) {
        CodecAPDU request = TempEquipment.autoControlState(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/pcState")
    public Object pcState(String address) {
        CodecAPDU request = TempEquipment.periodControlState(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/tempThreshold")
    public Object tempThreshold(String address) {
        CodecAPDU request = TempEquipment.tempThreshold(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/setTempThreshold")
    public Object setTempThreshold(String address, float up, float down) {
        CodecAPDU request = TempEquipment.setTempThreshold(PROXY_ADDRESS, up, down);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/acPeriod")
    public Object readACPeriod(String address) {
        CodecAPDU request = TempEquipment.autoControlPeriod(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/setACPeriod")
    public Object setACPeriod(String address, short bh, short bm, short eh, short em) {
        CodecAPDU request = TempEquipment.setAutoControlPeriod(PROXY_ADDRESS, bh, bm, eh, em);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/on")
    public Object on(String address) {
        CodecAPDU request = TempEquipment.on(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/off")
    public Object off(String address) {
        CodecAPDU request = TempEquipment.off(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/onTempControl")
    public Object onTempControl(String address) {
        CodecAPDU request = TempEquipment.onTempControl(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/offTempControl")
    public Object offTempControl(String address) {
        CodecAPDU request = TempEquipment.offTempControl(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/onPeriodControl")
    public Object onPeriodControl(String address) {
        CodecAPDU request = TempEquipment.onPeriodControl(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/offPeriodControl")
    public Object offPeriodControl(String address) {
        CodecAPDU request = TempEquipment.offPeriodControl(PROXY_ADDRESS);
        requestService.sendRequest(request, address);
        return "OK";
    }
}
