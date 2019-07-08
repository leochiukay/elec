package com.telek.elec.protocal.apdu.read.request;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.read.Get;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

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
public class GetRequestNormalList extends ResTypeCodecAPDU implements Get {
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
        super(APDUResType.GET_NORMAL_LIST);
        this.apduSequence = APDUSequence.GET_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(oadSequence), 2));
        if (oadSequence > 0 && oads != null) {
            for (OAD oad : oads) {
                sb.append(oad.encode());
            }
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.oadSequence = Integer.parseInt(hexString.substring(index, index += 2), 16);

        if (oadSequence > 0) {
            oads = new ArrayList<>(oadSequence);
            for (int i = 0; i < oadSequence; i++) {
                String oadHex = hexString.substring(index, index += 8);
                OAD oad = new OAD();
                oad.decode(oadHex);
                oads.add(oad);
            }
        }
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (oadSequence < 0 || (oadSequence > 0 && (oads == null || oads.size() != oadSequence))) {
            throw new EncodeException("oadSequence和oads个数不匹配");
        }
    }
}
