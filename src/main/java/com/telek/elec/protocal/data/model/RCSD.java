package com.telek.elec.protocal.data.model;

import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public class RCSD extends AbsData {
    @Override
    protected int calculateSpecialCharLength() throws EncodeException {
        return 0;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return null;
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        return 0;
    }
}
