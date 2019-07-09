package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.exeception.EncodeException;

public abstract class Selector extends AbsResult {

    protected int id;

    public abstract int getCharLength() throws EncodeException;

}
