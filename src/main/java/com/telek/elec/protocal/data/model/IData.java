package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * 数据类型的顶层包
 */
public interface IData {

    /**
     * 编码
     * @return
     */
    String encode() throws EncodeException;

    /**
     * 解码
     */
    int decode(String hexString) throws DecodeException;

}
