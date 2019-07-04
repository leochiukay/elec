package com.telek.elec.protocal.data;

import com.telek.elec.protocal.data.model.DateTime;
import com.telek.elec.protocal.data.model.DateTimeS;
import com.telek.elec.protocal.data.model.DoubleLongUnsigned;
import com.telek.elec.protocal.data.model.AbsBasicData;
import com.telek.elec.protocal.data.model.Long;
import com.telek.elec.protocal.data.model.LongUnsigned;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.OctString;
import com.telek.elec.protocal.data.model.Unsigned;
import com.telek.elec.protocal.constant.DataType;

import lombok.Data;

/**
 * 十六进制字符串转数据
 */
@Data
public class HexToDataConvertor {

    /**
     * 十六进制字符串转数据
     * 字符串
     *  * 09 —— octet-string
     *  * 06 —— SIZE(6)
     *  * 12 34 56 78 90 12 —— 通信地址：123456789012
     * @param hex
     * @return
     */
    public static AbsBasicData hexToData(String hex) {
        if (hex == null) {
            return null;
        }
        // 1 获取该数据的数据类型
        int dataType = java.lang.Integer.parseInt(hex.substring(0, 2), 16);
        // 2 解码十六进制字符串
        AbsBasicData iData = null;
        if (dataType == DataType.NULL.getCode()) {
            iData = new Null();
        }
        if (dataType == DataType.UNSIGNED.getCode()) {
            iData = new Unsigned();
        }
        if (dataType == DataType.LONG.getCode()) {
            iData = new Long();
        }
        if (dataType == DataType.LONG_UNSIGNED.getCode()) {
            iData = new LongUnsigned();
        }
        if (dataType == DataType.DOUBLE_LONG_UNSIGNED.getCode()) {
            iData = new DoubleLongUnsigned();
        }
        if (dataType == DataType.DATE_TIME.getCode()) {
            iData = new DateTime();
        }
        if (dataType == DataType.DATE_TIME_S.getCode()) {
            iData = new DateTimeS();
        }
        if (dataType == DataType.OCTET_STRING.getCode()) {
            iData = new OctString();
        }
        iData.decode(hex);
        return iData;
    }

}
