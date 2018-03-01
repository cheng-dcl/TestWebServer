package com.cheng.protobuf;


import com.cheng.message.prototest.PlayerMsgPro;
import com.cheng.message.prototest3.PlayerMsg3Pro;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * *  protobuf简单对象测试类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class TestProtobuf {
    public static void main(String[] args){

//        /**
//         * Protobuf3.5.1 测试
//         */
//
//        PlayerMsg3Pro.PlayerMsg3.Builder builder = PlayerMsg3Pro.PlayerMsg3.newBuilder();
//
//        builder.setId(1);
//        builder.setAge("25");
//        builder.setName("dcl");
//        builder.setLevel(99);
//
//
//
//        PlayerMsg3Pro.PlayerMsg3 playerMsg1 = builder.build();
//
//        byte[] bytes =  playerMsg1.toByteArray();
//
//
//        try {
//            PlayerMsg3Pro.PlayerMsg3 playerMsg2 = PlayerMsg3Pro.PlayerMsg3.parseFrom(bytes);
//
//            System.out.println("Player1 { " +
//                    "Id:" + playerMsg1.getId() +
//                    "name: " + playerMsg1.getName() +
//                    "}");
//
//            System.out.println("Player2 { " +
//                    "Id:" + playerMsg2.getId() +
//                    "name: " + playerMsg2.getName() +
//                    "}");
//
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }


        /**
         * Protobuf2.4.1 测试
         */
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