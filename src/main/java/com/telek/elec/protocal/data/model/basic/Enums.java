package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

/**
 * 枚举
 */
public class Enums extends AbsBasicData {

    private static final int CHAR_LENGTH = 2;

    /**
     * 1字节
     */
    private short value;

    public Enums() {
        this.dataType = DataType.ENUM;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return StringUtils.subLastNumStr(java.lang.Integer.toHexString(value), 2);
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) throws DecodeException {
        this.value = Short.parseShort(hexExcludeDataType.substring(0, CHAR_LENGTH), 16);
        return CHAR_LENGTH;
    }
}
