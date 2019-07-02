package com.telek.elec.protocal.apdu.server;

import com.telek.elec.protocal.apdu.APDU;

public abstract class Server extends APDU {

    /**
     * piid-1字节
     */
    protected int piid;
    /**
     * 时间标签-1字节
     */
    protected int timeStamp;

    /**
     * 讲二进制字符串解析为对象
     * @param byteStr
     */
    public abstract void decodeByteStr(String byteStr);

}
