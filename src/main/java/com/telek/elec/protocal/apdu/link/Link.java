package com.telek.elec.protocal.apdu.link;

import com.telek.elec.protocal.apdu.APDU;

/**
 * 预链接：
 */
public abstract class Link extends APDU {

    /**
     * piid-1字节
     */
    protected int piid;

}
