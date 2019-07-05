package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public abstract class CodecAPDU extends DecoderAPDU {

    /**
     * 除了通用属性以外的十六进制解码开始下标位置
     */
    protected int decodeHexExcludeCommonBeginIndex;

    public CodecAPDU() {
        int index = APDU_SEQUENCE_CHAR_LENGTH;
        if (hasPiidFied()) {
            index += PIID_CHAR_LENGTH;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
    }

    /**
     * 解码
     * 1、作为服务端，将请求的十六进制字符串解码成该对象
     * 2、作为客户端，将服务端响应的十六进制响应数据解码到该对象
     * @param hexString
     * @throws DecodeException
     */
    public void decode(String hexString) throws DecodeException {
        decodeHexToThis(hexString);
    }

    /**
     * 编码
     * 1、作为客户端，将该对象编码成十六进制字符串发送请求
     * 2、作为服务端，将响应数据编码成十六进制发送给客户端
     * @return
     * @throws EncodeException
     */
    public String encode() throws EncodeException {
        return encodeThisToHex();
    }

}
