package com.telek.elec.web.controller;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.service.request.RequestService;
import com.telek.elec.protocal.service.request.apdufactory.TempEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wll
 * @Date: 2019/7/7 23:05
 * @Description:
 */
@RestController
@RequestMapping("/test/tempEq")
public class TempEquipmentController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/temperature/{address}")
    public Object temperature(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.temperature();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @GetMapping("/acState/{address}")
    public Object acState(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.autoControlState();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @GetMapping("/pcState/{address}")
    public Object pcState(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.periodControlState();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @GetMapping("/tempThreshold/{address}")
    public Object tempThreshold(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.tempThreshold();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/setTempThreshold/{address}")
    public Object setTempThreshold(@PathVariable("address") String address, String up, String down) {
        CodecAPDU request = TempEquipment.setTempThreshold(up, down);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @GetMapping("/readACPeriod/{address}")
    public Object readACPeriod(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.autoControlPeriod();
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
        CodecAPDU request = TempEquipment.setAutoControlPeriod(controlPeriodList);
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/on/{address}")
    public Object on(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.on();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/off/{address}")
    public Object off(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.off();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/onTempControl/{address}")
    public Object onTempControl(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.onTempControl();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/offTempControl/{address}")
    public Object offTempControl(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.offTempControl();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/onPeriodControl/{address}")
    public Object onPeriodControl(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.onPeriodControl();
        requestService.sendRequest(request, address);
        return "OK";
    }

    @PostMapping("/offPeriodControl/{address}")
    public Object offPeriodControl(@PathVariable("address") String address) {
        CodecAPDU request = TempEquipment.offPeriodControl();
        requestService.sendRequest(request, address);
        return "OK";
    }
}
