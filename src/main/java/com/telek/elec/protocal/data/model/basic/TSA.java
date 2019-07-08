package com.telek.elec.protocal.data.model.basic;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.model.basic.string.OctetString;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:40
 * @Description:
 */
public class TSA extends AbsBasicData {
    private OctetString address;

    public TSA() {
        this.dataType = DataType.TSA;
    }

    public TSA(String address) {
        this.dataType = DataType.TSA;
        this.address = new OctetString(address.getBytes());
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return 0;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return address.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }
}
