package com.telek.elec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 空调时段温控
 *
 */
@Controller
@RequestMapping("/test/act")
public class AirConditionTempController {

    /**
     * 参数配置
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/params")
    public Object params(String address, short up, short down, short beginHour, short beginMinute, short endHour, short endMinute) throws Exception {
        /*Long upLong = new Long();
        upLong.setValue(up);
        upLong.setConversionCoefficient(-1);

        Long downLong = new Long();
        downLong.setValue(down);
        downLong.setConversionCoefficient(-1);

        Unsigned beginHourU = new Unsigned();
        beginHourU.setValue(beginHour);
        Unsigned beginMinuteU = new Unsigned();
        beginMinuteU.setValue(beginMinute);
        Unsigned endHourU = new Unsigned();
        endHourU.setValue(endHour);
        Unsigned endMinuteU = new Unsigned();
        endMinuteU.setValue(endMinute);
        CodecAPDU codecAPDU = AirConditionTempControl.params(upLong, downLong, beginHourU, beginMinuteU, endHourU, endMinuteU);
        ProtocalSendHelper.send2Service(address, codecAPDU, false);*/
        return true;
    }

}
