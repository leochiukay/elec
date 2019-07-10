package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.constant.DARType;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * dar信息
 */
@Data
public class DAR extends AbsDataModel {

    private DARType dar;

    public DAR(DARType dar) {
        this.dar = dar;
    }

    public DAR() {
    }

    @Override
    protected String encodeSpecial() {
        return StringUtils.subLastNumStr(java.lang.Integer.toHexString(dar.getCode()), 2);
    }

    @Override
    protected int decodeSpecial(String hexString) {
        int dar = java.lang.Integer.parseInt(hexString.substring(0, 2), 16);
        for (DARType value : DARType.values()) {
            if (dar == value.getCode()) {
                this.dar = value;
                break;
            }
        }
        return 2;
    }
}
