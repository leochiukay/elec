package com.telek.elec.protocal.apdu.client;

import com.telek.elec.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端apdu
 */
@Slf4j
public abstract class Client {

    /**
     * 序列id-1字节
     */
    protected int id;
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
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.id), 2));
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), 2));
        String s = assembleSpecial();
        sb.append(s);
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.timeStamp), 2));

        log.info(this.getClass().getSimpleName() + "--客户端应用层APDU--" + sb.toString());

        return sb.toString();
    }

    /**
     * 子类赋值
     */
    protected abstract String assembleSpecial();

}
