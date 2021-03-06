package com.telek.elec.protocal.apdu;

import lombok.Data;

/**
 * 除了序列号以外的通用属性
 */
@Data
public abstract class AbsAPDU extends APDU {

    protected static final int PIID_CHAR_LENGTH = 2;

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
