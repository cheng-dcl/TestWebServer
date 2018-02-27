package com.cheng.protobuf;


import com.cheng.message.prototest.PlayerMsgPro;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * *  protobuf简单对象测试类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class TestProtobuf {
    public static void main(String[] args){

        PlayerMsgPro.PlayerMsg.Builder builder = PlayerMsgPro.PlayerMsg.newBuilder();
        builder.setId(1);
        builder.setAge("25");
        builder.setName("dcl");
        builder.setLevel(99);

        PlayerMsgPro.PlayerMsg playerMsg1 = builder.build();

        byte[] bytes =  playerMsg1.toByteArray();


        try {
            PlayerMsgPro.PlayerMsg playerMsg2 = PlayerMsgPro.PlayerMsg.parseFrom(bytes);

            System.out.println("Player1 { " +
                    "Id:" + playerMsg1.getId() +
                    "name: " + playerMsg1.getName() +
                    "}");

            System.out.println("Player2 { " +
                    "Id:" + playerMsg2.getId() +
                    "name: " + playerMsg2.getName() +
                    "}");

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}