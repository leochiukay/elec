package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class TI extends AbsData {

    public static final int TI_CHAR_LENGTH = 6;

    /**
     * 单位 ENUMERATED-1 字节
     * {
     * 秒 （0），
     * 分 （1），
     * 时 （2），
     * 日 （3），
     * 月 （4），
     * 年 （5）
     */
    private byte unit;
    /**
     * 间隔值 long-unsigned-2字节
     */
    private int interVal;

    public TI() {
        this.dataType = DataType.TI;
    }

    public TI(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public TI(byte unit, int interVal, boolean isEncodeDataType) {
        this(isEncodeDataType);
        this.unit = unit;
        this.interVal = interVal;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return TI_CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(unit), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(interVal), 2));
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.unit = Byte.parseByte(hexString.substring(0, index += 2), 16);
        this.interVal = Integer.parseInt(hexString.substring(index, index += 4), 16);
        return TI_CHAR_LENGTH;
    }

}
