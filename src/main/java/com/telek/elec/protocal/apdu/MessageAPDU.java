package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.constant.APDUResType;
import com.telek.elec.protocal.constant.APDUSequence;

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

    /**
     * 通过16进制报文数据完善
     * @param hexMsg
     * @return
     */
    public void resolveHex(String hexMsg) {
        int apduSequence = Integer.parseInt(hexMsg.substring(0, APDU_SEQUENCE_CHAR_LENGTH), 16);
        this.apduSequence = APDUSequence.decode(apduSequence);
        if (this.apduSequence != null) {
            int resType = Integer.parseInt(hexMsg.substring(APDU_SEQUENCE_CHAR_LENGTH, APDU_SEQUENCE_CHAR_LENGTH + 2), 16);
            this.apduResType = APDUResType.decode(resType, this.apduSequence.getApduType());
        }
    }
}
