package com.telek.elec.protocal.apdu;

import lombok.Data;

@Data
public abstract class CommonCodecAPDU extends CodecAPDU {

    /**
     * 除了通用属性以外的十六进制解码开始下标位置
     */
    protected int decodeHexExcludeCommonBeginIndex;

    public CommonCodecAPDU() {
        int index = APDU_SEQUENCE_CHAR_LENGTH;
        if (hasPiidFied()) {
            index += PIID_CHAR_LENGTH;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
    }
}
