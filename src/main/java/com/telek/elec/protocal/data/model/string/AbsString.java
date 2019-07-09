package com.telek.elec.protocal.data.model.string;

import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * 字符串
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12
 */
@Data
public abstract class AbsString extends AbsData {

    protected static final int SIZE_CHAR_LENGTH = 2;

    /**
     * 1字节
     * 长度--标识后面有几个十六进制
     */
    protected int size;
    /**
     * 字节数组，具体形式见子类
     */
    protected String str;

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return 2 + size * 2;
    }

}
