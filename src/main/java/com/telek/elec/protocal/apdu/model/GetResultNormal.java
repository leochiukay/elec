package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

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
public class GetResultNormal extends AbsResult {
    /**
     * 对象属性描述符-4字节
     */
    private OAD oad;
    /**
     * 结果
     */
    private GetResult getResult;


    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(getResult.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        // 解码oad
        OAD oad = new OAD();
        int oadLength = oad.decode(hexString.substring(0, 8));
        this.oad = oad;
        // 解码结果
        GetResult getResult = new GetResult();
        int getResultLength = getResult.decode(hexString.substring(8));
        this.getResult = getResult;
        return oadLength + getResultLength;
    }
}
