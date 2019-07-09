package com.telek.elec.protocal.data.model.string;

import java.io.UnsupportedEncodingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

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
        this.isEncodeDataType = isEncodeDataType;
        this.str = value;
        this.size = this.str.getBytes().length;
    }


    @Override
    protected String encodeSpecial() throws EncodeException {
        try {
            byte[] asciis = this.str.getBytes("utf-8");
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
            this.str = new String(HexBin.decode(hexString.substring(index, index += size * 2)), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return size + size * 2;
    }
}
