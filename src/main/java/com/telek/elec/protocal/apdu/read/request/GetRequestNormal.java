package com.telek.elec.protocal.apdu.read.request;

import com.telek.elec.protocal.apdu.model.OAD;
import com.telek.elec.protocal.apdu.read.CommonGet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.GetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 读取一个对象属性请求
 * 05 01 01 40 01 02 00 00
 * 05 —— [5] GET-Request
 * 01 —— [1] GetRequestNormal
 * 01 —— PIID
 * 40 01 02 00 —— OAD：通信地址40010200
 * 00 —— 没有时间标签
 */
@Data
public class GetRequestNormal extends CommonGet {

    private OAD oad;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public GetRequestNormal() {
        this.apduSequence = APDUSequence.GET_REQUEST;
        this.getType = GetType.NORMAL;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        if (oad != null) {
            sb.append(oad.encodeToHex());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        OAD oad = new OAD();
        oad.setOi(Integer.parseInt(hexString.substring(6, 10), 16));
        oad.setAttr(Integer.parseInt(hexString.substring(10, 12), 16));
        oad.setIndex(Integer.parseInt(hexString.substring(12, 14), 16));
        this.oad = oad;
        this.timeStamp = Integer.parseInt(hexString.substring(14),16);
    }
}
