package com.telek.elec.protocal.data;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.basic.DateTime;
import com.telek.elec.protocal.data.model.basic.DateTimeS;
import com.telek.elec.protocal.data.model.basic.DoubleLongUnsigned;
import com.telek.elec.protocal.data.model.basic.Long;
import com.telek.elec.protocal.data.model.basic.LongUnsigned;
import com.telek.elec.protocal.data.model.basic.Null;
import com.telek.elec.protocal.data.model.basic.OctString;
import com.telek.elec.protocal.data.model.basic.Unsigned;
import com.telek.elec.protocal.data.model.complex.OAD;
import com.telek.elec.protocal.data.model.complex.OI;
import com.telek.elec.protocal.data.model.complex.OMD;
import com.telek.elec.protocal.exeception.DecodeException;

import lombok.Data;

/**
 * 十六进制字符串转数据
 */
@Data
public class HexToDataConvertor {

    /**
     * 十六进制字符串转数据,无论是基本数据类型还是复杂数据类型
     * 头两位必须为data_type类型十六进制，
     * 如果像复杂数据类型没有就自己补充
     *
     * 字符串
     *  * 09 —— octet-string
     *  * 06 —— SIZE(6)
     *  * 12 34 56 78 90 12 —— 通信地址：123456789012
     * @param hexIncludeDataType
     * @return
     */
    public static AbsData hexToData(String hexIncludeDataType) throws DecodeException {
        if (hexIncludeDataType == null) {
            return null;
        }
        // 1 获取该数据的数据类型
        int dataType = java.lang.Integer.parseInt(hexIncludeDataType.substring(0, 2), 16);
        // 2 解码十六进制字符串
        AbsData iData = null;
        /****************************************************/
        /**************基本数据类型***************************/
        /****************************************************/
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
        /****************************************************/
        /**************复杂数据类型***************************/
        /****************************************************/
        if (dataType == DataType.OI.getCode()) {
            iData = new OI();
        }
        if (dataType == DataType.OMD.getCode()) {
            iData = new OMD();
        }
        if (dataType == DataType.OAD.getCode()) {
            iData = new OAD();
        }
        iData.decode(hexIncludeDataType);
        return iData;
    }

}
