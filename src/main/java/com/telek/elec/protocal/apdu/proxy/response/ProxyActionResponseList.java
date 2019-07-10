package com.telek.elec.protocal.apdu.proxy.response;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.proxy.ProxyActionResponseItem;
import com.telek.elec.protocal.apdu.proxy.Proxy;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxyActionResponseList extends ResTypeCodecAPDU implements Proxy {

    //private TSA tsa;

    private List<ProxyActionResponseItem> items = new ArrayList<>();

    /**
     * 上报信息-2字节
     */
    private int followReport;
    /**
     * 时间标签-2字节
     */
    private int timeStamp;

    public ProxyActionResponseList() {
        super(APDUResType.PROXY_ACTION_LIST);
        this.apduSequence = APDUSequence.PROXY_RESPONSE;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = decodeHexExcludeCommonBeginIndex;
        int size = Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ProxyActionResponseItem item = new ProxyActionResponseItem();
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
        for (ProxyActionResponseItem item : items) {
            sb.append(item.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(followReport), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }
}
