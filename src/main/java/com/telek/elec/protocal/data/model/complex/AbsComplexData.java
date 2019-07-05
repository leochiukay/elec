package com.telek.elec.protocal.data.model.complex;

import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * 复杂数据类型：和基本数据类型不同（没有头两个字符串表示数据类型，所有的字符串表示的是数据的值）
 * eg：
 * 4字节OAD
 *  * 00 10 01 00--------0010,01,00
 */
public abstract class AbsComplexData extends AbsData {

    /**
     * 将当前对象编码成十六进制字符串
     * @return
     */
    @Override
    public String encode() throws EncodeException {
        validateEncode();
        String res = encodeSpecial();
        calculateCharLength();
        return res;
    }

    /**
     * 将该字符串解码成当前对象
     * @param hexString
     * @return 返回该对象所占字符串长度
     */
    @Override
    public int decode(String hexString) throws DecodeException {
        validateDecode();
        int i = decodeSpecial(hexString);
        this.charLength = i;
        return i;
    }

    /**
     * 子类解码
     * @return
     */
    protected abstract String encodeSpecial() throws EncodeException;

    /**
     * 子类解码
     * @param hexString
     * @return
     */
    protected abstract int decodeSpecial(String hexString) throws DecodeException;

    /**
     * 编码校验
     * @throws EncodeException
     */
    protected void validateEncode() throws EncodeException {
    }

    /**
     * 解码校验
     * @throws EncodeException
     */
    protected void validateDecode() throws DecodeException {
    }

    /**
     * 计算字符串长度
     */
    protected abstract void calculateCharLength();

}
