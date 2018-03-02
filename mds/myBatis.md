[MyBatis Generator官网中文翻译]:http://mbg.cndocs.ml/index.html
[MyBatis Generator]:https://github.com/mybatis/generator/releases

[MyBatis官网中文翻译]:http://www.mybatis.org/mybatis-3/zh/index.html
[MyBatis]:https://github.com/mybatis/mybatis-3/releases

[MyBatis example]:https://www.cnblogs.com/kevin1990/p/6231122.html

[MyBatis Generator 参数说明]:http://blog.csdn.net/testcs_dn/article/details/77881776

[javaweb]:../image/mybatis/javaweb.png
[mybatis四个文件]:../image/mybatis/mybatis文件.png
[generator后文件结构]:../image/mybatis/generator后文件结构.png

# MyBatis &  MyBatis Generator 

## 简介
MyBatis 是支持普通 SQL 查询，存储过程和高级映射的优秀持久层框架。 MyBatis 消除了几乎所有的 JDBC 代码和参数的手工设置以及对结果集的检索。 MyBatis 可以使用简单的XML 或注解用于配置和原始映射，将接口和 Java 的 POJO（ Plain Old Java Objects，普通的Java 对象）映射成数据库中的记录.

  1）MyBATIS 目前提供了三种语言实现的版本，包括：Java、.NET以及Ruby。  
  2）它提供的持久层框架包括SQL Maps和Data Access Objects（DAO）。  
  3）mybatis与hibernate的对比？  
   mybatis提供一种“半自动化”的ORM实现。  
   这里的“半自动化”，是相对Hibernate等提供了全面的数据库封装机制的“全自动化”ORM实现而言，“全自动”ORM实现了POJO和数据库表之间的映射，以及 SQL 的自动生成和执行。  
   而mybatis的着力点，则在于POJO与SQL之间的映射关系。
    
## 认识MyBatis
 MyBatis具体指什么，首先看看javaWeb的包含的基本技术和流程。如下图：
 ![javaweb]
 
 **javaweb大体分为四块：**  
>* 1，Jsp： Java服务器页面。也就是我们看到的web页面。里面包含有相关web页面技术和特有的JSTL表达式。
>* 2，Servlet： 我理解为页面的中转接口，这里做Jsp的控制中心用。页面的业务逻辑在这处理。
>* 3，JDBC：持久化操作。连接数据库，读写数据。即增删改查。
>* 4，SQL：数据库。 本地保存数据的地方。 常见的有MYSQL，SQLServer，Oracle等。  
 那MyBatis和Hibernate就是做的JDBC的事，对JDBC做了封装。而mybatis更多的做的就是将数据做映射。  
 配置的方式管理sql语句。半自动化实现ORM（对象关系映射）。
 
 ## MyBatis的用法 详见[MyBatis官网中文翻译]
 **1.先认识用到的基本四个文件：**   
 **如图：**
 ![mybatis四个文件]
>* XXXModel.java: 与表格对应的对象类。如UserModel.java , 用户对象。
>* XXXMapper.java: 与对象类对应的“Dao层”实现接口,主要是一些Sql方法接口。 如UserMapper.java
>* XXXMapper.xml:映射上面的Mapper类。主要是将接口方法里的sql语句对应控制在配置中。这正是mybatis的一大特点。如UserMapper.xml
               ps：sql在配置中这个特性，也导致了一个缺点。就是相较于传统jdbc，sql语句在代码中。不便于调试SQL了。
                  但是使用log4j可以记录到sql语句的状态。当然仅仅是log。
>* mybatis.xml：mybatis配置。两大功能，1，设置数据库的连接相关。 2，映射上面Mapper.xml（多个）配置。  
```xml
<environments default="cybatis">
    <environment id="cybatis">
        <!-- type="JDBC" 代表使用JDBC的提交和回滚来管理事务 -->
        <transactionManager type="JDBC" />

        <!-- mybatis提供了3种数据源类型，分别是：POOLED,UNPOOLED,JNDI -->
        <!-- POOLED 表示支持JDBC数据源连接池 -->
        <!-- UNPOOLED 表示不支持数据源连接池 -->
        <!-- JNDI 表示支持外部数据源连接池 -->
        <dataSource type="POOLED">
            <property name="driver" value="${jdbc.driver}" />
            <property name="url" value="${jdbc.url}" />
            <property name="username" value="${jdbc.username}" />
            <property name="password" value="${jdbc.password}" />
        </dataSource>
    </environment>
</environments>


<mappers>
    <!-- 配置mapper，有更多继续添加 -->
    <mapper resource="com/cheng/mybatis/test/TestMapper.xml"/>
</mappers>
```
不难看出上面一块是设置数据库连接，下面mappers映射Mapper.xml。
   
 
 **2.mybatis流程**  
