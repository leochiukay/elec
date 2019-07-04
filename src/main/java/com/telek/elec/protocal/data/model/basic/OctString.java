package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 字符串
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12 —— 通信地址：123456789012
 */
@Data
public class OctString extends AbsBasicData {

    private static final int SIZE_CHAR_LENGTH = 2;

    /**
     * 1字节
     * 长度--标识后面有几个十六进制
     */
    private int size;
    /**
     * 字符串十六进制
     */
    private String value;

    public OctString() {
        this.dataType = DataType.OCTET_STRING;
    }

    @Override
    protected int calculateSpecialCharLength() {
        return value != null ? SIZE_CHAR_LENGTH + value.length() : SIZE_CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(size), SIZE_CHAR_LENGTH));
        sb.append(value);
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.size = java.lang.Integer.parseInt(hexExcludeDataType.substring(0, SIZE_CHAR_LENGTH), 16);
        int charLength = SIZE_CHAR_LENGTH + size * 2;
        this.value = hexExcludeDataType.substring(SIZE_CHAR_LENGTH, charLength);
        return 2 + size * 2;
    }

}
