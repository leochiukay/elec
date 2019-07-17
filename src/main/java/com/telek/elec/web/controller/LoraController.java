package com.telek.elec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.get.request.GetRequestNormal;
import com.telek.elec.protocal.constant.BAUDType;
import com.telek.elec.protocal.data.model.Comdcb;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.Lora;

/**
 * @Auther: wll
 * @Date: 2019/7/7 22:41
 * @Description:
 */
@RestController
@RequestMapping("/test/lora")
public class LoraController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/dl")
    public Object readProperties(String address) {
        GetRequestNormal apdu = Lora.deviceList();
        requestService.sendRequest(apdu, address);
        return "ok";
    }

    @PostMapping("/port")
    public Object setPort(String address) {
        OAD oad = new OAD(new OI(0xf221), 2, 0);
        Comdcb comdcb = new Comdcb(BAUDType.BAUD_9600bps, 2, 8, 1, 0);
        CodecAPDU codecAPDU = Lora.portParam(oad, comdcb, new Enums((short) 1));
        requestService.sendRequest(codecAPDU, address);
        return "ok";
    }
}
