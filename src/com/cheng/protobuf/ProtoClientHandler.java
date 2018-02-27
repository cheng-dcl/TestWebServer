package com.cheng.protobuf;

import com.cheng.log4j.LoggerType;
import com.cheng.message.prototest.DataPro;
import com.cheng.message.prototest.LoginReqPro;
import com.cheng.message.prototest.LoginRespPro;
import com.cheng.message.prototest.ProtoHeadPro;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class ProtoClientHandler extends ChannelHandlerAdapter {


    public static final Logger logger = Logger.getLogger(LoggerType.PROTO);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
        logger.info("exceptionCaught");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        logger.info("channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("channelActive  ==> write to Server. ");


        DataPro.DataMessage.Builder builder = DataPro.DataMessage.newBuilder();
        builder.setName("dcl");
        builder.setCmd(ProtoHeadPro.ProtoHead.LOGIN_REQ_VALUE);
        builder.setStatus(0);
        DataPro.DataMessage dataMessage = builder.build();

        ctx.channel().writeAndFlush(dataMessage);



//        LoginReqPro.LoginReq.Builder builder = LoginReqPro.LoginReq.newBuilder();
//        builder.setId(1);
//        builder.setName("dcl");
//        builder.setPassword("123456");
//        LoginReqPro.LoginReq loginReq = builder.build();
//
//        ctx.writeAndFlush(loginReq);


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        logger.info("channelRead");

        logger.info("channelRead   ==> Read from Server");
        DataPro.DataMessage dataMessage = (DataPro.DataMessage) msg;
        logger.info("\n" + dataMessage.toString());

        logger.info("then  write to Server..");
        DataPro.DataMessage.Builder builder = DataPro.DataMessage.newBuilder();
        builder.setStatus(dataMessage.getStatus()+1);
        builder.setCmd(ProtoHeadPro.ProtoHead.LOGIN_REQ_VALUE);
        builder.setName(dataMessage.getName() + "Client_" + builder.getStatus());
        ctx.writeAndFlush(builder.build());


//        logger.info("channelRead   ==> Read from Server");
//        LoginRespPro.LoginResp loginResp = (LoginRespPro.LoginResp) msg;
//        logger.info(loginResp.toString());

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        logger.info("channelReadComplete");
    }
}