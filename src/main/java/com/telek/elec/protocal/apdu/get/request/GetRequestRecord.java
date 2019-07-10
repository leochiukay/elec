package com.telek.elec.protocal.apdu.get.request;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.get.GetRequestRecordData;
import com.telek.elec.protocal.apdu.get.Get;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 终端读取电能表在2016-01-20 00： 00： 00的日冻结正向有功总及费率电能量
 * 05 03 03 50 04 02 00 01 20 21 02 00 07 E0 16 01 14 00 00 00 02 00 20 21 02 00 00 00 10 02 00 00
 * 05 —— [5] GET-Request
 * 03 —— [3] GetRequestRecord
 * 03 —— PIID
 * 50 04 02 00 ——  日冻结
 * 01 —— RSD， 选择方法1
 * 20 21 02 00 —— OAD，数据冻结时间
 * 1C 07 E0 01 14 00 00 00 —— 时间
 * 02 —— RCSD， SEQUENCE OF个数=2
 * 00 —— [0] OAD
 * 20 21 02 00 —— OAD
 * 00 —— [0] OAD
 * 00 10 02 00 —— OAD
 * 00 —— 没有时间标签
 */
@Data
public class GetRequestRecord extends ResTypeCodecAPDU implements Get {

    private GetRequestRecordData getRecord;

    private int timeStamp;

    public GetRequestRecord() {
        super(APDUResType.GET_RECORD);
        this.apduSequence = APDUSequence.GET_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(getRecord.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        GetRequestRecordData getRecord = new GetRequestRecordData();
        int charLen = getRecord.decode(hexString);
        this.getRecord = getRecord;
        this.timeStamp = Integer.parseInt(hexString.substring(charLen, charLen + 2), 16);
    }
}
