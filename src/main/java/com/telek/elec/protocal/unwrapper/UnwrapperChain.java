package com.telek.elec.protocal.unwrapper;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.exeception.DecodeException;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PETER on 2015/3/25.
 */
public class UnwrapperChain {
    private List<Unwrapper> unwrappers = new ArrayList<>();

    public UnwrapperChain() {
        this.add(new HeadUnwrapper());
        this.add(new DataUnwrapper());
        this.add(new TailUnwrapper());
    }

    public void add(Unwrapper unwrapper) {
        if (unwrappers.size() > 0) {
            Unwrapper pre = unwrappers.get(unwrappers.size() - 1);
            pre.setNext(unwrapper);
        }
        unwrappers.add(unwrapper);
    }

    public void decode(ByteBuffer in, Packet out) throws DecodeException {
        if (unwrappers.size() > 0) {
            unwrappers.get(0).decode(in, out);
        }
    }
}
