package com.telek.elec.netty;

import com.telek.elec.cache.TempCache;
import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.ProcessingServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.telek.elec.protocal.constant.ProtocalConstant.*;

/**
 * @Auther: wll
 * @Date: 2018/6/20 14:58
 * @Description:
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<Packet> {
    private ExecutorService service = Executors.newCachedThreadPool();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String channelId = ctx.channel().remoteAddress().toString().substring(1);
        String address = NettyContext.getConcentratorByChannel(channelId);
        log.info("{}，10分钟未收到服务器消息", address);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("关闭这个不活跃通道！");
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().remoteAddress().toString().substring(1);
        NettyContext.clientChannel.put(channelId, ctx.channel());
        log.info(ctx.channel().remoteAddress().toString().substring(1) + "----连接到服务端");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().remoteAddress().toString().substring(1);
        NettyContext.clientChannel.remove(channelId);
        Iterator<Map.Entry<String, String>> iter = NettyContext.concentratorCache.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            if (entry.getValue().equals(channelId)) {
                iter.remove();
            }
        }
        log.info(ctx.channel().remoteAddress().toString().substring(1) + "----与服务器断开连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常时断开连接
        cause.printStackTrace();
        log.error("netty出现异常", cause.getCause());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        //从服务端收到消息时被调用

        String channelId = ctx.channel().remoteAddress().toString().substring(1);
        TempCache.serviceAddressInfo.put(packet.getSa().getAddress(), packet.getSa());
        service.execute(new ProcessingServer(packet));
        NettyContext.concentratorCache.put(packet.getSa().getAddress(), channelId);
    }
}
