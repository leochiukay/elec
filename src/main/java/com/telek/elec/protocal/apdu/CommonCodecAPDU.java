package com.telek.elec.protocal.apdu;

import lombok.Data;

@Data
public abstract class CommonCodecAPDU extends CodecAPDU {

    /**
     * 除了通用属性以外的十六进制解码开始下标位置
     */
    protected int decodeHexExcludeCommonBeginIndex;

    @Override
    protected Object subclassDecodeProcessing(String hexString) {
        int index = 2;
        if (hasPiidFied()) {
            index = 4;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
        return null;
    }
}
