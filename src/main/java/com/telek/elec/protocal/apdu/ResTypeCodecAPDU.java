package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * 复杂的apdu,即:
 *    各种请求响应的小分类：eg 读取一个对象属性请求、读取若干个对象属性请求等
 */
@Data
public abstract class ResTypeCodecAPDU extends CodecAPDU {

    protected static final int TYPE_CHAR_LENGTH = 2;

    /**
     * 各种请求响应的小分类：eg 读取一个对象属性请求、读取若干个对象属性请求等
     * 1字节
     */
    private APDUResType apduResType;

    public ResTypeCodecAPDU(APDUResType apduResType) {
        this.apduResType = apduResType;

        int index = APDU_SEQUENCE_CHAR_LENGTH + TYPE_CHAR_LENGTH;
        if (hasPiidFied()) {
            index += PIID_CHAR_LENGTH;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
    }

    @Override
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), APDU_SEQUENCE_CHAR_LENGTH));
        // 获取类型
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduResType.getType()), TYPE_CHAR_LENGTH));
        // ppid
        if (hasPiidFied()) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), PIID_CHAR_LENGTH));
        }
        return sb;
    }

    /**
     * 解码通用属性
     * @param hexString
     * @return
     */
    @Override
    protected void decodeCommonHexToThis(String hexString) {
        int begin = APDU_SEQUENCE_CHAR_LENGTH;
        int resType = Integer.parseInt(hexString.substring(begin, begin += TYPE_CHAR_LENGTH), 16);
        this.apduResType = APDUResType.getResByType(resType, apduSequence.getApduType());
        if (hasPiidFied()) {
            this.piid = Integer.parseInt(hexString.substring(begin, begin + PIID_CHAR_LENGTH), 16);
        }
    }
}
