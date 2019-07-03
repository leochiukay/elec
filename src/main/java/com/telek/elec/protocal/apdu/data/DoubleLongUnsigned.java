package com.telek.elec.protocal.apdu.data;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class DoubleLongUnsigned extends IData {

    /**
     * 4字节
     */
    private long value;

    public DoubleLongUnsigned() {
        super(DataType.DOUBLE_LONG_UNSIGNED);
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Long.toHexString(value), 8);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.value = java.lang.Long.parseLong(hexExcludeDataType.substring(0, 8), 16);
        return 8;
    }
}
