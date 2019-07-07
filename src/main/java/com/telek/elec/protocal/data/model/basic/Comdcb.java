package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public class Comdcb extends AbsBasicData {

    private static final int CHAR_LENGTH = (2 + 2) * 5;

    /**
     * 波特率
     */
    private Enums baudRate;
    /**
     * 检验位
     */
    private Enums calibration;
    /**
     * 数据位
     */
    private Enums dataBit;
    /**
     * 停止位
     */
    private Enums stopBit;
    /**
     * 流控
     */
    private Enums flowControl;


    public Comdcb() {
        this.dataType = DataType.COMDCB;
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return CHAR_LENGTH;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(baudRate.encode());
        sb.append(calibration.encode());
        sb.append(dataBit.encode());
        sb.append(stopBit.encode());
        sb.append(flowControl.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        Enums baudRate = new Enums();
        int index = 0;
        int i = baudRate.decodeSpecial(hexString.substring(index));
        this.baudRate = baudRate;

        Enums calibration = new Enums();
        i = baudRate.decodeSpecial(hexString.substring(index += i));
        this.calibration = calibration;

        Enums dataBit = new Enums();
        i = baudRate.decodeSpecial(hexString.substring(index += i));
        this.dataBit = dataBit;

        Enums stopBit = new Enums();
        i = baudRate.decodeSpecial(hexString.substring(index += i));
        this.stopBit = stopBit;

        Enums flowControl = new Enums();
        i = baudRate.decodeSpecial(hexString.substring(index += i));
        this.flowControl = flowControl;

        return CHAR_LENGTH;
    }
}
