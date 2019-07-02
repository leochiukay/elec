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
     * 时间标签-1字节
     */
    protected int timeStamp;

    /**
     * 组装
     * @return
     */
    public String assembleByteStr(){
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), 2));
        String s = assembleSpecial();
        sb.append(s);
        if (hasTimeStampField()) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.timeStamp), 2));
        }

        log.info(this.getClass().getSimpleName() + "--客户端应用层APDU--" + sb.toString());

        return sb.toString();
    }

    /**
     * 子类赋值
     */
    protected abstract String assembleSpecial();

    /**
     * 子类判断是否有时间标签字段
     * @return
     */
    protected abstract boolean hasTimeStampField();

}
