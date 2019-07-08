package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class MS extends AbsData {

    public static final int MS_CHAR_LENGTH = 2;

    private int value;

    public MS() {
        this.dataType = DataType.MS;
    }

    public MS(int value) {
        this();
        this.value = value;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return MS_CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return StringUtils.subLastNumStr(Integer.toHexString(value), 2);
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        this.value = Integer.parseInt(hexString.substring(0, MS_CHAR_LENGTH), 16);
        return MS_CHAR_LENGTH;
    }
}
