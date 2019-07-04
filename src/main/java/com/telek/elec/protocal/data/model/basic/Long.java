package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class Long extends NumericalData {

    private static final int CHAR_LENGTH = 4;

    /**
     * 2字节
     */
    private short value;

    public Long() {
        this.dataType = DataType.LONG;
    }

    @Override
    protected int calculateSpecialCharLength() {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Double.toHexString(value), CHAR_LENGTH);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.value = java.lang.Short.parseShort(hexExcludeDataType.substring(0, CHAR_LENGTH), 16);
        return CHAR_LENGTH;
    }

    public double getValue() {
        return value;
    }
}
