package com.telek.elec.protocal.apdu.read;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.GetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class AbsGet extends CodecAPDU implements Get {

    protected static final int TYPE_CHAR_LENGTH = 2;

    /**
     * 获取类型-1字节
     */
    protected GetType getType;

    public AbsGet(GetType getType) {
        int index = APDU_SEQUENCE_CHAR_LENGTH + TYPE_CHAR_LENGTH;
        if (hasPiidFied()) {
            index += PIID_CHAR_LENGTH;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
        this.getType = getType;
    }

    @Override
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), APDU_SEQUENCE_CHAR_LENGTH));
        // 获取类型
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.getType.getType()), TYPE_CHAR_LENGTH));
        // ppid
        if (hasPiidFied()) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), PIID_CHAR_LENGTH));
        }
        return sb;
    }

    @Override
    protected void decodeCommonHexToThis(String hexString) {
        int begin = APDU_SEQUENCE_CHAR_LENGTH;
        int getType = Integer.parseInt(hexString.substring(begin, begin += TYPE_CHAR_LENGTH), 16);
        for (GetType value : GetType.values()) {
            if (getType == value.getType()) {
                this.getType = value;
                break;
            }
        }
        if (hasPiidFied()) {
            this.piid = Integer.parseInt(hexString.substring(begin, begin + PIID_CHAR_LENGTH), 16);
        }
    }
}
