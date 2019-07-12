package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.Datas;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Selector2 extends Selector {

    private static final int ID = 2;

    private OAD oad;

    private Datas begin;

    private Datas end;

    private Datas dataInterval;

    public Selector2() {
        this.id = ID;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(begin.encode());
        sb.append(end.encode());
        sb.append(dataInterval.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.oad = new OAD();
        index += oad.decode(hexString);
        this.begin = new Datas(HexToDataConvertor.hexToData(hexString.substring(index)));
        index += begin.getData().getCharLength();
        this.end = new Datas(HexToDataConvertor.hexToData(hexString.substring(index)));
        index += end.getData().getCharLength();
        this.dataInterval = new Datas(HexToDataConvertor.hexToData(hexString.substring(index)));
        index += dataInterval.getData().getCharLength();
        return index;
    }

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return oad.getCharLength() + begin.getData().getCharLength() + end.getData().getCharLength() + dataInterval.getData().getCharLength();
    }

}
