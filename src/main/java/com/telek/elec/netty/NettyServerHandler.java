package com.telek.elec.netty;

import com.telek.elec.protocal.packet.Packet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2018/6/20 14:58
 * @Description:
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {

    }
}
