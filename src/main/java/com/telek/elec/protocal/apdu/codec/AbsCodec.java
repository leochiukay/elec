package com.telek.elec.protocal.apdu.codec;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public abstract class AbsCodec implements ICodec {

    /**
     * 编码校验
     * @throws EncodeException
     */
    protected void validateEncode() throws EncodeException{
    }

    /**
     * 解码校验
     * @throws EncodeException
     */
    protected void validateDecode() throws DecodeException {
    }

    /**
     * 子类编码自己特有属性
     * @return
     */
    protected abstract String encodeSpecial() throws EncodeException;

    /**
     * 子类解码自己特有属性
     * @param hexString
     * @return
     */
    protected abstract int decodeSpecial(String hexString) throws DecodeException;

}
