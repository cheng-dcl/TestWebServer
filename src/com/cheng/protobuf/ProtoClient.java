package com.cheng.protobuf;

import com.cheng.log4j.LoggerType;
import com.cheng.message.prototest.DataPro;
import com.cheng.message.prototest.ProtoHeadPro;
import com.cheng.netty.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * *  protobuf客户端启动类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class ProtoClient {

    public static final Logger logger = Logger.getLogger(LoggerType.PROTO);

    public static void main(String[] args){

        PropertyConfigurator.configure("resource/log4j.properties");

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder())
                                                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                                                    .addLast(new ProtobufDecoder(DataPro.DataMessage.getDefaultInstance()))
                                                    .addLast(new ProtobufEncoder())
                                                    .addLast(new ProtoClientHandler());

                        }

                    });

            ChannelFuture cf = bootstrap.connect("127.0.0.1", 8765).sync();
            logger.info("Proto Client Connect! localhost:8765  == >  Write to Channel.");



            cf.channel().closeFuture().sync();
            logger.info("Proto Client end! localhost:8765");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}