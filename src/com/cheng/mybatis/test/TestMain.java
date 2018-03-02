package com.cheng.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class TestMain {

    private static SqlSessionFactory sessionFactory;

    public static void main(String[] args)throws Exception{

        PropertyConfigurator.configure("resource/log4j.properties");

        creatSessionFactory();

        TestDao testDao = new TestDao();

        //Add
        TestModel testModel = new TestModel();
        testModel.setId(1);
        testModel.setName("test1");
        TestModel testModel2 = new TestModel();
        testModel2.setId(2);
        testModel2.setName("test2");
        testDao.addTest(testModel);
        testDao.addTest(testModel2);

        //select
//        testDao.selectTest(1);
//        testDao.selectTests();

        //update
//        TestModel testModel3 = new TestModel();
//        testModel3.setId(2);
//        testModel3.setName("hello");
//        testDao.updateTest(testModel3);

        //delete
//        testDao.deleteTest(1);
//        testDao.selectTests();
    }


    public static void creatSessionFactory() {


        Properties dbProperties = new Properties();
        dbProperties.put("jdbc.driver","com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        dbProperties.put("jdbc.url",url);
        dbProperties.put("jdbc.username","root");
        dbProperties.put("jdbc.password","dcldcl");

        try {
            //读到mybatis.xml文件
            Reader reader = org.apache.ibatis.io.Resources.getResourceAsReader("com/cheng/mybatis/test/mybatis.xml");
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