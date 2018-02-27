package com.cheng.netty;

import java.io.Serializable;

/**
 * *  服务器下发给客户端的对象
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private Request req;

    private int responseId;

    private String name;


    public Request getReq() {
        return req;
    }

    public void setReq(Request req) {
        this.req = req;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Response { \n" +
                "reqId = " + this.responseId + "\n" +
                "username = " + this.name + "\n" +
                "Request = " + this.req + "\n" +
                "}";
    }
}