package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.BAUDType;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class Comdcb extends AbsData {

    private static final int CHAR_LENGTH = 2 * 5;

    /**
     * 波特率
     */
    private BAUDType baudRate;
    /**
     * 检验位
     */
    private int calibration;
    /**
     * 数据位
     */
    private int dataBit;
    /**
     * 停止位
     */
    private int stopBit;
    /**
     * 流控
     */
    private int flowControl;


    public Comdcb() {
        this.dataType = DataType.COMDCB;
    }

    public Comdcb(BAUDType baudRate, int calibration, int dataBit, int stopBit, int flowControl) {
        this();
        this.baudRate = baudRate;
        this.calibration = calibration;
        this.dataBit = dataBit;
        this.stopBit = stopBit;
        this.flowControl = flowControl;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(baudRate.getCode()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(calibration), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataBit), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(stopBit), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(flowControl), 2));
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.baudRate = BAUDType.get(Integer.parseInt(hexString.substring(index, index += 2)));
        this.calibration = Integer.parseInt(hexString.substring(index, index += 2));
        this.dataBit = Integer.parseInt(hexString.substring(index, index += 2));
        this.stopBit = Integer.parseInt(hexString.substring(index, index += 2));
        this.flowControl = Integer.parseInt(hexString.substring(index, index += 2));
        return CHAR_LENGTH;
    }
}
