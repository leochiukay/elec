package com.telek.elec.protocal.apdu.model;

public interface IResult {

    /**
     * 将当前对象编码成十六进制字符串
     * @return
     */
    String encode();

    /**
     * 将该字符串解码成当前对象
     * @param onlyThisHexStr 仅截取当前需要解码长度的字符串，不包括对象以外的其它多余字符串
     */
    void decode(String onlyThisHexStr);

}
