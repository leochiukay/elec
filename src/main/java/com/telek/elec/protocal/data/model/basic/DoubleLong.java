package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class DoubleLong extends NumericalData {

    private static final int CHAR_LENGTH = 8;

    /**
     * 4字节
     */
    private int value;

    public DoubleLong() {
        this.dataType = DataType.DOUBLE_LONG_UNSIGNED;
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
        this.value = java.lang.Integer.parseInt(hexExcludeDataType.substring(0, CHAR_LENGTH), 16);
        return CHAR_LENGTH;
    }

    public double getValue() {
        return value;
    }
}
