package com.telek.elec.protocal.codec;


import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.block.Seq;
import com.telek.elec.protocal.block.SeqCache;
import com.telek.elec.protocal.exeception.DecodeException;
import com.telek.elec.protocal.unwrapper.UnwrapperChain;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * Created by PETER on 2015/3/14.
 */
@Slf4j
public class Decoder {
    // 解码链
    private UnwrapperChain unwrapperChain = new UnwrapperChain();

    public Packet decode(final ByteBuffer in) throws DecodeException {
        Packet out = new Packet();
        unwrapperChain.decode(in, out);
        return out;
    }
}
