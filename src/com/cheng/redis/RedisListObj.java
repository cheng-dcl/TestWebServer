package com.cheng.redis;

import java.io.Serializable;
import java.util.List;

/**
 * *  封装List供Redis使用
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class RedisListObj<V extends Serializable> implements  Serializable {
    private List<V> list;

    public RedisListObj(List<V> list){
        this.list = list;
    }

    public List<V> getList() {
        return list;
    }

    public void setList(List<V> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RedisListObj{" +
                "list=" + list +
                '}';
    }
}