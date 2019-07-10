package com.telek.elec.protocal.apdu.proxy.response;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.get.GetResponseRecordData;
import com.telek.elec.protocal.apdu.proxy.Proxy;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxyGetResponseRecord extends ResTypeCodecAPDU implements Proxy {

    private TSA tsa;

    private GetResponseRecordData resultRecord;

    /**
     * 上报信息-2字节
     */
    private int followReport;
    /**
     * 时间标签-2字节
     */
    private int timeStamp;

    public ProxyGetResponseRecord() {
        super(APDUResType.PROXY_GET_RECORD);
        this.apduSequence = APDUSequence.PROXY_RESPONSE;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = decodeHexExcludeCommonBeginIndex;
        this.tsa = new TSA();
        index += tsa.decode(hexString.substring(index));
        this.resultRecord = new GetResponseRecordData();
        index += resultRecord.decode(hexString);
        this.followReport = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.tsa.encode());
        sb.append(this.resultRecord.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }
}
