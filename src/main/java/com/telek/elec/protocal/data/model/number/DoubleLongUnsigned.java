package com.telek.elec.protocal.data.model.number;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public class DoubleLongUnsigned extends NumericalData {

    private static final int CHAR_LENGTH = 8;

    /**
     * 4字节
     */
    private long value;

    public DoubleLongUnsigned() {
        this.dataType = DataType.DOUBLE_LONG_UNSIGNED;
    }

    public DoubleLongUnsigned(long value) {
        this();
        this.value = value;
    }

    @Override
    protected void setValue(long value) {
        this.value = value;
    }

    @Override
    protected int getSpecialCharLength() {
        return charLength;
    }

    public long getValue() {
        return value;
    }
}
