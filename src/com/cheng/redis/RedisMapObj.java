package com.cheng.redis;

import java.io.Serializable;
import java.util.Map;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class RedisMapObj<K extends Object,V extends Serializable> implements Serializable{
    private Map<K,V> map;

    public RedisMapObj(Map<K, V> map) {
        this.map = map;
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "RedisMapObj{" +
                "map=" + map +
                '}';
    }
}