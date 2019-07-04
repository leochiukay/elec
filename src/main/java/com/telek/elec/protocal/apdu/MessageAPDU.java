package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.constant.APDUResType;

import lombok.Data;

/**
 * 报文Apdu
 */
@Data
public class MessageAPDU extends APDU {

    private static final String REGX = "(\\d*|[a-f])*";

    /**
     * apdu 完整16进制报文数据
     */
    private String hexMsg;
    /**
     * 各种请求响应的小分类：eg 读取一个对象属性请求、读取若干个对象属性请求等
     */
    private APDUResType apduResType;

    public MessageAPDU() {
    }

    /**
     * 通过16进制报文数据完善
     * @param hexMsg
     * @return
     */
    public MessageAPDU resolveHex(String hexMsg) {
        String s = hexMsg.substring(0, APDU_SEQUENCE_CHAR_LENGTH);
        int i = Integer.parseInt(s, 16);
        apduSequence = apduSequence.getByIdSequence(i);

        if (apduSequence != null) {
            s = hexMsg.substring(APDU_SEQUENCE_CHAR_LENGTH, APDU_SEQUENCE_CHAR_LENGTH + 2);
            i = Integer.parseInt(s, 16);
            this.apduResType = APDUResType.getResByType(i, apduSequence.getApduType());
        }
        return this;
    }
}
