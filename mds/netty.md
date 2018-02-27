# netty的测试用例

### 定义 
    Netty是一个高性能、异步事件驱动的NIO框架，它提供了对TCP、UDP和文件传输的支持，
    作为一个异步NIO框架，Netty的所有IO操作都是异步非阻塞的。

### Netty实现通信的步骤
> * **服务器**：
>  1. 创建两个线程组
>  2. 创建辅助工具类，用于服务器通道的一系列配置
>  3. 设置实际处理数据的类 ，自定义Handler。及相关编解码器Decoder，Encoder。
>  4. 端口绑定(异步处理)，以及等待关闭
> * **客户端**：同服务器设置一致。

### TCP粘包、拆包问题
    自定义通道处理类Handler和编解码器Decoder，Encoder。

### 源代码
* 目录：[src/com/cheng/netty](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/netty)
* 测试：先启动ServerMain，再启动ClientMain

### [Netty官方翻译文档](http://ifeve.com/netty5-user-guide/)

[website,]:http://blog.csdn.net/T1DMzks/article/details/78369686?locationNum=6&fps=1
[website,]:http://blog.csdn.net/haoyuyang/article/details/53243785
[website,]:http://blog.csdn.net/u010154380/article/details/46988269
