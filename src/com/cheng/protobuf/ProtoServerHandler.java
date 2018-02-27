package com.cheng.protobuf;

import com.cheng.log4j.LoggerType;
import com.cheng.message.prototest.*;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class ProtoServerHandler extends ChannelHandlerAdapter {

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
        logger.info("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        logger.info("channelRead   ==> Read from Client");

        DataPro.DataMessage dataMessage = (DataPro.DataMessage) msg;
        logger.info("\n" + dataMessage.toString());

        if (dataMessage.getStatus() >= 5)
        {
            ctx.close();
            logger.info("ServerChannel Close!!!");
            return;
        }

        logger.info("then  write to Client..");
        DataPro.DataMessage.Builder builder = DataPro.DataMessage.newBuilder();
        builder.setStatus(dataMessage.getStatus()+1);
        builder.setCmd(ProtoHeadPro.ProtoHead.LOGIN_RESP_VALUE);
        builder.setName(dataMessage.getName() + "Server_" + builder.getStatus());
        ctx.writeAndFlush(builder.build());




//        LoginReqPro.LoginReq loginReq = (LoginReqPro.LoginReq) msg;
//        logger.info(loginReq.toString());
//
//        logger.info("then  write to Client..");
//        LoginRespPro.LoginResp.Builder builder = LoginRespPro.LoginResp.newBuilder();
//        builder.setStatus(LoginRespPro.StatusCode.newBuilder()
//                .setStatus(1).
//                setTip("login Success"));
//        builder.setPlayer(PlayerMsgPro.PlayerMsg.newBuilder()
//                .setId(loginReq.getId())
//                .setName(loginReq.getName())
//                .setAge("25")
//                .setLevel(99));
//        LoginRespPro.LoginResp loginResp = builder.build();
//        ctx.writeAndFlush(loginResp);


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        logger.info("channelReadComplete");
    }
}