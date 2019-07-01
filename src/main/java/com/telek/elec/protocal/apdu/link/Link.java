package com.telek.elec.protocal.apdu.link;

/**
 * 预链接：
 */
public abstract class Link {
    /**
     * 类型：1预链接请求，2预链接响应
     */
    protected int id;
    /**
     * piid-1字节
     */
    protected int piid;

}
