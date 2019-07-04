package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class Unsigned extends AbsBasicData {

    private static final int CHAR_LENGTH = 2;

    /**
     * 1字节
     */
    private int value;

    public Unsigned() {
        super(DataType.UNSIGNED);
    }

    @Override
    protected int calculateSpecialCharLength() {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Integer.toHexString(value), CHAR_LENGTH);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.value = java.lang.Integer.parseInt(hexExcludeDataType.substring(0, CHAR_LENGTH), 16);
        return CHAR_LENGTH;
    }
}
