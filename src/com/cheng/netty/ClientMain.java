package com.cheng.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetAddress;

/**
 * *  客户端启动类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class ClientMain {

    public static void main(String[] args){
        /**
         * 客户端设置与服务器类似，
         */
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new ClientHandler());
//                        }

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }

//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            //设置定长字符串接收
//                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(5));
//                            //设置字符串形式的解码，使用StringDecoder后，在通信不用转成ByteBuf类
//                            socketChannel.pipeline().addLast(new StringDecoder());
//                            socketChannel.pipeline().addLast(new ClientHandler());
//                        }

                    });



            ChannelFuture cf = bootstrap.connect("127.0.0.1",8764).sync();

            /**
             * Base
             */
//            Thread.sleep(1000);
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer("111".getBytes()));
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer("222".getBytes()));
//            Thread.sleep(3000);
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer("333".getBytes()));

            /**
             * TCP粘包、拆包问题
             * 设置分割后分隔符类：DelimiterBasedFrameDecoder 和 解码器StringDecoder后
             */
//            Thread.sleep(1000);
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer("111..11$_1".getBytes()));
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer("22$_2".getBytes()));
//            Thread.sleep(3000);
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer("33_3".getBytes()));

            /**
             * TCP粘包、拆包问题
             * 设置定长类：FixedLengthFrameDecoder 和 解码器StringDecoder后
             */
            Thread.sleep(1000);
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("123456789".getBytes()));
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("4444".getBytes()));
            Thread.sleep(3000);
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("5555566".getBytes()));


            cf.channel().closeFuture().sync();
            group.shutdownGracefully();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }

    }
}