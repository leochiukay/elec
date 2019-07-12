package com.telek.elec.protocal.apdu.model.selector;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.data.model.MS;
import com.telek.elec.protocal.data.model.number.Unsigned;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;

public class Selector10 extends Selector {

    private static final int ID = 19;

    /**
     * 上 n 条记录 unsigned，
     */
    private short lastNum;

    private MS ms;

    public Selector10() {
        this.id = ID;
    }

    public Selector10(short lastNum, MS ms) {
        this();
        this.lastNum = lastNum;
        this.ms = ms;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(new Unsigned(lastNum).encode());
        sb.append(ms.encode());
        return sb.toString();
    }

    @Override
    protected int decodeSpecial(String hexString) throws DecodeException {
        int index = 0;
        this.lastNum = (short) Integer.parseInt(hexString.substring(index, index += 2), 16);
        this.ms = new MS();
        index += ms.decode(hexString.substring(index));
        return index;
    }

    @Override
    public int getCharLength() throws EncodeException {
        encode();
        return 2 + ms.getCharLength();
    }
}
