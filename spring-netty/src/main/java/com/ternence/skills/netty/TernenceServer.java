package com.ternence.skills.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
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
        EventLoopGroup workGroup = new NioEventLoopGroup();//nio事件循环,通过nio的方式来接收连接和处理连接

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //快速配置服务器
            bootstrap.group(workGroup).channel(NioServerSocketChannel.class)//设置nio类型的channel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {//有连接的时候会创建一个channel处理io
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("myHandler", new EchoServerHandler());
                        }
                    });
            //配置完成之后启动服务器
            ChannelFuture channelFuture = bootstrap.bind().sync();//sync可中断的方法
            System.out.println(EchoServerHandler.class.getName() + " started and listen on " + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();//应用程序会一直等待，直到channel关闭
        } finally {
            workGroup.shutdownGracefully().sync();//优雅关闭，以及释放所有的资源，包括创建的线程
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
