package com.telek.elec.netty;

import java.util.concurrent.TimeUnit;

import com.telek.elec.protocal.Packet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * netty启动器
 */
@Slf4j
public class NettyStarter {

    /**
     * 端口
     */
    private Integer port;

    public NettyStarter(Integer port) {
        this.port = port;
    }

    /**
     * 服务端netty管道
     */
    private ServerSocketChannel channel;

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.MINUTES))
                                    .addLast(new V698ProtocalDecoder()).addLast(new V698ProtocalEncoder()).addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            if (future.isSuccess()) {
                log.info("启动 " + this.getClass().getSimpleName() + " 成功, port:" + port);
            }
            channel = (ServerSocketChannel) future.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            log.error(this.getClass().getSimpleName() + "断开连接,即将进行重连");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
            }
            start();
        }
    }

    public static void send(String address, byte[] data) {
        String channelId = NettyContext.concentratorCache.get(address);
        Channel channel = NettyContext.clientChannel.get(channelId);
        if (channel == null) {
            log.error("找不到{}对应的channel,集中器地址{}", channelId, address);
            return;
        }
        channel.writeAndFlush(data);
    }

    public static Packet syncSend(String address, byte[] data) {
        String channelId = NettyContext.concentratorCache.get(address);
        Channel channel = NettyContext.clientChannel.get(channelId);
        if (channel == null) {
            log.error("找不到{}对应的channel,集中器地址{}", channelId, address);
            return null;
        }
        String syncKey = address;
        SyncWriteFuture future = new SyncWriteFuture();
        NettyContext.syncKey.put(syncKey, future);
        Packet resp = null;
        try {
            final String finalSyncKey = syncKey;
            channel.writeAndFlush(data).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    // 失败移除
                    if (!future.isSuccess()) {
                        NettyContext.syncKey.remove(finalSyncKey);
                    }
                }
            }).sync();
            resp = future.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("采集数据异常", e);
        } finally {
            NettyContext.syncKey.remove(syncKey);
        }
        return resp;
    }
}
