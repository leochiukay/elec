package com.telek.elec.protocal.apdu.proxy.request;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.get.GetRequestRecordData;
import com.telek.elec.protocal.apdu.proxy.Proxy;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.TSA;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * 参考读取一个记录型对象
 */
@Data
public class ProxyGetRequestRecord extends ResTypeCodecAPDU implements Proxy {

    /**
     * 代理一个服务器的超时时间-2字节
     */
    private LongUnsigned timeout;

    private TSA tsa;

    private GetRequestRecordData getRecord;
    /**
     * 1 字节时间标签
     */
    private int timeStamp;


    public ProxyGetRequestRecord() {
        super(APDUResType.PROXY_GET_RECORD);
        this.apduSequence = APDUSequence.PROXY_REQUEST;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        this.timeout = new LongUnsigned(Integer.parseInt(hexString.substring(index, index += 2), 16));
        this.tsa = new TSA();
        index += tsa.decode(hexString.substring(index));
        this.getRecord = new GetRequestRecordData();
        index += getRecord.decode(hexString.substring(index));
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(timeout.encode());
        sb.append(tsa.encode());
        sb.append(getRecord.encode());
        return sb.toString();
    }

}
