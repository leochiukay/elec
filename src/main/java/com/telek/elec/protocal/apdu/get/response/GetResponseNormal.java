package com.telek.elec.protocal.apdu.get.response;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.get.GetResultNormal;
import com.telek.elec.protocal.apdu.get.Get;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
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
 *
 * 00 —— FollowReport  OPTIONAL=0 表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
public class GetResponseNormal extends ResTypeCodecAPDU implements Get {
    /**
     * 结果
     */
    private GetResultNormal getResultNormal;
    /**
     * 标识是否有上报信息-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public GetResponseNormal() {
        super(APDUResType.GET_NORMAL);
        this.apduSequence = APDUSequence.GET_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(getResultNormal.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int hexLength = hexString.length();
        int index = this.decodeHexExcludeCommonBeginIndex;
        GetResultNormal getResultNormal = new GetResultNormal();
        getResultNormal.decode(hexString.substring(index, hexLength - 4));
        this.getResultNormal = getResultNormal;

        String followReport = hexString.substring(hexLength - 4, hexLength - 2);
        String timeStamp = hexString.substring(hexLength - 2);
        this.followReport = Integer.parseInt(followReport, 16);
        this.timeStamp = Integer.parseInt(timeStamp, 16);
    }
}
