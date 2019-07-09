package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

public class Comdcb extends AbsData {

    private static final int CHAR_LENGTH = 2 * 5;

    /**
     * 波特率
     */
    private short baudRate;
    /**
     * 检验位
     */
    private short calibration;
    /**
     * 数据位
     */
    private short dataBit;
    /**
     * 停止位
     */
    private short stopBit;
    /**
     * 流控
     */
    private short flowControl;


    public Comdcb() {
        this.dataType = DataType.COMDCB;
    }

    public Comdcb(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public Comdcb(short baudRate, short calibration, short dataBit, short stopBit, short flowControl, boolean isEncodeDataType) {
        this(isEncodeDataType);
        this.baudRate = baudRate;
        this.calibration = calibration;
        this.dataBit = dataBit;
        this.stopBit = stopBit;
        this.flowControl = flowControl;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(baudRate), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(calibration), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataBit), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(stopBit), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(flowControl), 2));
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.baudRate = (short) Integer.parseInt(hexString.substring(index, index += 2));
        this.calibration = (short) Integer.parseInt(hexString.substring(index, index += 2));
        this.dataBit = (short) Integer.parseInt(hexString.substring(index, index += 2));
        this.stopBit = (short) Integer.parseInt(hexString.substring(index, index += 2));
        this.flowControl = (short) Integer.parseInt(hexString.substring(index, index += 2));
        return CHAR_LENGTH;
    }
}
