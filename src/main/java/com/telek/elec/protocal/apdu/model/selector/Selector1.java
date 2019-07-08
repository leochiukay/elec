package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
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
public class Selector1 extends Selector {

    private OAD oad;

    private AbsData data;

    public Selector1() {
        this.id = 1;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(data.encode());
        return sb.toString();
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

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return oad.getCharLength() + data.getCharLength();
    }

}
