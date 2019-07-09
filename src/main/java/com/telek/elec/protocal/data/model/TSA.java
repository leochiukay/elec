package com.telek.elec.protocal.data.model;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
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
    private int addressType;
    private int logicAddress;
    private String address;

    public TSA() {
        this.dataType = DataType.TSA;
    }

    public TSA(String address) {
        this.dataType = DataType.TSA;
        this.addressType = 0;
        this.logicAddress = 0;
        this.address = address;
    }

    public TSA(int addressType, int logicAddress, String address) {
        this.dataType = DataType.TSA;
        this.addressType = addressType;
        this.logicAddress = logicAddress;
        this.address = address;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sbf = new StringBuffer();
        byte sa0 = (byte) (((addressType << 6) & 0xC0) + ((logicAddress << 4) & 0x30) + (address.length() - 1 & 0x0F));
        sbf.append(HexBin.encode(new byte[]{sa0}));
        sbf.append(address);
        OctetString octetString = new OctetString(sbf.toString());
        return octetString.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        OctetString octetString = new OctetString(hexString);
        octetString.decode(hexString);
        String s = octetString.getStr();
//        TODO
        return octetString.getCharLength();
    }
}
