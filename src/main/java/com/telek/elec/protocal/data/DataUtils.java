package com.telek.elec.protocal.data;

import com.telek.elec.protocal.constant.DataType;

public class DataUtils {

    /**
     * 通过值获取该数据类型
     * @param dataType
     * @return
     */
    public static DataType getDataType(int dataType) {
        for (DataType value : DataType.values()) {
            if (value.getCode() == dataType) {
                return value;
            }
        }
        return null;
    }

}
