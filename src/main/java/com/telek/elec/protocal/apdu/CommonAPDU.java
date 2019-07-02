package com.telek.elec.protocal.apdu;

import lombok.Data;

/**
 * 除了序列号以外的通用属性
 */
@Data
public abstract class CommonAPDU extends APDU {

    /**
     * piid-1字节
     */
    protected int piid;

    /**
     * 是否有piid属性
     * @return
     */
    protected boolean hasPiidFied() {
        return true;
    }

}
