package com.telek.elec.protocal.apdu.read;

import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.constant.GetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class CommonGet extends CodecAPDU implements Get {

    /**
     * 获取类型-1字节
     */
    protected GetType getType;

    @Override
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), 2));
        // 获取类型
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.getType.getType()), 2));
        // ppid
        if (hasPiidFied()) {
            sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.piid), 2));
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
        int getType = Integer.parseInt(hexString.substring(2, 4), 16);
        for (GetType value : GetType.values()) {
            if (getType == value.getType()) {
                this.getType = value;
                break;
            }
        }

        if (hasPiidFied()) {
            this.piid = Integer.parseInt(hexString.substring(4, 6), 16);
        }
    }

}
