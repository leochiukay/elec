package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.Datas;
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

    private static final int ID = 1;

    private OAD oad;

    private Datas data;

    public Selector1() {
        this.id = 1;
    }

    public Selector1(OAD oad, Datas data) {
        this();
        this.oad = oad;
        this.data = data;
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
        int index = 0;
        OAD oad = new OAD();
        index += oad.decode(hexString.substring(index));
        this.oad = oad;
        AbsData absData = HexToDataConvertor.hexToData(hexString.substring(index));
        this.data = new Datas(absData);
        return index + absData.getCharLength();
    }

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return oad.getCharLength() + data.getData().getCharLength();
    }

}
