package com.ternence.skills.io.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 使用netty编写一个客户端
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/3/20 17:18
 */
public class EchoServerClient {
    /**
     * 连接服务器
     */
    private void connect(String host, int port) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();//客户端快速配置
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();

            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    System.out.println("client connected");
                } else {
                    System.out.println("server attempt failed");
                    future.cause().printStackTrace();
                }
            });
            channelFuture.channel().writeAndFlush("this is a hello world message !");
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();//可响应中断的操作
        }
    }

    public static void main(String[] args) {
        try {
            new EchoServerClient().connect("127.0.0.1", 8090);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
