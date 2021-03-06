package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.apdu.codec.AbsCodec;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class AbsData extends AbsCodec implements IData {

    private static final int DATA_TYPE_CHAR_LENGTH = 2;

    /**
     * 是否编码data_type字段
     */
    protected boolean isEncodeDataType;

    /**
     * 数据类型-1字节：
     */
    protected DataType dataType;

    /**
     * 总共占用字符串长度
     */
    protected int charLength;

    /**
     * 将当前对象编码成十六进制字符串
     *
     * @return
     */
    @Override
    public String encode() throws EncodeException {
        validateEncode();
        StringBuilder sb = new StringBuilder();
        String common = encodeCommon();
        if (common != null) {
            sb.append(common);
        }
        String special = encodeSpecial();
        if (special != null) {
            sb.append(special);
        }
        this.charLength = sb.length();
        return sb.toString();
    }

    /**
     * 编码通用属性
     *
     * @return
     */
    public String encodeCommon() {
        if (isEncodeDataType) {
            return StringUtils.subLastNumStr(Integer.toHexString(dataType.getCode()), 2);
        } else {
            return null;
        }
    }

    /**
     * 将该字符串解码成当前对象
     *
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
     *
     * @return
     */
    private int decodeCommon(String hex) {
        if (isEncodeDataType) {
            return DATA_TYPE_CHAR_LENGTH;
        } else {
            return 0;
        }
    }

    /**
     * 计算字符串长度
     */
    protected int calculateSpecialCharLength() throws EncodeException {
        this.encode();
        return this.getCharLength();
    }
}