>* 根据数据库表，依据上面的文件顺序依次创立对应的数据对象model，接口mapper，接口映射mapper.xml。
      所有的表对象处理完后。 再将所有的mapper映射到mybatis.xml中。
>* 设置mybatis.xml中的数据库参数。读取mybatis.xml创建sessionFactory。
>* 获取session，即可做jdbc操作。
    
以上具体内容和配置的参数就不细说了。直接看栗子测试及配置映射关系。
    
 ## MyBatis Generator
 上面提到了每个表格都要同样的创建上面的三个文件。这么重复而又繁多的操作当然是不能容忍的。那么
 MyBatis Generator就派上用场了。按照自身规范自动化生成三个文件。详见[MyBatis Generator官网中文翻译]  
 有四种方法，这里只记录下最简单的方法。
 
 ## MyBatis Generator用法
1，先从官网上引入`mybatis和mybatis generator的jar包`。导入到工程中。[MyBatis]和[MyBatis Generator]获取。  
2，需要一个指定一个文件`generator.xml`文件。用来设置数据库表格对应的对象输出。 `请注意上面的是mybatis配置，这个是generator的。确保区分`(毕竟有在这里蒙圈了好一会。)  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration >
    <classPathEntry location="G:\IntelliJSpace\MyIntelli\TestWebServer\web\WEB-INF\lib\mysql-connector-java-5.1.17.jar" />
    <context id="context1" targetRuntime="MyBatis3" defaultModelType="conditional">
        <commentGenerator>
            <property name="suppressAllComments" value="false" />
        </commentGenerator>
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost:3306/test" userId="root" password="dcldcl" />
        <javaModelGenerator targetPackage="com.cheng.mybatis.test.model" targetProject="../../../Tmp" />
        <sqlMapGenerator targetPackage="com.cheng.mybatis.test.maps" targetProject="../../../Tmp" />
        <javaClientGenerator targetPackage="com.cheng.mybatis.test.mappers" targetProject="../../../Tmp" type="XMLMAPPER" />
        <table schema="test" tableName="config_user" domainObjectName="ConfigUser"/>
        <table schema="test" tableName="config_age" domainObjectName="ConfigAge">
           <property name="rootClass" value="com.cheng.mybatis.test.model.BaseModel" />
        </table>

    </context>
</generatorConfiguration>
```
**功能：设定数据库的连接和表格输出的三种文件路径。**
>* `classPathEntry`：mysql包的路径
>* `jdbcConnection`：mysql的连接设置
>* `javaModelGenerator`：XXXModel.java类的包路径和       `targetProject`：包根目录。
>* `sqlMapGenerator`：XXXMapper.xml的包路径
>* `javaClientGenerator`：XXXMapper.java接口的包路径。
>* `table`：数据库里的表格和生成的类名`domainObjectName`。有多少加多少，不用给我面子。     `rootClass`：指定父类

3.用generator工具生成文件。 cd到generator  jar包所在目录执行
```bat
java -jar generator.jar -configfile generator文件路径

如：
java -jar mybatis-generator-core-1.3.6.jar -configfile G:\IntelliJSpa
ce\MyIntelli\TestWebServer\resource\mybatis\generator_config.xml
```
生成如下：  
![generator后文件结构]

4.（**重要的，别忘了**）回归到Mybatis。 `将XXXMapper.xml全部加到mybatis.xml的mappers里去`。
```xml
<mappers>
    <!-- 映射文件方式1，一个一个的配置 -->
    <mapper resource="com/cheng/mybatis/test/maps/ConfigUserMapper.xml" />
    <mapper resource="com/cheng/mybatis/test/maps/ConfigAgeMapper.xml" />


    <!-- 映射文件方式2，自动扫描包内的Mapper接口与配置文件 -->
    <!--<package name="Tmp/com/cheng/mabatis/test/maps"/>-->
</mappers>
```


### 源代码

1. [MyBatis测试](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/mybatis/test):  
修改TestMain中的数据库连接参数,创建对应的表，或者自行建表修改配置映射  ，运行TestMain   
2.[使用Mybatis Generator后的测试](https://github.com/dcl-Cheng/TestWebServer/tree/master/src/com/cheng/mybatis/testByGenerator):
修改DbTool中的数据库连接参数,创建对应的表，或者自行建表修改配置映射 ,运行UserService  
