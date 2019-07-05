package com.telek.elec.protocal.wrapper;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.exeception.EncodeException;

/**
 * Created by PETER on 2015/3/24.
 */
public abstract class Wrapper {
    protected Wrapper next;

    abstract void encode(Packet in, Packet.SA sa, byte[] data, CodecAPDU apdu, int seq) throws EncodeException;

    public void setNext(Wrapper next) {
        this.next = next;
    }
}
