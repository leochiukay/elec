package com.telek.elec.protocal.apdu.data;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class Unsigned extends IData {
    /**
     * 1字节
     */
    private int value;

    public Unsigned() {
        super(DataType.UNSIGNED);
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Long.toHexString(value), 2);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.value = Integer.parseInt(hexExcludeDataType.substring(0, 2), 16);
        return 2;
    }
}
