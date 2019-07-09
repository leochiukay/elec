package com.telek.elec.protocal.wrapper;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;
import com.telek.elec.protocal.exeception.EncodeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PETER on 2015/3/24.
 */
public class WrapperChain {
    List<Wrapper> wrappers = new ArrayList<>();

    public WrapperChain() {
        this.add(new HeadWrapper());
        this.add(new DataWrapper());
        this.add(new TailWrapper());
    }

    private void add(Wrapper wrapper) {
        if (wrappers.size() > 0) {
            Wrapper pre = wrappers.get(wrappers.size() - 1);
            pre.setNext(wrapper);
        }
        wrappers.add(wrapper);
    }

    public void encode(Packet in, Packet.SA sa, byte[] data, CodecAPDU apdu, int seq) throws EncodeException {
        if (wrappers.size() > 0) {
            wrappers.get(0).encode(in, sa, data, apdu, seq);
        }
    }
}
