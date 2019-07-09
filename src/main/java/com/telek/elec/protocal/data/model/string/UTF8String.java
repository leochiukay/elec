package com.telek.elec.protocal.data.model.string;

import java.io.UnsupportedEncodingException;

import com.telek.elec.protocal.constant.DataType;

public class UTF8String extends AbsString {

    public UTF8String() {
        this.dataType = DataType.UTF8_STRING;
    }

    public UTF8String(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public UTF8String(String value, boolean isEncodeDataType) {
        this();
        try {
            this.isEncodeDataType = isEncodeDataType;
            this.value = value.getBytes("utf-8");
            this.size = this.value.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
