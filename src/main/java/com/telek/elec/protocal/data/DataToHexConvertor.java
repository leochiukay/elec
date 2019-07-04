package com.telek.elec.protocal.data;

import com.telek.elec.protocal.data.model.AbsBasicData;

import lombok.Data;

/**
 * 数据转十六进制字符串
 */
@Data
public class DataToHexConvertor {

    /**
     * 数据转十六进制字符串
     * @param data
     * @return
     */
    public static String dataToHex(AbsBasicData data) {
        if (data == null) {
            return "";
        }
        return data.encode();
    }

}
