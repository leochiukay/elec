package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.basic.Enums;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:48
 * @Description:
 */
public class Region extends AbsData {
    private int unit;

    private AbsData start;

    private AbsData end;

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return 0;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sb = new StringBuffer();
        Enums enums = new Enums((short) unit);
        enums.setEncodeDataType(false);
        sb.append(enums.encode());
        sb.append(start.encode());
        sb.append(end.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int charLength = 0;
        Enums enums = new Enums();
        enums.setEncodeDataType(false);
        enums.decode(hexString);
        charLength += enums.getCharLength();
        this.unit = enums.getValue();
        AbsData start = HexToDataConvertor.hexToData(hexString.substring(charLength));
        this.start = start;
        charLength += start.getCharLength();
        AbsData end = HexToDataConvertor.hexToData(hexString.substring(charLength));
        this.end = end;
        charLength += end.getCharLength();
        return charLength;
    }
}
