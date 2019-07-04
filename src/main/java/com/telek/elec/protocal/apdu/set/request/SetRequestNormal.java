package com.telek.elec.protocal.apdu.set.request;

import com.telek.elec.protocal.apdu.model.SetRequestData;
import com.telek.elec.protocal.apdu.set.AbsSet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.SetType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 设置时钟
 * 发送：06 01 02 40 00 02 00 1C 07 E0 01 14 10 1B 0B 00
 * 06 —— [6] SET-Request
 * 01 —— [1] SetRequestNormal
 * 02 —— PIID
 * 40 00 02 00 —— OAD
 * 1C —— Data：类型28：date_time_s
 * 07 E0 01 14 10 1B 0B—— 时间：2016-01-20 16：27：11
 * 00 —— 没有时间标签
 */
@Data
public class SetRequestNormal extends AbsSet {
    /**
     * 设置数据
     */
    private SetRequestData setData;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public SetRequestNormal() {
        super(SetType.NORMAL);
        this.apduSequence = APDUSequence.SET_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(setData.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        SetRequestData setData = new SetRequestData();
        setData.decode(hexString.substring(index, hexString.length() - 2));
        this.setData = setData;
        this.timeStamp = Integer.parseInt(hexString.substring(hexString.length() - 2), 16);
    }
}
