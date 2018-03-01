# google Protocol Buffer 的测试用例 


### 简介 
    Protocol Buffer(以下简称Protobuf)是google 的一种数据交换的格式，它独立于语言，独立于平台。
    google 提供了三种语言的实现：java、c++ 和 python，每一种实现都包含了相应语言的编译器以及库文件。
    由于它是一种二进制的格式，比使用 xml 进行数据交换快许多。可以把它用于分布式应用之间的数据通信或者异构环境下的数据交换。
    作为一种效率和兼容性都很优秀的二进制数据传输格式，可以用于诸如网络传输、配置文件、数据存储等诸多领域。
     
### 安装
* **Java**  
   需要两个文件 **`protobuf_java_x_x.jar`**和对应版本的**`protoc.exe`**，用与解析proto文件为java文件。
   [下载对应版本源码和protoc.exe工具](https://github.com/google/protobuf/releases)。[并使用原方式生成jar包。](http://blog.csdn.net/u013656135/article/details/52194248)
 
* **C#**
   生成文件c#用protobuf的官方方式没看，貌似比较复杂，环境也比较复杂。简单方式下载protobuf-net工具。  
   [批量导入生成方式](https://www.cnblogs.com/hammerc/p/4663469.html)
 
### 解析方式
cd到protoc.exe目录，执行下面语法。 可以写bat文件和python文件等各种脚本语言，方便自动化解析proto文件。


``` 
Java方式：
protoc --java_out=目标java包目录  --proto_path=proto包目录目录  proto文件目录
protoc --java_out=../../src --proto_path=./test ./test/*.proto

（单个生成，使用批量导入，貌似proto位置只能是当前位置，无法设置）
c#方式:
protobuf-net\ProtoGen\protogen -i:proto文件 -o:cs文件    

protobuf-net\ProtoGen\protogen -i:Cmd.proto -o:Cmd.cs

```

### proto文件常见语言（2.x版本）
> **3.x版本新增头部增加syntax=“proto3”，细节查看官网**

|语言|说明|
|:---------|:---------|
|message|定义消息类型 => class|
|enum|枚举|
|package|包|
|import|导入 => import|
|option|选项|
|optional|可填写参数|
|required|必填参数|
|repeated|多个 => Array|
|default|默认值|
|--|--|
|java_package|java文件目的包名|
|java_outer_classname|java文件的类名。**千万不能与message名称一样**|

例：(`参数后面的值依次增加`)
```$xslt
    package proto;
    
    option java_package = "com.cheng.message.prototest";
    option java_outer_classname = "LoginRespPro";
    
    import "test/PlayerMsg.proto";
    
    message LoginResp{
        optional PlayerMsg player = 1;
        optional StatusCode status = 2;
    
    }
    
    message StatusCode{
        optional int32 status = 1 [default = 1];
        optional string tip = 2;
    }
```

### proto文件数值： 略


### 源代码
* 目录：[src/com/cheng/protobuf](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/protobuf)
* 测试：1：单独测试proto对象，启动TestProtobuf . 分别测试了3.5.1和2.4.1版本。 
&emsp;&emsp;&emsp;2：结合netty，log4j和proto测试。先启动ProtoServer，再启动ProtoClient。（2.4.1版本）

[website,]:http://shift-alt-ctrl.iteye.com/blog/2210885
[website,]:https://www.cnblogs.com/hammerc/p/4663469.html

