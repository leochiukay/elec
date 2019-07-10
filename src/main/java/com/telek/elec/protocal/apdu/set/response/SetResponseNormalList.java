package com.telek.elec.protocal.apdu.set.response;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.set.SetResponseData;
import com.telek.elec.protocal.apdu.set.Set;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 设置多个对象属性请求
 * 响应：86 02 03 02 40 01 02 00 00 40 00 02 00 00 00 00
 * 86 —— [134] SET-Response
 * 02 —— [2] SetResponseNormalList
 * 03 —— PIID-ACD
 * 02 —— SEQUENCE OF个数=2
 * 40 01 02 00 —— OAD1
 * 00 —— DAR1，0成功
 * 40 00 02 00 —— OAD2
 * 00 —— DAR2，0成功
 * 00 —— FollowReport  OPTIONAL=0 表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
public class SetResponseNormalList extends ResTypeCodecAPDU implements Set {

    private List<SetResponseData> responseDatas = new ArrayList<>();
    /**
     * 标识是否有上报信息-1字节
     */
    private int followReport;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public SetResponseNormalList() {
        super(APDUResType.SET_NORMAL_LIST);
        this.apduSequence = APDUSequence.SET_RESPONSE;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(responseDatas.size()), 2));
        for (SetResponseData responseData : responseDatas) {
            sb.append(responseData.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.responseDatas = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String data = hexString.substring(index, index += 10);
            SetResponseData responseData = new SetResponseData();
            responseData.decode(data);
            responseDatas.add(responseData);
        }
        this.followReport = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);

    }
}
