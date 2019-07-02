package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;

/**
 * 请求/响应 apdu顶层类
 */
@Data
public abstract class APDU {

    /**
     * 请求响应类型
     */
    protected APDUSequence apduSequence;

}
