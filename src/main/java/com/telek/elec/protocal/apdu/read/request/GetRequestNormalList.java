package com.telek.elec.protocal.apdu.read.request;

import java.util.List;

import com.telek.elec.protocal.apdu.model.OAD;
import com.telek.elec.protocal.apdu.read.CommonGet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.GetType;

import lombok.Data;

/**
 * 读取多个对象属性请求
 * 读取三相电压、电流
 * 发送：05 02 02 02 20 00 02 00 20 01 02 00 00
 * 05 —— [5] GET-Request
 * 02 —— [2] GetRequestNormalList
 * 02 —— PIID
 * 02 —— SEQUENCE OF OAD，个数=2
 * 20 00 02 00 —— OAD1：A，B，C相计量电压
 * 20 01 02 00 —— OAD2：A，B，C相计量电流
 * 00 —— 没有时间标签
 */
@Data
public class GetRequestNormalList extends CommonGet {
    /**
     * oad个数-1字节
     */
    private int oadSequence;
    /**
     * oad:根据oadSequence确定个数
     */
    List<OAD> oads;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public GetRequestNormalList() {
        this.apduSequence = APDUSequence.GET_REQUEST;
        this.getType = GetType.NORMAL_LIST;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        this.oadSequence = Integer.parseInt(hexString.substring(6, 8), 16);
        int end = 8;
        for (int i = 0; i < oadSequence; i++) {

        }
        this.timeStamp = Integer.parseInt(hexString.substring(end), 16);
    }
}
