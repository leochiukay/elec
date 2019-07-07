package com.telek.elec.controller;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.read.request.GetRequestNormal;
import com.telek.elec.protocal.apdu.read.response.GetResponseNormal;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.StreetLamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wll
 * @Date: 2019/7/7 22:43
 * @Description:
 */
@RestController
@RequestMapping("/test/lamp")
public class StreetLampController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/readState/{address}")
    public Object readState(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.controlState();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @GetMapping("/readAC/{address}")
    public Object readAC(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.autoControlState();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @GetMapping("/readACPeriod/{address}")
    public Object readACPeriod(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.autoControlPeriod();
        requestService.sendRequest(request, address);
        return "OK";
    }

    /**
     * @Description:
     * @auther: wll
     * @date: 22:53 2019/7/7
     * @param: address,
     * periods 格式：HH:mm-HH:mm;HH:mm-HH:mm;...
     * @return: java.lang.Object
     */
    @PostMapping("/setACPeriod/{address}")
    public Object setACPeriod(@PathVariable("address") String address, @RequestParam String periods) {
        String[] periodArr = periods.split(";");
        List<Map<String, Short>> controlPeriodList = new ArrayList<>();
        for (String period : periodArr) {
            Map<String, Short> periodMap = new HashMap<>();
            periodMap.put("sh", Short.parseShort(period.split("-")[0].split(":")[0]));
            periodMap.put("sm", Short.parseShort(period.split("-")[0].split(":")[1]));
            periodMap.put("eh", Short.parseShort(period.split("-")[1].split(":")[0]));
            periodMap.put("em", Short.parseShort(period.split("-")[1].split(":")[1]));
            controlPeriodList.add(periodMap);
        }
        CodecAPDU request = StreetLamp.setAutoControlPeriod(controlPeriodList);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/on/{address}")
    public Object on(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.on();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/off/{address}")
    public Object off(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.off();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/onAC/{address}")
    public Object onAC(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.onAutoControl();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/offAC/{address}")
    public Object offAC(@PathVariable("address") String address) {
        CodecAPDU request = StreetLamp.offAutoControl();
        requestService.sendRequest(request, address);
        return "OK";
    }
}
