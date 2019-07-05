package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * 数据信息
 * eg：
 *      * 09 —— octet-string
 *      * 06 —— SIZE(6)
 *      * 12 34 56 78 90 12 —— 通信地址：123456789012
 */
@Data
public class DataInfo extends AbsResult {

    private AbsData data;

    public DataInfo() {
    }

    public DataInfo(AbsData data) {
        this.data = data;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return data.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        this.data = HexToDataConvertor.hexToData(hexString);
        return data.getCharLength();
    }
}
