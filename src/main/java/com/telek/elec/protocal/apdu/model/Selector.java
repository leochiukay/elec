package com.telek.elec.protocal.apdu.model;

import com.telek.elec.protocal.exeception.EncodeException;

import lombok.Data;

@Data
public abstract class Selector extends AbsDataModel {

    protected int id;

    public abstract int getCharLength() throws EncodeException;
}
