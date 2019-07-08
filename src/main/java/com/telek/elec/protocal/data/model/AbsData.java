package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.apdu.codec.AbsCodec;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class AbsData extends AbsCodec implements IData {

    protected static final int DATA_TYPE_CHAR_LENGTH = 2;

    /**
     * 是否编码data_type字段
     */
    protected boolean isEncodeDataType;

    /**
     * 数据类型-1字节：
     * 基本数据类型和复杂数据类型虽然都有该字段，
     * 但是基本数据类型编解码的时候头两个字符串为该字段，
     * 而复杂数据类型不包括
     */
    protected DataType dataType;

    /**
     * 总共占用字符串长度
     */
    protected int charLength;

    /**
     * 将当前对象编码成十六进制字符串
     * @return
     */
    @Override
    public String encode() throws EncodeException {
        validateEncode();
        StringBuilder sb = new StringBuilder();
        String common = encodeCommon();
        sb.append(common);
        String special = encodeSpecial();
        if (special != null) {
            sb.append(special);
        }
        int commonCharLen = 0;
        if (isEncodeDataType) {
            commonCharLen = 2;
        }
        this.charLength = commonCharLen + calculateSpecialCharLength();
        return sb.toString();
    }

    /**
     * 编码通用属性
     * @return
     */
    public String encodeCommon() {
        if (isEncodeDataType) {
            return StringUtils.subLastNumStr(Integer.toHexString(dataType.getCode()), 2);
        } else {
            return "";
        }
    }

    /**
     * 将该字符串解码成当前对象
     * @param hexString
     * @return 返回该对象所占字符串长度
     */
    @Override
    public int decode(String hexString) throws DecodeException {
        validateDecode();
        int commonIndex = decodeCommon(hexString);
        int i = decodeSpecial(hexString.substring(commonIndex));
        this.charLength = i + commonIndex;
        return charLength;
    }

    /**
     * 解码通用属性
     * @return
     */
    public int decodeCommon(String hex) {
        if (isEncodeDataType) {
            return DATA_TYPE_CHAR_LENGTH;
        } else {
            return 0;
        }
    }

    /**
     * 计算字符串长度
     */
    protected abstract int calculateSpecialCharLength() throws EncodeException;

}
