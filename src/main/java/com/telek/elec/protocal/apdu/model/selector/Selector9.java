package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public class Selector9 extends Selector {

    private static final int ID = 9;

    /**
     * 上第 n 次记录 unsigned
     */
    private short lastNum;

    public Selector9() {
        this.id = ID;
    }

    public Selector9(short lastNum) {
        this();
        this.lastNum = lastNum;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        return new Unsigned(lastNum).encode();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        this.lastNum = (short) Integer.parseInt(hexString.substring(0, 2), 16);
        return 2;
    }

    @Override
    public int getCharLength() throws EncodeException {
        return 2;
    }
}
