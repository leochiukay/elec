package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.model.string.OctetString;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:40
 * @Description:
 */
public class TSA extends AbsData {

    private OctetString address;

    public TSA() {
        this.dataType = DataType.TSA;
    }

    public TSA(boolean isEncodeDataType) {
        this();
        this.isEncodeDataType = isEncodeDataType;
    }

    public TSA(String address, boolean isEncodeDataType) {
        this(isEncodeDataType);
        this.address = new OctetString(address.getBytes(), false);
    }

    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        address.encode();
        return address.getCharLength();
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return address.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        OctetString octetString = new OctetString();
        // todo--第一个字符串是表示后续字符串长度吗？？？？
        int size = Integer.parseInt(hexString.substring(0, 2));
        octetString.setSize(size);
        octetString.decode(hexString.substring(2));
        return octetString.getCharLength();
    }
}
