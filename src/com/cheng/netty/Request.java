package com.cheng.netty;

import java.io.Serializable;

/**
 * *  客户端请求服务器对象
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private int reqId;
    private String name;


    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request { \n" +
                "reqId = " + this.reqId + "\n" +
                "username = " + this.name + "\n" +
                "}";
    }
}