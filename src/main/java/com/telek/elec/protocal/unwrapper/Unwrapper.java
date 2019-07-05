package com.telek.elec.protocal.unwrapper;


import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.exeception.DecodeException;

import java.nio.ByteBuffer;

/**
 * Created by PETER on 2015/3/25.
 */
public abstract class Unwrapper {
    protected Unwrapper next;

    abstract void decode(ByteBuffer in, Packet out) throws DecodeException;

    void setNext(Unwrapper unwrapper) {
        next = unwrapper;
    }
}
