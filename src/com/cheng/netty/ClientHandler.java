package com.cheng.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * *  自定义的handler类
 * *  @author:dcl-Cheng
 * *  @blog:www.dcl-Cheng.com
 **/
public class ClientHandler extends ChannelHandlerAdapter {
    /**
     * 从通道里读到数据，并写数据传回客户端
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("[ClientHandler - channelRead]");

//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        String body = new String(bytes,"utf-8");

        String body = (String)msg;
        System.out.println("Client 收到服务器的返回信息:" + body);
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[ClientHandler - exceptionCaught]");
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("[ClientHandler - channelReadComplete]");
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("[ClientHandler - channelActive]");
    }

}