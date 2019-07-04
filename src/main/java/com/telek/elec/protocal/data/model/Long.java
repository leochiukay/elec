package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class Long extends AbsBasicData {

    private static final int CHAR_LENGTH = 4;

    /**
     * 2字节
     */
    private long value;

    public Long() {
        super(DataType.LONG);
    }

    @Override
    protected int calculateSpecialCharLength() {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Long.toHexString(value), CHAR_LENGTH);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.value = java.lang.Long.parseLong(hexExcludeDataType.substring(0, CHAR_LENGTH), 16);
        return CHAR_LENGTH;
    }
}
