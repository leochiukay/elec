package com.telek.elec.netty;

import java.util.List;

import org.apache.commons.codec.binary.Hex;

import com.telek.elec.protocal.packet.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wll
 * @Date: 2019/1/17 15:42
 * @Description:
 */
@Slf4j
public class V698ProtocalDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int readableBytes = in.readableBytes();
        if (readableBytes <= 0) {
            return null;
        }
        byte[] bs = new byte[readableBytes];
        in.readBytes(bs);
        log.info("接收数据为：" + Hex.encodeHexString(bs));
        boolean validate = validate(bs);
        if (!validate) {
            return null;
        }
        return new Packet();
    }

    /**
     * 帧校验
     * @param bs
     * @return
     */
    private boolean validate(byte[] bs) {
        // 头帧校验

        return false;
    }
}
