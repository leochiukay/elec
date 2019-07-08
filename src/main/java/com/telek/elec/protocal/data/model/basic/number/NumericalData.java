package com.telek.elec.protocal.data.model.basic.number;

import java.math.BigDecimal;

import com.telek.elec.protocal.data.model.basic.AbsBasicData;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 数值型
 *
 */
@Data
public abstract class NumericalData extends AbsBasicData {

    /**
     * 换算系数
     */
    private int conversionCoefficient;


    @Override
    protected int calculateSpecialCharLength() {
        return getCharLength();
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Long.toHexString(getValue()), getCharLength());
    }

    @Override
    protected int decodeSpecial(String hexExcludeDataType) {
        setValue(java.lang.Long.parseLong(hexExcludeDataType.substring(0, getCharLength()), 16));
        return getCharLength();
    }

    /**
     * 获取换算系数后的真实值
     * @return
     */
    public double getRealValue(){
        int i = conversionCoefficient * 10;
        if (i > 0) {
            return getValue() * i;
        } else if (i < 0) {
            return new BigDecimal(getValue())
                    .divide(new BigDecimal(i), -conversionCoefficient, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
        } else {
            return getValue();
        }
    }

    protected abstract long getValue();

    protected abstract void setValue(long value);

}
