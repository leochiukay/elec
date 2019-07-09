package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.RSD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

/**
 * 50 04 02 00 ——  日冻结
 *  * 01 —— RSD， 选择方法1
 *  * 20 21 02 00 —— OAD，数据冻结时间
 *  * 1C 07 E0 01 14 00 00 00 —— 时间
 *  * 02 —— RCSD， SEQUENCE OF个数=2
 *  * 00 —— [0] OAD
 *  * 20 21 02 00 —— OAD
 *  * 00 —— [0] OAD
 */
@Data
public class GetRecord extends AbsResult {

    private OAD oad;

    private RSD rsd;

    private RCSD rcsd;

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(oad.encode());
        sb.append(rsd.encode());
        sb.append(rcsd.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        OAD oad = new OAD();
        int oadLen = oad.decode(hexString.substring(index, index += 4));
        this.oad = oad;

        RSD rsd = new RSD();
        int rsdLen = rsd.decode(hexString.substring(index += oadLen));
        this.rsd = rsd;

        RCSD rcsd = new RCSD();
        int rcsdLen = rcsd.decode(hexString.substring(index += rsdLen));
        this.rcsd = rcsd;

        return index + rcsdLen;
    }
}
