package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

@Data
public abstract class AbsData implements IData {

    protected static final int DATA_TYPE_CHAR_LENGTH = 2;

    /**
     * 数据类型-1字节
     */
    protected DataType dataType;

    /**
     * 总共占用字符串长度（包括datatype）
     */
    protected int charLength;

}
