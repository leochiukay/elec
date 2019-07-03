package com.telek.elec.protocal.apdu.data;

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
public class OctString extends IData {

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
        super(DataType.OCTET_STRING);
    }

    @Override
    protected String encodeSpecial() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(size), 2));
        sb.append(value);
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        this.size = Integer.parseInt(hexExcludeDataType.substring(0, 2), 16);
        this.value = hexExcludeDataType.substring(2, 2 + size * 2);
        return 2 + size * 2;
    }

}
