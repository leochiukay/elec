package com.telek.elec.protocal.data.model;

import org.apache.commons.lang3.ArrayUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.telek.elec.protocal.constant.DataType;
import com.telek.elec.protocal.data.HexToDataConvertor;
import com.telek.elec.protocal.data.model.string.OctetString;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

import lombok.Data;

/**
 * @Auther: wll
 * @Date: 2019/7/8 22:40
 * @Description:
 */
@Data
public class TSA extends AbsData {
    // 地址类型：bit7 bit6
    private int addressType;
    // 逻辑地址: bit5 bit4
    private int logicAddress;
    // 地址SA-不包括前面地址类型、逻辑地址、地址长度N等1个字节
    private String address;

    public TSA() {
        this.dataType = DataType.TSA;
    }

    public TSA(String address) {
        this();
        this.addressType = 0;
        this.logicAddress = 0;
        this.address = address;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuffer sbf = new StringBuffer();
        byte sa0 = (byte) (((addressType << 6) & 0xC0) + ((logicAddress << 4) & 0x30) + (address.length()/2 - 1 & 0x0F));
        sbf.append(HexBin.encode(new byte[]{sa0}));
        sbf.append(address);
        OctetString octetString = new OctetString(sbf.toString());
        return octetString.encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        hexString = StringUtils.subLastNumStr(Integer.toHexString(DataType.OCTET_STRING.getCode()), 2) + hexString;
        OctetString octetString = (OctetString) HexToDataConvertor.hexToData(hexString);
        String hexStr = octetString.getStr();
        byte[] bytes = HexBin.decode(hexStr);
        int b = bytes[0] & 0xff;
        this.addressType = b >>> 6 & 0xff;
        this.logicAddress = b >>> 4 & 0x3;
        this.address = HexBin.encode(ArrayUtils.subarray(bytes, 1, bytes.length));
        return 2 + octetString.getSize() * 2;
    }
}
