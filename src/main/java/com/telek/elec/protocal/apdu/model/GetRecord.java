package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.data.model.RCSD;
import com.telek.elec.protocal.data.model.RSD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

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
        int oadLen = oad.decode(hexString.substring(index));
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
