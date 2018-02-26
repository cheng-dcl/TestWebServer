package com.cheng.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * *  服务端启动类
 * *  @author:dcl-Cheng
 * *  @blog:www.dcl-Cheng.com
 **/
public class ServerMain {

    public static void main(String[] args){


        /**
         * 1.创建两个线程组
         * 一个用于处理服务器端接收客户端连接
         * 一个用于网络通信读写
         */
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup oGroup = new NioEventLoopGroup();

        try {
            /**
             * 2. 创建辅助工具类，用于服务器通道的一系列配置
             * - 绑定线程组
             * - 设置nio的模式通道  NioServerSocketChannel
             * - 设置通道值， tcp缓冲区，发送缓冲大小，接收缓冲大小，保持连接
             *
             *

             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(pGroup,oGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.SO_SNDBUF,32*1024)
                    .option(ChannelOption.SO_RCVBUF,32*1024)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        /**
                         * BASE
                         * 3. 设置实际处理数据的类 ，自定义Handler   ServerHandler
                         */
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new ServerHandler());
//                        }

                        /**
                         *  TCP粘包、拆包问题
                         *  分隔符类：DelimiterBasedFrameDecoder（自定义分隔符）
                         *  字符串形式的解码：StringDecoder（自定义解码器）
                         *  注意顺序：handler最后添加
                         */
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            //设置特殊分隔符"$_"
//                            ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
//                            //设置字符串形式的解码，使用StringDecoder后，在通信不用转成ByteBuf类
//                            socketChannel.pipeline().addLast(new StringDecoder());
//                            socketChannel.pipeline().addLast(new ServerHandler());
//                        }

                        /**
                         *  TCP粘包、拆包问题
                         *  定长：FixedLengthFrameDecoder（自定义分隔符）
                         *  字符串形式的解码：StringDecoder（自定义解码器）
                         *  注意顺序：handler最后添加
                         */

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //设置定长字符串接收
                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(5));
                            //设置字符串形式的解码，使用StringDecoder后，在通信不用转成ByteBuf类
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    });


            /**
             * 4. 端口绑定，并异步处理，返回的cf异步方法
             * 5. 等待关闭
             */

            ChannelFuture cf = serverBootstrap.bind(8764).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pGroup.shutdownGracefully();
            oGroup.shutdownGracefully();
        }

    }
}