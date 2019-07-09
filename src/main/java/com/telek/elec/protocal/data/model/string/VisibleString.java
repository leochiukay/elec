package com.telek.elec.protocal.data.model.string;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * 字符串
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12---ASCII码
 */
@Data
public class VisibleString extends AbsString {

    public VisibleString() {
        this.dataType = DataType.OCTET_STRING;
    }

    public VisibleString(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public VisibleString(byte[] value, boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
        if (value == null) {
            size = 0;
        } else {
            this.value = value;
            this.size = value.length;
        }
    }
}
