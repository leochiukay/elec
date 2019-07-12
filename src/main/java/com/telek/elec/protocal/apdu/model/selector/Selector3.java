package com.telek.elec.protocal.apdu.model.selector;

import java.util.ArrayList;
import java.util.List;

import com.telek.elec.protocal.apdu.model.Selector;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.exeception.EncodeException;
import com.telek.elec.util.StringUtils;

public class Selector3 extends Selector {

    private static final int ID = 3;

    private List<Selector2> selector2s = new ArrayList<>();

    public Selector3() {
        this.id = ID;
    }

    public Selector3(List<Selector2> selector2s) {
        this();
        this.selector2s = selector2s;
    }

    @Override
    protected String encodeSpecial() throws EncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.subLastNumStr(Integer.toHexString(selector2s.size()), 2));
        for (Selector2 selector2 : selector2s) {
            sb.append(selector2.encode());
        }
        return sb.toString();
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
