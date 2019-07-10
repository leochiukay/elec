package com.telek.elec.protocal.apdu.proxy.request;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.proxy.ProxyActionListItem;
import com.telek.elec.protocal.apdu.proxy.Proxy;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public class ProxyActionRequestList extends ResTypeCodecAPDU implements Proxy {

    /**
     * 代理一个服务器的超时时间-2字节
     */
    private LongUnsigned timeout;
    /**
     * 个数
     */
    private List<ProxyActionListItem> items = new ArrayList<>();
    /**
     * 1 字节时间标签
     */
    private int timeStamp;

    public ProxyActionRequestList() {
        super(APDUResType.PROXY_ACTION_LIST);
        this.apduSequence = APDUSequence.PROXY_REQUEST;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = decodeHexExcludeCommonBeginIndex;
        this.timeout = new LongUnsigned(Integer.parseInt(hexString.substring(index, index += 4), 16));
        int size = Integer.parseInt(hexString.substring(index, index += 2));
        this.items = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            ProxyActionListItem proxyGetListItem = new ProxyActionListItem();
            int decode = proxyGetListItem.decode(hexString.substring(index));
            this.items.add(proxyGetListItem);
            index += decode;
        }
        this.timeStamp = Integer.parseInt(hexString.substring(index, index + 2));
    }

    @Override
    protected String encodeThisSpecialToHex() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(timeout.encode());
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(items.size()), 2));
        for (ProxyActionListItem item : items) {
            sb.append(item.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }
}
