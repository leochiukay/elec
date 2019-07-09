package com.telek.elec.protocal.data.model.string;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 字符串
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12
 */
@Data
public abstract class AbsString extends AbsData {

    protected static final int SIZE_CHAR_LENGTH = 2;

    /**
     * 1字节
     * 长度--标识后面有几个十六进制
     */
    protected int size;
    /**
     * 字节数组，具体形式见子类
     */
    protected byte[] value;

    @Override
    protected String encodeSpecial() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(size), SIZE_CHAR_LENGTH));
        if (value != null) {
            sb.append(HexBin.encode(value));
        }
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.size = java.lang.Integer.parseInt(hexExcludeDataType.substring(0, SIZE_CHAR_LENGTH), 16);
        String valueStr = hexExcludeDataType.substring(SIZE_CHAR_LENGTH, SIZE_CHAR_LENGTH + size * 2);
        this.value = HexBin.decode(valueStr);
        return 2 + size * 2;
    }

    @Override
    protected int calculateSpecialCharLength() {
        return value != null ? SIZE_CHAR_LENGTH + value.length : SIZE_CHAR_LENGTH;
    }

}
