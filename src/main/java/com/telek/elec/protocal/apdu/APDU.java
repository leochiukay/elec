package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.constant.APDUSequence;

import lombok.Data;

/**
 * 请求/响应 apdu顶层类
 */
@Data
public abstract class APDU {

    protected static final int APDU_SEQUENCE_CHAR_LENGTH = 2;

    /**
     * 请求响应类型-1字节
     */
    protected APDUSequence apduSequence;

}
