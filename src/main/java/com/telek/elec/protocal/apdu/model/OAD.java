package com.telek.elec.protocal.apdu.model;

import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 对象标识,4字节，如00100200。
 */
@Data
public class OAD {

    /**
     * 对象标识OI，标识终端中对象唯一名称的编码，2字节。如0010-正向有功电能
     *
     */
    private int oi;
    /**
     * 属性:1字节
     */
    private int attr;
    /**
     * 下标：1字节
     */
    private int index;

    /**
     * 将该对象编码为hex
     * @return
     */
    public String encodeToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(oi), 4));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(attr), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(index), 2));
        return sb.toString();
    }

}
