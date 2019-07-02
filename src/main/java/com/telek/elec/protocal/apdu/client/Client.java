package com.telek.elec.protocal.apdu.client;

import com.telek.elec.protocal.apdu.APDU;
import com.telek.elec.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端apdu
 */
@Slf4j
public abstract class Client extends APDU {

    /**
     * piid-1字节
     */
    protected int piid;

    /**
     * 组装公用字段
     * @return
     */
    protected String encodeHexCommon(){
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), 2));
        return sb.toString();
    }
}
