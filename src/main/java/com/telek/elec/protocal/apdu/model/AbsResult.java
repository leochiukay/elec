package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public abstract class AbsResult {

    /**
     * 将当前对象编码成十六进制字符串
     * @return
     */
    public String encode() throws EncodeException {
        validateEncode();
        return encodeSpecial();
    }

    /**
     * 将该字符串解码成当前对象
     * @param hexString
     * @return 返回该对象所占字符串长度
     */
    public int decode(String hexString) throws DecodeException {
        validateDecode();
        return decodeSpecial(hexString);
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
}
