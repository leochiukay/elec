package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.IData;

import lombok.Data;

/**
 * 数据信息
 * eg：
 *      * 09 —— octet-string
 *      * 06 —— SIZE(6)
 *      * 12 34 56 78 90 12 —— 通信地址：123456789012
 */
@Data
public class DataInfo extends IResult {

    private IData data;

    @Override
    protected String encodeSpecial() {
        return data.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) {
        this.data = HexToDataConvertor.hexToData(hexString);
        return data.getCharLength();
    }
}
