package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public class Null extends AbsData {

    public Null() {
        this.dataType = DataType.NULL;
    }

    public Null(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    @Override
    protected int calculateSpecialCharLength() {
        return 0;
    }

    @Override
    protected String encodeSpecial() {
        return null;
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        return 0;
    }
}
