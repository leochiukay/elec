package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 4字节
 * 00 10 01 00 —— OMD
 */
@Data
public class OMD extends AbsData {

    public static final int OMD_CHAR_LENGTH = 8;

    private OI oi;
    /**
     * 1字节
     * 方法标识——即对象方法编号。
     */
    private int methodNum;
    /**
     * 1字节
     * 操作模式——值默认为0。
     */
    private int model;

    public OMD() {
        this.dataType = DataType.OMD;
    }

    public OMD(OI oi, int methodNum, int model) {
        this();
        this.oi = oi;
        this.methodNum = methodNum;
        this.model = model;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oi.encode());
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(methodNum), 2));
        sb.append(StringUtils.subLastNumStr(java.lang.Integer.toHexString(model), 2));
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        OI oi = new OI();
        int oiCharLen = oi.decode(hexString);
        this.oi = oi;
        this.methodNum = java.lang.Integer.parseInt(hexString.substring(oiCharLen, oiCharLen += 2), 16);
        this.model = java.lang.Integer.parseInt(hexString.substring(oiCharLen, oiCharLen += 2), 16);
        return OMD_CHAR_LENGTH;
    }

    @Override
    protected int calculateSpecialCharLength() {
        return OMD_CHAR_LENGTH;
    }
}
