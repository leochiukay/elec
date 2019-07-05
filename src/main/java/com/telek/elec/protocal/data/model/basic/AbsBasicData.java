package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.data.DataUtils;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 基本数据类型: 头两个字符串表示该数据类型
 * eg：
 *  * 字符串
 *  * 09 —— octet-string
 *  * 06 —— SIZE(6)
 *  * 12 34 56 78 90 12 —— 通信地址：123456789012
 **/
@Data
public abstract class AbsBasicData extends AbsData {

    /**
     * 编码，将该数据类型编码成16进制字符串
     * @return 首2字符为数据类型
     */
    @Override
    public String encode() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(dataType.getCode()), DATA_TYPE_CHAR_LENGTH));
        String special = encodeSpecial();
        if (special != null) {
            sb.append(special);
        }
        // 计算char长度
        this.charLength = DATA_TYPE_CHAR_LENGTH + calculateSpecialCharLength();
        return sb.toString();
    }

    /**
     * 解码
     * @param hex 必须从数据类型的第一位开发，不足自己补  eg:09 —— octet-string-----标识数据类型为字符串
     *  *                                            06 —— SIZE(6)
     *  *                                            12 34 56 78 90 12 —— 字符串：123456789012
     * @return 返回该类型总共占用多少字符串长度，包括首位的数据类型2字符
     */
    @Override
    public int decode(String hex) throws DecodeException {
        int res = 0;
        if (hex == null) {
            return res;
        }
        int type = java.lang.Integer.parseInt(hex.substring(0, DATA_TYPE_CHAR_LENGTH), 16);
        this.dataType = DataUtils.getDataType(type);
        res = DATA_TYPE_CHAR_LENGTH;
        int charLength = res + decodeSpecial(hex.substring(2));
        this.charLength = charLength;
        return charLength;
    }

    /**
     * 子类计算自己字符串长度
     * @return
     */
    protected abstract int calculateSpecialCharLength() throws EncodeException;

    /**
     * 子类编码自己特有属性
     * @return
     */
    protected abstract String encodeSpecial() throws EncodeException;

    /**
     * 子类解码自有属性
     * @param hexExcludeDataType
     * @return
     */
    protected abstract int decodeSpecial(String hexExcludeDataType) throws DecodeException;
}
