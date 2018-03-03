# mysql测试

mysql毕竟是一大块。更多的是指令操作，内容过于松散。
需要用时才查找文档更好。注重的是使用的过程。
所以这篇的内容和细节就不篇幅写了。移步[MySQL 教程](http://www.runoob.com/mysql/mysql-tutorial.html)
或[官方文档](https://dev.mysql.com/doc/)


这里只记录一些定义的sql语法。

**数据库操作：**
```sql
    #创建
    CREATE DATABASE IF NOT EXISTS RUNOOB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
    #选择数据库
    USE RUNOOB;
    #删除数据库
    DROP DATABASE RUNOOB;
```

**数据表操作：**
```sql
    #创建
    CREATE TABLE table_name (column_name column_type);
    #删除
    DROP TABLE table_name ;
```

**数据操作**
```sql
    #增
    INSERT INTO table_name ( field1, field2,...fieldN )
                           VALUES
                           ( value1, value2,...valueN );
    #删
    DELETE FROM table_name [WHERE Clause]
    #查
    SELECT column_name,column_name
    FROM table_name
    [WHERE Clause]
    [LIMIT N][ OFFSET M]
    #改
    UPDATE table_name SET field1=new-value1, field2=new-value2
    [WHERE Clause]
    
    #模糊查询  LIKE 
    SELECT field1, field2,...fieldN 
    FROM table_name
    WHERE field1 LIKE condition1 [AND [OR]] filed2 = 'somevalue'
    
    #组合结果集UNION:将多个select结果组合到一起
    SELECT expression1, expression2, ... expression_n
    FROM tables
    [WHERE conditions]
    UNION [ALL | DISTINCT]
    SELECT expression1, expression2, ... expression_n
    FROM tables
    [WHERE conditions];
    
    #排序 ORDER BY
    SELECT field1, field2,...fieldN table_name1, table_name2...
    ORDER BY field1, [field2...] [ASC [DESC]]
    
    #分组 GROUP BY
    SELECT column_name, function(column_name)
    FROM table_name
    WHERE column_name operator value
    GROUP BY column_name;
    
```
**几个重要点：**
1. 连接：INNER JOIN ， LEFT JOIN ，RIGHT JOIN
2. 正则
3. ALTER命令

[测试简单sql](../resource/db/TestWebServer.sql)