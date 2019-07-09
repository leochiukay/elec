package com.telek.elec.protocal.data.model.number;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * 整数
 */
@Data
public class Integer extends NumericalData {
    private static final int CHAR_LENGTH = 2;

    /**
     * 1字节
     */
    private byte value;

    public Integer() {
        this.dataType = DataType.INTEGER;
    }

    public Integer(byte value) {
        this();
        this.value = value;
    }

    @Override
    protected void setValue(long value) {
        this.value = (byte) value;
    }

    @Override
    protected int getSpecialCharLength() {
        return charLength;
    }

    public long getValue() {
        return value;
    }
}
