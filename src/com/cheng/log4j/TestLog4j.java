package com.cheng.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * *  测试log4j
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class TestLog4j {

    public static final Logger logger = Logger.getLogger(LoggerType.SYS);

    public static void main(String[] args){

        PropertyConfigurator.configure("resource/log4j.properties");
        Parent parent = new Parent();
        Son son = new Son();

        //多次打印到文件，测试RollingFileAppender模式。
        for (int i=0;i<10;i++)
        {
            parent.ToLog();
            son.ToLog();
        }


        for(int i=0;i<5;i++){
            logger.debug("HelloWorld" + i);
        }
        for(int i=0;i<5;i++){
            logger.info("从入门到放弃" + i);
        }
    }



}

