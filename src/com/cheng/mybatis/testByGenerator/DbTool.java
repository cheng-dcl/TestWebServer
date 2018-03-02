package com.cheng.mybatis.testByGenerator;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * *  数据库连接工具，提供session
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class DbTool {


    private static SqlSessionFactory sessionFactory ;

    static {
        //设置数据库连接数据,写入到mybatis.xml的文件中值中
        // 这些参数正式工作中尽量配置在配置文件中
        Properties dbProperties = new Properties();
        dbProperties.put("driver","com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        dbProperties.put("url",url);
        dbProperties.put("username","root");
        dbProperties.put("password","dcldcl");

        try {
            //读到mybatis.xml文件
            Reader reader = org.apache.ibatis.io.Resources.getResourceAsReader("com/cheng/mybatis/test/MybatisTestConfig.xml");
            //配置数据写到mybatis.xml中，并获取sessionFactory
            sessionFactory = new SqlSessionFactoryBuilder().build(reader,dbProperties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        return sessionFactory.openSession();
    }

}