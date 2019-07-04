package com.telek.elec.protocal.apdu.read.response;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.GetResultNormal;
import com.telek.elec.protocal.apdu.read.Get;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 响应：85 02 02 02 20 00 02 00 01 01 03 12 09 6D 12 09 6D 12 09 6D 20 01 02 00 01 01 03 05 00 00 03 E8 05 00 00 03 E8 05 00 00 03 E8 00 00
 * 85 —— [133] GET-Response
 * 02 —— [2] GetResponseNormalList
 * 02 —— PIID-ACD
 *
 * 02 —— SEQUENCE OF OAD，个数=2
 *
 * 20 00 02 00 —— OAD
 * 01 —— Data
 * 01 —— 类型=1，表示数组
 * 03 —— 数组元素个数=3
 * 12 09 6D —— 元素1：类型18：long-unsigned 241.3V A相
 * 12 09 6D —— 元素2：类型18：long-unsigned 241.3V B相
 * 12 09 6D —— 元素3：类型18：long-unsigned 241.3V C相
 * 20 01 02 00 —— OAD
 * 01 —— Data
 * 01 —— 类型=1，表示数组
 * 03 —— 数组元素个数=3
 * 05 00 00 03 E8 —— 元素1：类型：5 double-long
 * 05 00 00 03 E8 —— 元素2：类型：5 double-long
 * 05 00 00 03 E8 —— 元素3：类型：5 double-long
 * 00 —— FollowReport  OPTIONAL=0 表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
public class GetResponseNormalList extends ResTypeCodecAPDU implements Get {

    /**
     * resultNormal个数-1字节
     */
    private int resultNormalCount;

    private List<GetResultNormal> getResultNormalList;
    /**
     * 标识是否有上报信息-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public GetResponseNormalList() {
        super(APDUResType.GET_NORMAL_LIST);
        this.apduSequence = APDUSequence.GET_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(resultNormalCount), 2));
        if (resultNormalCount > 0 && getResultNormalList != null) {
            for (GetResultNormal resultNormal : getResultNormalList) {
                sb.append(resultNormal.encode());
            }
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int hexLength = hexString.length();
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.resultNormalCount = Integer.parseInt(hexString.substring(index, index += 2), 16);
        if (resultNormalCount > 0) {
            getResultNormalList = new ArrayList<>(resultNormalCount);
            for (int i = 0; i < resultNormalCount; i++) {
                String s = hexString.substring(index);
                GetResultNormal resultNormal = new GetResultNormal();
                int charLength = resultNormal.decode(s);
                index += charLength;
                getResultNormalList.add(resultNormal);
            }
        }
        String followReport = hexString.substring(hexLength - 4, hexLength - 2);
        String timeStamp = hexString.substring(hexLength - 2);
        this.followReport = Integer.parseInt(followReport, 16);
        this.timeStamp = Integer.parseInt(timeStamp, 16);
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (resultNormalCount < 0 || (resultNormalCount > 0 && (getResultNormalList == null || getResultNormalList.size() != resultNormalCount))) {
            throw new EncodeException("GetResultNormal个数有误");
        }
    }
}
