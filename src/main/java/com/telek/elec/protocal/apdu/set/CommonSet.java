package com.telek.elec.protocal.apdu.set;

import com.telek.elec.protocal.apdu.CommonCodecAPDU;
import com.telek.elec.protocal.constant.SetType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

@Data
public abstract class CommonSet extends CommonCodecAPDU implements Set {

    /**
     * 设置类型-1字节
     */
    protected SetType setType;

    public CommonSet(SetType setType) {
        this.setType = setType;
    }

    @Override
    protected StringBuilder encodeCommonFieldToHex() {
        StringBuilder sb = new StringBuilder();
        // 序号
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.apduSequence.getId()), 2));
        // 获取类型
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(this.setType.getType()), 2));
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
        for (SetType value : SetType.values()) {
            if (getType == value.getType()) {
                this.setType = value;
                break;
            }
        }
        if (hasPiidFied()) {
            this.piid = Integer.parseInt(hexString.substring(4, 6), 16);
        }
    }

    @Override
    protected Object subclassDecodeProcessing(String hexString) {
        int index = 4;
        if (hasPiidFied()) {
            index = 6;
        }
        this.decodeHexExcludeCommonBeginIndex = index;
        return null;
    }
}
