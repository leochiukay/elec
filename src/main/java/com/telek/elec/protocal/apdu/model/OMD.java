package com.telek.elec.protocal.apdu.model;

import com.telek.elec.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 4字节
 * 00 10 01 00 —— OMD
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OMD implements IResult {

    /**
     * 对象标识OI，标识终端中对象唯一名称的编码，2字节。如0010-正向有功电能
     *
     */
    private int oi;
    /**
     * 1字节
     * 方法标识——即对象方法编号。
     */
    private int index;
    /**
     * 1字节
     * 操作模式——值默认为0。
     */
    private int model;

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(oi), 4));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(index), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(model), 2));
        return sb.toString();
    }

    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null || onlyThisHexStr.length() != 8) {
            return;
        }
        this.oi = Integer.parseInt(onlyThisHexStr.substring(0, 4), 16);
        this.index = Integer.parseInt(onlyThisHexStr.substring(4, 6), 16);
        this.model = Integer.parseInt(onlyThisHexStr.substring(6), 16);
    }
}
