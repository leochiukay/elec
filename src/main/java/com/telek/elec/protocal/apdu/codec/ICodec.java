package com.telek.elec.protocal.apdu.codec;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public interface ICodec {

    /**
     * 将当前对象编码成十六进制字符串
     * @return
     */
    String encode() throws EncodeException;

    /**
     * 将该字符串解码成当前对象
     * @param hexString
     * @return 返回该对象所占字符串长度
     */
    int decode(String hexString) throws DecodeException;

}
