package com.telek.elec.protocal.apdu.proxy.request;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.ResTypeCodecAPDU;
import com.telek.elec.protocal.apdu.model.proxy.ProxyGetListItem;
import com.telek.elec.protocal.apdu.proxy.Proxy;
import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;
import com.telek.elec.protocal.data.model.number.LongUnsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 代理读取2个电能表的当前电能量
 * 发送：09 01 0A 00 78 02 07 05 20 16 01 20 00 01 00 3C 01 00 10 02 00 07 05 20 16 01 20 00 02 00 3C 01 00 10 02 00 00
 * 09 —— [9] PROXY-Request
 * 01 —— [1] ProxyGetRequestList
 * 0A —— PIID
 * 00 78 —— 整个代理请求的超时时间
 * 02 —— SEQUENCE OF个数=2
 * 07 05 20 16 01 20 00 01 —— TSA
 * 00 3C —— 代理一个服务器的超时时间
 * 01 —— SEQUENCE OF OAD个数=1
 * 00 10 02 00  —— OAD
 * 07 05 20 16 01 20 00 02 —— TSA
 * 00 3C —— 代理一个服务器的超时时间
 * 01 —— SEQUENCE OF个数=1
 * 00 10 02 00 —— OAD
 * 00 —— 没有时间标签
 */
@Data
public class ProxyGetRequestList extends ResTypeCodecAPDU implements Proxy {

    /**
     * 代理一个服务器的超时时间-2字节
     */
    private LongUnsigned timeout;
    /**
     * 个数
     */
    private List<ProxyGetListItem> items = new ArrayList<>();
    /**
     * 1 字节时间标签
     */
    private int timeStamp;

    public ProxyGetRequestList() {
        super(APDUResType.PROXY_GET_LIST);
        this.apduSequence = APDUSequence.PROXY_REQUEST;
    }

    @Override
    protected void decodeSpecialHexToThis(String hexString) throws DecodeException {
        int index = decodeHexExcludeCommonBeginIndex;
        this.timeout = new LongUnsigned(Integer.parseInt(hexString.substring(index, index += 4), 16));
        int size = Integer.parseInt(hexString.substring(index, index += 2));
        this.items = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            ProxyGetListItem proxyGetListItem = new ProxyGetListItem();
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
        for (ProxyGetListItem item : items) {
            sb.append(item.encode());
        }
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(timeStamp), 2));
        return sb.toString();
    }
}
