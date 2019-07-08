package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public class Selector9 extends Selector {
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
