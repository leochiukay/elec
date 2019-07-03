package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.constant.DARType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 00 10 01 00 —— OMD
 * 00 —— DAR，0成功
 * 00 —— Data OPTIONAL=0 表示没有数据
 */
@Data
public class ActionResponseData implements IResult {

    private OMD omd;
    /**
     * 结果-1字节
     */
    private DARType dar;

    private int data;

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        sb.append(omd.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dar.getCode()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(data), 2));
        return sb.toString();
    }

    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null) {
            return;
        }
        int index = 0;
        OMD omd = new OMD();
        omd.decode(onlyThisHexStr.substring(index, index += 8));
        this.omd = omd;
        int dar = Integer.parseInt(onlyThisHexStr.substring(index, index += 2), 16);
        for (DARType value : DARType.values()) {
            if (dar == value.getCode()) {
                this.dar = value;
                break;
            }
        }
        this.data = Integer.parseInt(onlyThisHexStr.substring(index), 16);
    }
}
