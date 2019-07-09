package com.telek.elec.protocal.data;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.Array;
import com.telek.elec.protocal.data.model.CSD;
import com.telek.elec.protocal.data.model.Comdcb;
import com.telek.elec.protocal.data.model.MS;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.ROAD;
import com.telek.elec.protocal.data.model.RSD;
import com.telek.elec.protocal.data.model.TI;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.date.DateTime;
import com.telek.elec.protocal.data.model.date.DateTimeS;
import com.telek.elec.protocal.data.model.Enums;
import com.telek.elec.protocal.data.model.Null;
import com.telek.elec.protocal.data.model.Structure;
import com.telek.elec.protocal.data.model.number.DoubleLong;
import com.telek.elec.protocal.data.model.number.DoubleLongUnsigned;
import com.telek.elec.protocal.data.model.number.Integer;
import com.telek.elec.protocal.data.model.number.Long;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.data.model.string.OctetString;
import com.telek.elec.protocal.data.model.string.UTF8String;
import com.telek.elec.protocal.data.model.string.VisibleString;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.OI;
import com.telek.elec.protocal.data.model.OMD;
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
     * <p>
     * 字符串
     * * 09 —— octet-string
     * * 06 —— SIZE(6)
     * * 12 34 56 78 90 12 —— 通信地址：123456789012
     *
     * @param hexIncludeDataType
     * @return
     */
    public static AbsData hexToData(String hexIncludeDataType) throws DecodeException {
        if (hexIncludeDataType == null) {
            return null;
        }
        // 1 获取该数据的数据类型
        int dataTypeInt = java.lang.Integer.parseInt(hexIncludeDataType.substring(0, 2), 16);
        DataType dataType = DataType.decode(dataTypeInt);
        if (dataType == null) {
            return null;
        }
        // 2 解码十六进制字符串
        AbsData iData = null;
        switch (dataType) {
            case NULL:
                iData = new Null();
                break;
            case ARRAY:
                iData = new Array();
                break;
            case STRUCTURE:
                iData = new Structure();
                break;
            case BOOL:
                break;
            case BIT_STRING:
                break;
            case DOUBLE_LONG:
                iData = new DoubleLong();
                break;
            case DOUBLE_LONG_UNSIGNED:
                iData = new DoubleLongUnsigned();
                break;
            case OCTET_STRING:
                iData = new OctetString();
                break;
            case VISIBLE_STRING:
                iData = new VisibleString();
                break;
            case UTF8_STRING:
                iData = new UTF8String();
                break;
            case INTEGER:
                iData = new Integer();
                break;
            case LONG:
                iData = new Long();
                break;
            case UNSIGNED:
                iData = new Unsigned();
                break;
            case LONG_UNSIGNED:
                iData = new LongUnsigned();
                break;
            case LONG64:
                break;
            case LONG64_UNSIGNED:
                break;
            case ENUM:
                iData = new Enums();
                break;
            case FLOAT32:
                break;
            case FLOAT64:
                break;
            case DATE_TIME:
                iData = new DateTime();
                break;
            case DATE:
                break;
            case TIME:
                break;
            case DATE_TIME_S:
                iData = new DateTimeS();
                break;
            case OI:
                iData = new OI();
                break;
            case OAD:
                iData = new OAD();
                break;
            case ROAD:
                iData = new ROAD();
                break;
            case OMD:
                iData = new OMD();
                break;
            case TI:
                iData = new TI();
                break;
            case TSA:
                iData = new TSA();
                break;
            case MAC:
                break;
            case RN:
                break;
            case REGION:
                break;
            case SCALER_UNIT:
                break;
            case RSD:
                iData = new RSD();
                break;
            case CSD:
                iData = new CSD();
                break;
            case MS:
                iData = new MS();
                break;
            case SID:
                break;
            case SID_MAC:
                break;
            case COMDCB:
                iData = new Comdcb();
                break;
            case RCSD:
                iData = new RCSD();
                break;
        }
        if (iData != null) {
            iData.setEncodeDataType(true);
            iData.decode(hexIncludeDataType);
        }
        return iData;
    }
}
