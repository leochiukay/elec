package com.telek.elec.protocal.apdu.data;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class LongUnsigned extends IData {

    /**
     * 2字节
     */
    private long value;

    public LongUnsigned() {
        super(DataType.LONG);
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Long.toHexString(value), 4);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.value = java.lang.Long.parseLong(hexExcludeDataType.substring(0, 4), 16);
        return 4;
    }
}
