package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.constant.DARType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 设置响应请求
 * 40 01 02 00 —— OAD1
 * 00 —— DAR1，0成功
 */
@Data
public class SetResponseData implements IResult {

    private OAD oad;
    /**
     * 结果-1字节
     */
    private DARType dar;

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dar.getCode()), 2));
        return sb.toString();
    }

    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null) {
            return;
        }
        int index = 0;
        OAD oad = new OAD();
        oad.decode(onlyThisHexStr.substring(index, index += 8));
        this.oad = oad;
        int dar = Integer.parseInt(onlyThisHexStr.substring(index, index += 2), 16);
        for (DARType value : DARType.values()) {
            if (dar == value.getCode()) {
                this.dar = value;
                break;
            }
        }
    }
}
