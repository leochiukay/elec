package com.telek.elec.protocal.apdu.set;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.SetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class AbsSet extends CodecAPDU implements Set {

    protected static final int TYPE_CHAR_LENGTH = 2;

    /**
     * 设置类型-1字节
     */
    protected SetType setType;

    public AbsSet(SetType setType) {
        int index = APDU_SEQUENCE_CHAR_LENGTH + TYPE_CHAR_LENGTH;
        if (hasPiidFied()) {
            index += PIID_CHAR_LENGTH;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
        this.setType = setType;
    }

    @Override
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), APDU_SEQUENCE_CHAR_LENGTH));
        // 获取类型
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.setType.getType()), TYPE_CHAR_LENGTH));
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
        int getType = Integer.parseInt(hexString.substring(begin, begin += TYPE_CHAR_LENGTH), 16);
        for (SetType value : SetType.values()) {
            if (getType == value.getType()) {
                this.setType = value;
                break;
            }
        }
        if (hasPiidFied()) {
            this.piid = Integer.parseInt(hexString.substring(begin, begin + PIID_CHAR_LENGTH), 16);
        }
    }
}
