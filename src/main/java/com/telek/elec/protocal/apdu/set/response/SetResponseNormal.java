package com.telek.elec.protocal.apdu.set.response;

import com.telek.elec.protocal.apdu.model.SetResponseData;
import com.telek.elec.protocal.apdu.set.CommonSet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.SetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 响应一个对象属性请求
 * 响应：86 01 02 40 00 02 00 00 00 00
 * 86 —— [134] SET-Response
 * 01 —— [1] SetResponseNormal
 * 02 —— PIID-ACD
 * 40 00 02 00 —— OAD
 * 00 —— DAR，0成功
 * 00 —— FollowReport  OPTIONAL=0 表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
public class SetResponseNormal extends CommonSet {

    private SetResponseData setResponseData;
    /**
     * 标识是否有上报信息-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public SetResponseNormal() {
        super(SetType.NORMAL);
        this.apduSequence = APDUSequence.SET_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        if (setResponseData != null) {
            sb.append(setResponseData.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        int length = hexString.length();
        SetResponseData responseData = new SetResponseData();
        responseData.decode(hexString.substring(this.decodeHexExcludeCommonBeginIndex, length - 4));
        this.setResponseData = responseData;
        this.followReport = Integer.parseInt(hexString.substring(length - 4, length - 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(length - 2, length), 16);
    }
}
