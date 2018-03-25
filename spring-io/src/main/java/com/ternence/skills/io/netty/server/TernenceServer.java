package com.ternence.skills.io.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * netty使用的demo
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/3/20 16:42
 */
public class TernenceServer {

    /**
     * 绑定主机上的某一个端口，便于对外提供服务
     *
     * @param port 主机端口
     */
    private void bind(int port) throws InterruptedException {
        EventLoopGroup parentLoopGroup = new NioEventLoopGroup();//nio事件循环,通过nio的方式来接收连接和处理连接
        EventLoopGroup childLoopGroup = new NioEventLoopGroup();//用于处理child的事件和io
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //快速配置服务器
            bootstrap.group(parentLoopGroup, childLoopGroup).channel(NioServerSocketChannel.class)//设置nio类型的channel
                    .option(ChannelOption.SO_BACKLOG, 1024)//设置tcp/ip的backlog，(连接等待池)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {//有连接的时候会创建一个channel处理io
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //在pipeline的末尾添加一个channel的事件处理器，这个handler负责处理io或事件的到来
                            ch.pipeline().addLast("myHandler", new EchoServerHandler());
                        }
                    });
            //配置完成之后启动服务器
            ChannelFuture channelFuture = bootstrap.bind().sync();//sync可中断的方法
            System.out.println(EchoServerHandler.class.getName() + " started and listen on " + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();//应用程序会一直等待，直到channel关闭，否则运行完毕就直接退出main方法了
        } finally {
            parentLoopGroup.shutdownGracefully().sync();//优雅关闭，以及释放所有的资源，包括创建的线程
            childLoopGroup.shutdownGracefully().sync();//优雅关闭，以及释放所有的资源，包括创建的线程
        }

    }

    public static void main(String[] args) {
        try {
            new TernenceServer().bind(8090);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
