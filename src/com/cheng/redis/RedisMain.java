package com.cheng.redis;

import com.cheng.util.ObjectAndByte;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class RedisMain {

    public static void main(String[] args) throws InterruptedException {

        ChengRedisServer redisServer = new ChengRedisServer(new Jedis("localhost", 6379, 150));

//        System.out.println("是否连接成功：" + jedis.ping());

        //String
        redisServer.set("testString","dcl",RedisConst.NOTIME);
        String string = redisServer.getString("testString");
        System.out.println("[testString]=>" + string);

        //Int
        redisServer.set("testInt",10000,RedisConst.NOTIME);
        int value = redisServer.getInt("testInt");
        System.out.println("[testInt]=>" + value);

        //Object
        TestRedisModel testModel = new TestRedisModel();
        testModel.setId(1);
        testModel.setName("haha");

        redisServer.set("testObject",testModel,RedisConst.TENSECONDIME);
        System.out.println("[testObject]=>" + redisServer.getObj("testObject"));
        Thread.sleep(11000);
        System.out.println("[testObject,expire = 20]=>" + redisServer.getObj("testObject"));

        //List
        List<TestRedisModel> list = new ArrayList<TestRedisModel>();
        for (int i=0;i < 3 ;i++ ){
            testModel = new TestRedisModel();
            testModel.setId(i);
            testModel.setName("testList" + i);
            list.add(testModel);
        }
        redisServer.set("testList",list,RedisConst.NOTIME);
        System.out.println("testList =>" + redisServer.getList("testList"));

        //Map
        Map map = new HashMap<>();
        map.put("keyStr","StringValue");
        map.put("keyInt",100);
        TestRedisModel model = new TestRedisModel();
        model.setId(0);
        model.setName("mapObject");
        map.put("keyObject",model);
        List<TestRedisModel> mapList = new ArrayList<TestRedisModel>();
        for (int i=0;i < 3 ;i++ ){
            testModel = new TestRedisModel();
            testModel.setId(i);
            testModel.setName("testList" + i);
            mapList.add(testModel);
        }
        map.put("keyList",mapList);

        redisServer.set("testMap",map,RedisConst.NOTIME);
        System.out.println("testMap =>" + redisServer.getMap("testMap"));
        //遍历map
//        Map map1 = redisServer.getMap("testMap");
//
//        System.out.println("testMap =>");
//        Iterator entries = map1.entrySet().iterator();
//        while(entries.hasNext()){
//            Map.Entry entry = (Map.Entry) entries.next();
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }


//        System.out.println("==>" + redisServer.allKeys());
//        System.out.println("==>" + redisServer.exitsKey(ObjectAndByte.objectToByteArray("testMap")));
    }

}