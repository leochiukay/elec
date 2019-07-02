package com.telek.elec.protocal.apdu.read.response;

import com.telek.elec.protocal.apdu.model.OAD;
import com.telek.elec.protocal.apdu.read.CommonGet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.GetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 响应一个对象属性请求
 * 85 —— [133] GET-Response
 * 01 —— [1] GetResponseNormal
 * 01 —— PIID-ACD
 * 40 01 02 00 —— OAD
 * 01 —— Data
 * 09 —— octet-string
 * 06 —— SIZE(6)
 * 12 34 56 78 90 12 —— 通信地址：123456789012
 * 00 —— FollowReport  OPTIONAL=0 表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
public class GetResponseNormal extends CommonGet {

    private OAD oad;
    /**
     * 数据-1字节
     */
    private int data;
    /**
     * octet-string-1字节
     */
    private int oct;
    /**
     * size-1字节
     */
    private int size;
    /**
     * 通讯地址-字节数为size的值（字符串）
     */
    private String addr;
    /**
     * FollowReport-1字节  OPTIONAL=0 表示没有上报信息
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public GetResponseNormal() {
        this.apduSequence = APDUSequence.GET_RESPONSE;
        this.getType = GetType.NORMAL;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        if (oad != null) {
            sb.append(oad.encodeToHex());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(data), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(oct), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(size), 2));
        sb.append(addr);
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        /*this.oad = hexString.substring(6, 14);
        this.data = Integer.parseInt(hexString.substring(14, 16), 16);
        this.oct = Integer.parseInt(hexString.substring(16, 18), 16);
        this.size = Integer.parseInt(hexString.substring(18, 20), 16);
        this.addr = hexString.substring(20, 32);
        this.followReport = Integer.parseInt(hexString.substring(32, 34), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(34), 16);*/
    }
}
