package com.telek.elec.netty;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
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
@Component
public class NettyStarter {

    /**
     * 端口
     */
    @Value("${netty.server.port}")
    private Integer server_port = 9999;

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
            ChannelFuture future = bootstrap.bind(server_port).sync();
            if (future.isSuccess()) {
                log.info("启动 " + this.getClass().getSimpleName() + " 成功");
            }
            channel = (ServerSocketChannel) future.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
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
}
