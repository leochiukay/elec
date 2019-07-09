package com.telek.elec.protocal.data.model;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * @Auther: wll
 * @Date: 2019/7/8 21:13
 * @Description:
 */
@Data
public class ROAD extends AbsData {

    private OAD oad;

    private List<OAD> sequenceOfData = new ArrayList<>();

    public ROAD() {
        this.dataType = DataType.ROAD;
    }

    public ROAD(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return sequenceOfData.size() * 8 + 2 + 8;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(sequenceOfData.size()), 2));
        for (OAD data : sequenceOfData) {
            sb.append(data.encode());
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int charLength = 0;
        OAD oad = new OAD();
        oad.decode(hexString);
        this.oad = oad;
        charLength += oad.getCharLength();
        int size = java.lang.Integer.parseInt(hexString.substring(0, charLength), 16);
        charLength += 2;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                OAD sequenceOad = new OAD();
                sequenceOad.decode(hexString);
                this.sequenceOfData.add(sequenceOad);
                charLength += sequenceOad.getCharLength();
            }
        }
        return charLength;
    }
}
