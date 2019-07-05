package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public abstract class AbsData implements IData {

    protected static final int DATA_TYPE_CHAR_LENGTH = 2;

    /**
     * 数据类型-1字节：
     * 基本数据类型和复杂数据类型虽然都有该字段，
     * 但是基本数据类型编解码的时候头两个字符串为该字段，
     * 而复杂数据类型不包括
     */
    protected DataType dataType;

    /**
     * 总共占用字符串长度
     */
    protected int charLength;

}
