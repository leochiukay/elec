package com.telek.elec.protocal.apdu.proxy.response;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.proxy.ProxyGetResponseItem;
import com.telek.elec.protocal.apdu.proxy.Proxy;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 *响应：89 01 0A 02 07 05 20 16 01 20 00 01 01 00 10 02 00 01 01 05 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 07 05 20 16 01 20 00 02 01 00 10 02 00 01 01 05 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 00 00
 * 89 —— [137] PROXY-Response
 * 01 —— [1] ProxyGetResponseList
 * 0A —— PIID-ACD
 * 02 —— SEQUENCE OF个数=2
 * 07 05 20 16 01 20 00 01 —— TSA
 * 01 —— SEQUENCE OF个数=1
 * 00 10 02 00  —— OAD
 * 01 —— Data
 * 01 —— array
 * 05 —— 5个元素
 * 06 00 00 00 00 —— 总
 * 06 00 00 00 00 —— 费率1
 * 06 00 00 00 00 —— 费率2
 * 06 00 00 00 00 —— 费率3
 * 06 00 00 00 00 —— 费率4
 * 07 05 20 16 01 20 00 02 —— TSA
 * 01 —— SEQUENCE OF个数=1
 * 00 10 02 00  —— OAD
 * 01 —— Data
 * 01 —— array
 * 05 —— 5个元素
 * 06 00 00 00 00 —— 总
 * 06 00 00 00 00 —— 费率1
 * 06 00 00 00 00 —— 费率2
 * 06 00 00 00 00 —— 费率3
 * 06 00 00 00 00 —— 费率4
 * 00 —— FollowReport  OPTIONAL=0 表示没有上报信息
 * 00 —— 没有时间标签
 */
@Data
public class ProxyGetResponseList extends ResTypeCodecAPDU implements Proxy {

    //private TSA tsa;

    private List<ProxyGetResponseItem> items = new ArrayList<>();

    /**
     * 上报信息-2字节
     */
    private int followReport;
    /**
     * 时间标签-2字节
     */
    private int timeStamp;

    public ProxyGetResponseList() {
        super(APDUResType.PROXY_GET_LIST);
        this.apduSequence = APDUSequence.PROXY_RESPONSE;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = decodeHexExcludeCommonBeginIndex;
        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ProxyGetResponseItem item = new ProxyGetResponseItem();
            int len = item.decode(hexString.substring(index));
            this.items.add(item);
            index += len;
        }
        this.followReport = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.timeStamp = Integer.parseInt(hexString.substring(index, index += 2), 16);
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(items.size()), 2));
        for (ProxyGetResponseItem item : items) {
            sb.append(item.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }
}
