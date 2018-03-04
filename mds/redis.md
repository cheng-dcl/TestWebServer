# Redis & Jedis的测试用例

### 简介
 REmote DIctionary Server(Redis) 是一个由Salvatore Sanfilippo写的key-value存储系统。 
 Redis是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。 
 它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。
 
 `主要作用于缓存`。
 
 
### redis安装&使用
1。[下载redis](https://github.com/MSOpenTech/redis/releases)  
2。cd到安装目录。或者配下环境变量。 使用cmd窗口，执行： `redis-server.exe redis.windows.conf` 开启服务器。  
3。开启另一cmd窗口（客户终端）cd到目录。执行：`redis-cli` 开启一个连接。   
4。具体使用和教程细节内容，移步[Redis教程](http://www.runoob.com/redis/redis-tutorial.html)
   
### jedis的使用
**类库：需要用来连接redis的jar包jedis。**[下载jedis jar包](http://central.maven.org/maven2/redis/clients/jedis/2.9.0/jedis-2.9.0.jar)  
**1.连接redis：只需传入`ip，端口`即可获取一个jedis连接。**
```java
  Jedis jedis = new Jedis("localhost", 6379);
```
**2.因为redis是以键值对存储的。仔细看jedis api存取方法有两种(基本方法)：**
```java
   #string存储
   set(String key,String value)
   
   #byte[]存储
   set(byte[],byte[] value)
   
   get(String key)
   get(byte[])
```
**3.存储简单类型是可以的，但是对象就没法直接存储，只能用byte[]方式。所以得对jedis进行一层封装。  
封装几个java常用类型的存储。这样java就不关心数据怎么转换，按平常一样键值存取即可。**
```java
       #java直接调用的
       public void set(String key,String value,int expire);

       public void set(String key,Integer integer,int expire);

       public void set(String key,V value,int expire);

       public void set(String key,List<V> list,int expire);
   
       public void set(String key, Map<K,V> map,int expire);
   
       public String getString(String key);
   
       public Integer getInt(String key);
   
       public V getObj(String key);
   
       public List<V> getList(String key);
   
       public Map<K,V> getMap(String key);
       
       
       #list和map是没法直接转换的。还得把list和map等列表封装一层，转换为一个Object，里面存放list和map
       class RedisListObj<V extends Serializable> implements  Serializable {
           private List<V> list;
       }
       class RedisMapObj<K extends Object,V extends Serializable> implements Serializable{
           private Map<K,V> map;
       }
```   

### 源代码：
* 目录：[src/com/cheng/redis](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/redis)
* 测试：启动RedisMain

[website]:http://blog.csdn.net/Leonardo9029/article/details/42550423