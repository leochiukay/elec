package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.AbsResult;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * Selector1 为选择对象的指定值。
 */
@Data
public class Selector1 extends AbsResult {

    private OAD oad;

    private AbsData data;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(data.encode());
        return null;
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        OAD oad = new OAD();
        int oadCharLen = oad.decode(hexString);
        this.oad = oad;
        String dataStr = hexString.substring(oadCharLen);
        AbsData absData = HexToDataConvertor.hexToData(dataStr);
        this.data = absData;
        return oadCharLen + absData.getCharLength();
    }
}
