#log4j测试用例
## 简介
    Log4j是Apache的一个开放源代码项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件、甚至是套接口服务器、NT的事件记录器、UNIX Syslog守护进程等；
    我们也可以控制每一条日志的输出格式；通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
    使用：只需要做一个文件的配置即可。
    
## 配置文件：
* xml文件
* java特性文件（键=值）.properties
```
log4j.rootLogger=DEBUG,stdout
log4j.logger.SYS=DEBUG,SYS
log4j.logger.OTHER=WARN,OTHER

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Threshold=DEBUG
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=[%t][%p][%M]%m%n

log4j.appender.SYS=org.apache.log4j.DailyRollingFileAppender
log4j.appender.SYS.file=log/sys.log
log4j.appender.SYS.DatePattern='.'yyyy-MM-dd
log4j.appender.SYS.layout=org.apache.log4j.PatternLayout
log4j.appender.SYS.layout.ConversionPattern=[%t][%d][%p][%r][%c][%F:%L:%l:%%][%M]%m%n

log4j.appender.OTHER=org.apache.log4j.RollingFileAppender
log4j.appender.OTHER.file=log/other.log
log4j.appender.OTHER.MaxFileSize=2KB
log4j.appender.OTHER.MaxBackupIndex=10
log4j.appender.OTHER.layout=org.apache.log4j.PatternLayout
log4j.appender.OTHER.layout.ConversionPattern=[%t][%d][%p][---][%F:%L]%m%n
```
## 配置说明：  
>* **1.配置根Logger：** ：  
   log4j.rootLogger = [ level ] , appenderName, appenderName, …  
   level：日志记录的级别，如info，error ...   
   appenderName:输出目的地Appder的名称
>* **2.输出目的地Appder：**   
  stdout，相当于java中的syso，SYS和OTHER为自定义<br> 
  Appder分一下几种：  <br> 
  org.apache.log4j.ConsoleAppender（控制台），<br> 
  org.apache.log4j.FileAppender（文件）， <br>
  org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），<br>
  org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件），<br> 
  org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）<br>
>* **3.日志信息的格式（布局）layout：**   
  org.apache.log4j.HTMLLayout（以HTML表格形式布局），<br> 
  org.apache.log4j.PatternLayout（可以灵活地指定布局模式），<br> 
  org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串），<br> 
  org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等等信息）<br>
>* **4.日志信息的布局的格式化参数 ConversionPattern**：   
    1）%t 用来输出生成该日志事件的线程的名称<br>
    2）%p 用于输出日志事件的优先级，即DEBUG，INFO，WARN，ERROR，FATAL<br>
    3）%r 用于输出从layout（布局）的构建到日志事件创建所经过的毫秒数<br>
    4）%c 用于输出日志事件的category（类别），通常就是所在类的全名<br>
    5）%F 用于输出被发出日志记录请求，其中的文件名<br>
    6）%d 用于输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，输出类似：20017年02月18日 22：10：28，921<br>
    7）%L 用于输出日志事件的发生位置，即在代码中的行数。举例：10<br>
    8）%l 用于输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)<br>
    9）%% 用于输出％标志<br>
    10）%M 用于输出打印该条日志的方法名<br>
    11）%m 用于输出代码中指定的消息<br>
    12）%n 用于输出一个回车换行符，Windows平台为“rn”，Unix平台为“n”<br>
>* **3.日志输出的级别：**   <br>
    1）ALL    各级包括自定义级别。<br>
    2）TRACE    最详细的信息。一般这些信息只记录到日志文件中。自版本1.2.12[3]。<br>
    3）DEBUG    流经系统的详细信息。一般这些信息只记录到日志文件中。<br>
    4）INFO    令人感兴趣的运行时事件（启动/关闭）。一般这些信息将立即呈现在状态控制台上，因而要保守使用，并保持到最低限度。<br>
    5）WARN    使用已过时的API，API的滥用，潜在错误，其他不良的或意外的运行时的状况（但不一定是错误的）。一般这些信息将立即呈现在状态控制台上。<br>
    6）ERROR    其他运行时错误或意外情况。一般这些信息将立即呈现在状态控制台上。<br>
    7）FATAL    导致应用程序提前终止的严重错误。一般这些信息将立即呈现在状态控制台上。<br>
    8）OFF    最高级别，用于关闭日志记录。<br>
    
## 使用流程：
1. 配置文件log4j.properties,如上
2. 获取日志器  
```public static final Logger logger = Logger.getLogger(String appenderName);```
3. 读取配置  
```
   BasicConfigurator.configure ()： 自动快速地使用缺省Log4j环境。 
   PropertyConfigurator.configure ( String configFilename) ：读取使用Java的特性文件编写的配置文件。 
   DOMConfigurator.configure ( String filename ) ：读取XML形式的配置文件。
```

## 源代码
* 目录：[src/com/cheng/log4j](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/log4j)
* 测试：启动TestLog4j
  
 
[website]:http://www.cnblogs.com/godtrue/p/6442347.html  
[website]:https://www.ibm.com/developerworks/cn/java/l-log4j/
