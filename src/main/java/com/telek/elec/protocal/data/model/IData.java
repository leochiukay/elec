package com.telek.elec.protocal.data.model;

/**
 * 数据类型的顶层包
 */
public interface IData {

    /**
     * 编码：
     * 基本类型和复杂数据类型结构不一致
     * 基本数据类型头两个字符串标识该数据类型 如：字符串，下面09即表示data_type
     *  *                                      09 —— octet-string
     *  *                                      06 —— SIZE(6)
     *  *                                      12 34 56 78 90 12 —— 通信地址：123456789012
     *  而复杂数据类型包下的不包括该字段 如 oad 4字节  00 10 01 00
     * @return
     */
    //String encode() throws EncodeException;

    /**
     * 解码:
     * 基本类型和复杂数据类型结构不一致
     * 基本数据类型头两个字符串标识该数据类型 如：字符串，下面09即表示data_type
     *  *                                      09 —— octet-string
     *  *                                      06 —— SIZE(6)
     *  *                                      12 34 56 78 90 12 —— 通信地址：123456789012
     *  而复杂数据类型包下的不包括该字段 如 oad 4字节  00 10 01 00
     *
     * @return 返回值为该对象所占用的整个字符串长度
     */
    //int decode(String hexString) throws DecodeException;

}
