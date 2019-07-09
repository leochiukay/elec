package com.telek.elec.protocal.data.model.number;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public class Long extends NumericalData {

    private static final int CHAR_LENGTH = 4;

    /**
     * 2字节
     */
    private short value;

    public Long(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public Long() {
        this.dataType = DataType.LONG;
    }

    public Long(short value) {
        this();
        this.value = value;
    }

    @Override
    protected void setValue(long value) {
        this.value = (short) value;
    }

    @Override
    protected int getSpecialCharLength() {
        return charLength;
    }

    public long getValue() {
        return value;
    }
}
