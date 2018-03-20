package com.ternence.skills.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 业务逻辑处理的handler
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/3/20 17:04
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * channel 读取数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server received data :" + msg);
        ctx.write(msg);//写回数据
    }

    /**
     * channel读取完成
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);//flush数据和关闭channel
    }

    /**
     * 读取过程中的异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//捕捉异常信息
        ctx.close();//出现异常时关闭channel
    }
}
