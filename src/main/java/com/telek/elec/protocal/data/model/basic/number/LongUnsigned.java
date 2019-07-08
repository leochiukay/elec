package com.telek.elec.protocal.data.model.basic.number;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public class LongUnsigned extends NumericalData {

    private static final int CHAR_LENGTH = 4;

    /**
     * 2字节
     */
    private int value;

    public LongUnsigned() {
        this.dataType = DataType.LONG_UNSIGNED;
    }

    public LongUnsigned(int value) {
        this();
        this.value = value;
    }

    @Override
    protected void setValue(long value) {
        this.value = (int) value;
    }

    public long getValue() {
        return value;
    }
}
