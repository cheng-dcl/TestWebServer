package com.cheng.mybatis.testByGenerator;

import com.cheng.log4j.LoggerType;
import com.cheng.mybatis.test.mappers.ConfigUserMapper;
import com.cheng.mybatis.test.model.ConfigUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * *  config_user数据库按照mybatis做jdbc
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class UserService {

    public static final Logger logger = Logger.getLogger(LoggerType.MYBATIS);

    public static void main(String[] args){
        PropertyConfigurator.configure("resource/log4j.properties");

        UserDao userDao = new UserDao();
        /**
         * Add
         */
        ConfigUser user;
        for (int i = 0 ; i < 10 ;i++){
            user = new ConfigUser();
            user.setId(i);
            user.setName("dcl"+i);
            userDao.addUser(user);
        }

        /**
         * Delete
         */
        userDao.deleteUser(0);


        /**
         * Select
         */
         user = userDao.selectUserById(0);
         System.out.println(user != null ? "uer = " + user.getName() : "user = null");
         user = userDao.selectUserById(9);
         System.out.println(user != null ? "uer = " + user.getName() : "user = null");


        /**
         * Update
         */
        user = new ConfigUser();
        user.setId(9);
        user.setName("从入门到放弃");
        userDao.updateUser(user);
        System.out.println("Update = > " + userDao.selectUserById(9).getName());
    }


}