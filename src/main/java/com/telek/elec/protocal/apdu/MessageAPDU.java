package com.telek.elec.protocal.apdu;

import com.telek.elec.protocal.constant.APDUSequence;

/**
 * 报文Apdu
 */
public class MessageAPDU extends APDU {
    /**
     * apdu 完整16进制报文数据
     */
    private String hexMsg;

    public MessageAPDU() {
    }

    /**
     * 通过16进制报文数据完善
     * @param hexMsg
     * @return
     */
    public MessageAPDU fixHexMsg(String hexMsg) {
        if (hexMsg == null || hexMsg.length() < APDU_SEQUENCE_CHAR_LENGTH) {
            throw new RuntimeException("hexMsg长度不符合");
        }
        String s = hexMsg.substring(0, APDU_SEQUENCE_CHAR_LENGTH);
        if (!s.matches("\\d*")) {
            throw new RuntimeException("hexMsg格式不符合");
        }
        int i = Integer.parseInt(s, 16);
        for (APDUSequence value : APDUSequence.values()) {
            if (value.getId() == i) {
                this.apduSequence = value;
                break;
            }
        }
        return this;
    }
}
