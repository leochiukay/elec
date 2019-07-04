package com.telek.elec.protocal.data.model.basic;

import java.math.BigDecimal;

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

    protected abstract double getValue();

}
