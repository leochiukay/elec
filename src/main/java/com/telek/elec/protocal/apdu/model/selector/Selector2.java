package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.model.AbsData;
import com.telek.elec.protocal.data.model.OAD;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

@Data
public class Selector2 extends Selector {

    private OAD oad;

    private AbsData data;

    @Override
    protected String encodeSpecial() throws EncodeException {
        return null;
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }

    @Override
    public int getCharLength() throws EncodeException {
        return 0;
    }
}
