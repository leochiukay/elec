package com.telek.elec.netty;

import java.nio.ByteBuffer;
import java.util.List;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.codec.Decoder;
import com.telek.elec.protocal.constant.ProtocalConstant;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.codec.binary.Hex;

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
        // 1.帧最短长度为10个字节
        if (readableBytes <= 10) {
            return null;
        }
        in.markReaderIndex();
        // 2.校验帧头
        byte headFrame = in.readByte();
        while (headFrame == 0xFF) {
            headFrame = in.readByte();
        }
        if (headFrame != ProtocalConstant.START_FRAM) {
            log.error("异常帧头", headFrame);
            return null;
        }
        // 3.判断帧的长度是否满足解包，防止netty粘包分包
        byte[] lengthBytes = new byte[2];
        in.readBytes(lengthBytes);
        int length = ((lengthBytes[0] & 0xff)) + ((lengthBytes[1] & 0xff));
        if (readableBytes < length + 2) {
            in.resetReaderIndex();
            return null;
        }
        in.resetReaderIndex();
        byte[] datas = new byte[length + 2];
        in.readBytes(datas);
        in.resetReaderIndex();
        ByteBuffer buffer = in.readRetainedSlice(length + 2).nioBuffer();
        log.info("接收数据为：" + Hex.encodeHexString(datas));
        ReferenceCountUtil.release(in);
        // 4.解码
        Decoder decoder = new Decoder();
        return decoder.decode(buffer);
    }
}
