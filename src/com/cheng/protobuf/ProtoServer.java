package com.cheng.protobuf;

import com.cheng.log4j.LoggerType;
import com.cheng.message.prototest.DataPro;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * *  proto服务器启动类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class ProtoServer {

    public static final Logger logger = Logger.getLogger(LoggerType.PROTO);

    public static void main(String[] args){


        PropertyConfigurator.configure("resource/log4j.properties");

        EventLoopGroup readGroup = new NioEventLoopGroup();
        EventLoopGroup writeGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(readGroup,writeGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_RCVBUF,32 * 1024)
                .option(ChannelOption.SO_SNDBUF,32 * 1024)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder())
                                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                                .addLast(new ProtobufDecoder(DataPro.DataMessage.getDefaultInstance()))
                                                .addLast(new ProtobufEncoder())
                                                .addLast(new ProtoServerHandler());
                    }
                });

        try {

            ChannelFuture channelFuture = serverBootstrap.bind(8765).sync();
            logger.info("Proto Server Begin! localhost:8765");
//            channelFuture.addListener(ChannelFutureListener.CLOSE).sync();
            channelFuture.channel().closeFuture().sync();
            logger.info("Proto Server End! localhost:8765");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readGroup.shutdownGracefully();
            writeGroup.shutdownGracefully();
        }
    }
}