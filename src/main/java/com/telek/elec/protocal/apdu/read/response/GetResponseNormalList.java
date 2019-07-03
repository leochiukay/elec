package com.telek.elec.protocal.apdu.read.response;

import java.util.List;

import com.telek.elec.protocal.apdu.model.GetResultNormal;
import com.telek.elec.protocal.apdu.read.CommonGet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.GetType;
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
public class GetResponseNormalList extends CommonGet {

    /**
     * resultNormal个数-1字节
     */
    private int resultNormalCount;

    private List<GetResultNormal> getResultNormalList;

    public GetResponseNormalList() {
        super(GetType.NORMAL_LIST);
        this.apduSequence = APDUSequence.GET_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(resultNormalCount), 2));
        if (resultNormalCount > 0 && getResultNormalList != null) {
            for (GetResultNormal resultNormal : getResultNormalList) {
                sb.append(resultNormal.encode());
            }
        }
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) {
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.resultNormalCount = Integer.parseInt(hexString.substring(index, index += 2), 16);
        if (resultNormalCount > 0) {
            for (int i = 0; i < resultNormalCount; i++) {
                // todo
            }
        }
    }
}
