package com.telek.elec.protocal.data.model.complex;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

import java.util.Objects;

/**
 * 对象标识数据类型--2字节
 * 对象标识OI，标识终端中对象唯一名称的编码，2字节。如0010-正向有功电能
 */
@Data
public class OI extends AbsComplexData {

    private static final int OI_CHAR_LENGTH = 4;

    private int oi;

    public OI() {
        this.dataType = DataType.OI;
    }

    public OI(int oi) {
        this();
        this.oi = oi;
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(Integer.toHexString(oi), OI_CHAR_LENGTH);
    }

    @Override
    protected int decodeSpecial(String hexString) {
        this.oi = Integer.parseInt(hexString.substring(0, OI_CHAR_LENGTH), 16);
        return OI_CHAR_LENGTH;
    }

    @Override
    protected void calculateCharLength() {
        this.charLength = OI_CHAR_LENGTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OI oi1 = (OI) o;
        return oi == oi1.oi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oi);
    }
}
