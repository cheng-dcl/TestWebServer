package com.cheng.redis;

import com.cheng.util.ObjectAndByte;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * *  对Redis存取做一个封装，作用于使用java的对象存取方式
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class ChengRedisServer<K extends Object,V extends Serializable> {

    public Jedis jedis;

    public ChengRedisServer(Jedis jedis){
        this.jedis = jedis;
    }


    public Set<String> allKeys(){
        return jedis.keys("*");
    }
    public Set<String> keys(String string){
        return jedis.keys(string);
    }

    public boolean del(String key){
        return jedis.del(key) > 0 ? true : false;
    }
    public boolean delByte(String key){
        return jedis.del(ObjectAndByte.objectToByteArray(key)) > 0 ? true : false;
    }

    public boolean exitsKey(String key){
        return jedis.exists(key);
    }
    public boolean exitsKey(byte[] key){
        return jedis.exists(key);
    }


    public void set(String key,String value,int expire){
        jedis.set(key,value);
        setExpire(key,expire);
    }

    public void set(String key,Integer integer,int expire){
        set(key,integer.toString(),expire);
    }

    public void set(String key,V value,int expire){
        setBytes(key,value);
        setByteExpire(key,expire);
    }

    public void set(String key,List<V> list,int expire){
        setBytes(key,new RedisListObj(list));
        setByteExpire(key,expire);
    }

    public void set(String key, Map<K,V> map,int expire){
        setBytes(key,new RedisMapObj(map));
        setByteExpire(key,expire);
    }

    public String getString(String key){
        return jedis.get(key);
    }

    public Integer getInt(String key){
        return Integer.parseInt(getString(key));
    }

    public V getObj(String key) {
        byte[] bytes = jedis.get(ObjectAndByte.objectToByteArray(key));
        V obj = (V) ObjectAndByte.byteArrayToObject(bytes);
        return obj;
    }

    public List<V> getList(String key){
        RedisListObj redisListObj = (RedisListObj) getObj(key);
        return redisListObj.getList();
    }

    public Map<K,V> getMap(String key){
        RedisMapObj redisMapObj = (RedisMapObj) getObj(key);
        return redisMapObj.getMap();
    }


    private void setBytes(String key,Object value){
        jedis.set(ObjectAndByte.objectToByteArray(key),ObjectAndByte.objectToByteArray(value));
    }

    private void setExpire(String key,int time){
        if (RedisConst.NOTIME != time ){
            jedis.expire(key, time);
        }
    }

    private void setByteExpire(String key,int time){
        if (RedisConst.NOTIME != time ){
            jedis.expire(ObjectAndByte.objectToByteArray(key), time);
        }
    }
}