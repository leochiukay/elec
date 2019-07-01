package com.telek.elec.protocal.apdu.server;

public abstract class Server {

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
     * 讲二进制字符串解析为对象
     * @param byteStr
     */
    public abstract void decodeByteStr(String byteStr);

}
