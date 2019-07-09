package com.telek.elec.protocal.data.model.number;

import com.telek.elec.protocal.constant.DataType;

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

    public DoubleLong(int value) {
        this();
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    protected void setValue(long value) {
        this.value = (int) value;
    }

    @Override
    protected int getSpecialCharLength() {
        return CHAR_LENGTH;
    }
}
