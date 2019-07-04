package com.telek.elec.protocal.apdu.action;

import com.telek.elec.protocal.apdu.CommonCodecAPDU;
import com.telek.elec.protocal.constant.ActionType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class AbsAction extends CommonCodecAPDU implements Action {

    protected static final int TYPE_CHAR_LENGTH = 2;

    /**
     * action类型-1字节
     */
    protected ActionType actionType;

    public AbsAction(ActionType actionType) {
        int index = APDU_SEQUENCE_CHAR_LENGTH + TYPE_CHAR_LENGTH;
        if (hasPiidFied()) {
            index += PIID_CHAR_LENGTH;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
        this.actionType = actionType;
    }

    @Override
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), APDU_SEQUENCE_CHAR_LENGTH));
        // 获取类型
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.actionType.getType()), TYPE_CHAR_LENGTH));
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
        for (ActionType value : ActionType.values()) {
            if (getType == value.getType()) {
                this.actionType = value;
                break;
            }
        }
        if (hasPiidFied()) {
            this.piid = Integer.parseInt(hexString.substring(begin, begin + PIID_CHAR_LENGTH), 16);
        }
    }
}
