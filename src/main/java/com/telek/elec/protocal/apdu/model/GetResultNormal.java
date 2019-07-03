package com.telek.elec.protocal.apdu.model;

import lombok.Data;

/**
 * normal get 得到的结果
 * 40 01 02 00 —— OAD
 * 01 —— Data
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12 —— 通信地址：123456789012
 */
@Data
public class GetResultNormal implements IResult {
    /**
     * 对象属性描述符-4字节
     */
    private OAD oad;
    /**
     * 结果
     */
    private GetResult getResult;


    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(getResult.encode());
        return sb.toString();
    }

    @Override
    public void decode(String onlyThisHexStr) {
        if (onlyThisHexStr == null) {
            return;
        }
        int length = onlyThisHexStr.length();
        // 解码oad
        if (length >= 8) {
            OAD oad = new OAD();
            oad.decode(onlyThisHexStr.substring(0, 8));
            this.oad = oad;
        }
        // 解码结果
        if (length > 8) {
            GetResult getResult = new GetResult();
            getResult.decode(onlyThisHexStr.substring(8));
            this.getResult = getResult;
        }
    }
}
