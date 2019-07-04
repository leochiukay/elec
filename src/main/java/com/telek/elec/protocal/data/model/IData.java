package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * 数据类型的顶层包
 */
public interface IData {

    /**
     * 编码： 基本类型和复杂数据类型结构不一致
     * 基本数据类型头两个字符串标识该数据类型 如：字符串，下面09即表示data_type
     *  *                                      09 —— octet-string
     *  *                                      06 —— SIZE(6)
     *  *                                      12 34 56 78 90 12 —— 通信地址：123456789012
     *  而复杂数据类型包下的不包括 该字段
     * @return
     */
    String encode() throws EncodeException;

    /**
     * 解码
     */
    int decode(String hexString) throws DecodeException;

}
