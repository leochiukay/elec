package com.telek.elec.protocal.apdu.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * get result结果类型
 */
@Getter
@AllArgsConstructor
public enum DataResultType {

    DAR(0, "错误信息，dar"), DATA(1, "正确数据");

    int code;

    String msg;

    public static DataResultType getByCode(int code) {
        for (DataResultType value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
