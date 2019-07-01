package com.telek.elec.netty;

import com.telek.elec.protocal.packet.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2019/1/17 15:42
 * @Description:
 */
@Slf4j
public class V698ProtocalEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {

    }
}
