package com.telek.elec.protocal.service.request.apdufactory;

import static com.telek.elec.protocal.service.RequestFactory.getRequestNormal;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.apdu.factory.CommonOADFactory;
import com.telek.elec.protocal.apdu.model.get.GetRequestRecordData;
import com.telek.elec.protocal.apdu.get.request.GetRequestRecord;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.RSD;

/**
 * 发送请求的service
 */
public class Common {

    /**
     * 获取发电时间
     */
    public static CodecAPDU generationTime2() {
        GetRequestRecord getRequestRecord = new GetRequestRecord();
        getRequestRecord.setPiid(03);
        GetRequestRecordData getRecord = new GetRequestRecordData();
        getRequestRecord.setGetRecord(getRecord);

        getRecord.setOad(CommonOADFactory.generationTime());
        getRecord.setRsd(new RSD(null));
        getRecord.setRcsd(new RCSD());

        return getRequestRecord;
    }

    /**
     * 获取发电时间
     */
    public static CodecAPDU generationTime() {
        return getRequestNormal(CommonOADFactory.generationTime());
    }

    /**
     * 环境温度：2601
     * @return
     */
    public static CodecAPDU ambientTemperature() {
        return getRequestNormal(CommonOADFactory.ambientTemperature());
    }

    /**
     * 组件温度：2602
     * @return
     */
    public static CodecAPDU componentTemperature() {
        return getRequestNormal(CommonOADFactory.componentTemperature());
    }

    /**
     * 湿度：2603
     * @return
     */
    public static CodecAPDU humidity() {
        return getRequestNormal(CommonOADFactory.humidity());
    }

    /**
     * 辐射：2604
     * @return
     */
    public static CodecAPDU radiation() {
        return getRequestNormal(CommonOADFactory.radiation());
    }

    /**
     * 风速：2605
     * @return
     */
    public static CodecAPDU windSpeed() {
        return getRequestNormal(CommonOADFactory.windSpeed());
    }

    /**
     * 风向：2606
     * @return
     */
    public static CodecAPDU windDirection() {
        return getRequestNormal(CommonOADFactory.windDirection());
    }

    /**
     * 转速：2607
     * @return
     */
    public static CodecAPDU speed() {
        return getRequestNormal(CommonOADFactory.speed());
    }
}
