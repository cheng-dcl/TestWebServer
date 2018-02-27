# google Protocol Buffer 的测试用例 

### 简介 
     Protocol Buffer(以下简称Protobuf)是google 的一种**数据交换的格式**，它独立于语言，独立于平台。
     google 提供了三种语言的实现：java、c++ 和 python，每一种实现都包含了相应语言的编译器以及库文件。
     由于它是一种二进制的格式，比使用 xml 进行数据交换快许多。可以把它用于分布式应用之间的数据通信或者异构环境下的数据交换。
     作为一种效率和兼容性都很优秀的二进制数据传输格式，可以用于诸如网络传输、配置文件、数据存储等诸多领域
     
### 安装
* **Java**  
   需要两个文件 `**protobuf_java_x_x.jar**`包和对应版本的`**protoc.exe**`，用与解析proto文件到java文件。
   
 
### 解析方式
cd到protoc.exe目录，执行下面语法。 可以写入bat文件等各种脚本语言，方便自动化解析proto文件。

```
protoc --java_out=目标java包目录   proto文件目录

protoc --java_out=../../src ./test/*.proto
```

### proto文件常见语言
|语言|说明|
|:---------|:---------|
|message|定义消息类型 => class|
|enum|枚举|
|package|包|
|optional|可填写参数|
|required|必填参数|
|repeated|多个 => Array|
|option|选项|
|import|导入 => import|
|||
|java_package|java文件目的地包|
|java_outer_classname|java文件的类名。**千万不能与message名称一样**|

**例：(`参数后面的值依次增加`)**
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
        optional int32 status = 1;
        optional string tip = 2;
    }
```

### proto文件数值： 略


### 源代码
* 目录：[src/com/cheng/protobuf](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/protobuf)
* 测试：1：单独测试proto对象，启动TestProtobuf  
&emsp;&emsp;&emsp;2：结合netty，log4j和proto测试。先启动ProtoServer，再启动ProtoClient。

