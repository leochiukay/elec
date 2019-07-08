package com.telek.elec.protocal.data.model.basic.number;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public class Unsigned extends NumericalData {

    private static final int CHAR_LENGTH = 2;

    /**
     * 1字节
     */
    private short value;

    public Unsigned() {
        this.dataType = DataType.UNSIGNED;
    }

    public Unsigned(short value) {
        this();
        this.value = value;
    }

    @Override
    protected void setValue(long value) {
        this.value = (short) value;
    }

    public long getValue() {
        return value;
    }
}
