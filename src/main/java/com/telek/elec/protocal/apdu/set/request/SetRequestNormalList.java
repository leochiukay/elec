package com.telek.elec.protocal.apdu.set.request;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.SetRequestData;
import com.telek.elec.protocal.apdu.set.AbsSet;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.constant.SetType;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 设置通信地址和时钟
 *  发送：06 02 03 02 40 01 02 00 09 06 00 00 00 00 00 01 40 00 02 00 1C 07 E0 01 14 10 1B 0B 00
 *  06 —— [6] SET-Request
 *  02 —— [2] SetRequestNormalList
 *  03 —— PIID
 *  02 —— SEQUENCE OF的个数=2
 *  40 01 02 00 —— OAD1
 *  09 06 00 00 00 00 00 01 —— value1（通信地址值：000000000001）
 *  40 00 02 00 —— OAD2
 *  1C 07 E0 01 14 10 1B 0B —— value2（时间：2016-01-20 16:27:11）
 *  00 —— 没有时间标签
 */
@Data
public class SetRequestNormalList extends AbsSet {
    /**
     * oad个数-1字节
     */
    private int dataCount;
    /**
     * 设置参数值
     */
    private List<SetRequestData> setDatas;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public SetRequestNormalList() {
        super(SetType.NORMAL_LIST);
        this.apduSequence = APDUSequence.SET_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(dataCount), 16));
        if (dataCount > 0 && setDatas != null) {
            for (SetRequestData setData : setDatas) {
                sb.append(setData.encode());
            }
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.dataCount = Integer.parseInt(hexString.substring(index, index += 2), 16);
        if (dataCount > 0) {
            this.setDatas = new ArrayList<>(dataCount);
            for (int i = 0; i < dataCount; i++) {
                SetRequestData setData = new SetRequestData();
                setData.decode(hexString.substring(index, index += 24));
                this.setDatas.add(setData);
            }
        }
        this.timeStamp = Integer.parseInt(hexString.substring(hexString.length() - 2), 16);
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (dataCount < 0 || (dataCount > 0 && (setDatas == null || setDatas.size() != dataCount))) {
            throw new EncodeException("setDatas个数有误");
        }
    }
}
