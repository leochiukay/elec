package com.telek.elec.protocal.apdu.read.request;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.read.Get;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.complex.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 读取一个对象属性请求
 * 05 01 01 40 01 02 00 00
 * 05 —— [5] GET-Request
 * 01 —— [1] GetRequestNormal
 * 01 —— PIID
 * 40 01 02 00 —— OAD：通信地址40010200 (02:00000010, 00:00000000)
 * 00 —— 没有时间标签
 */
@Data
public class GetRequestNormal extends ResTypeCodecAPDU implements Get {

    private OAD oad;
    /**
     * 时间标签-1字节
     */
    private int timeStamp;

    public GetRequestNormal() {
        super(APDUResType.GET_NORMAL);
        this.apduSequence = APDUSequence.GET_REQUEST;
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        if (oad != null) {
            sb.append(oad.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = this.decodeHexExcludeCommonBeginIndex;
        OAD oad = new OAD();
        oad.decode(hexString.substring(index, index += 8));
        this.oad = oad;
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected void encodeValidateSpecial() throws EncodeException {
        if (oad == null) {
            throw new EncodeException("oad is null");
        }
    }
}
