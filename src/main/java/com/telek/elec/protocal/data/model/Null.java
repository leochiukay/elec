package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public class Null extends AbsData {

    public Null() {
        this.dataType = DataType.NULL;
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
