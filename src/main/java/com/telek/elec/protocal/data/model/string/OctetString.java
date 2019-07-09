package com.telek.elec.protocal.data.model.string;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 字符串
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12 —— 通信地址：123456789012
 */
@Data
public class OctetString extends AbsString {

    public OctetString() {
        this.dataType = DataType.OCTET_STRING;
    }

    public OctetString(String value) {
        this();
        this.str = value;
        this.size = value.length() / 2;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return StringUtils.subLastNumStr(Integer.toHexString(size), 2) + str;
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.size = Integer.parseInt(hexString.substring(0, index += 2), 16);
        this.str = hexString.substring(index, index += size * 2);
        return size + size * 2;
    }
}
