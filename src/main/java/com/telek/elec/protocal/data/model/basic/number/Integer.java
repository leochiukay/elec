package com.telek.elec.protocal.data.model.basic.number;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * 整数
 */
@Data
public class Integer extends NumericalData {
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

    public long getValue() {
        return value;
    }
}
