package com.telek.elec.protocal.data.model.string;

import java.io.UnsupportedEncodingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

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

    public VisibleString(String str, boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
        this.str = str;
        this.size = str.getBytes().length;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        try {
            byte[] asciis = this.str.getBytes("ascii");
            return StringUtils.subLastNumStr(Integer.toHexString(size), 2) + HexBin.encode(asciis);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.size = Integer.parseInt(hexString.substring(0, index += 2), 16);
        try {
            this.str = new String(HexBin.decode(hexString.substring(index, index += size * 2)), "ascii");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return size + size * 2;
    }
}
