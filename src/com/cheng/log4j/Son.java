package com.cheng.log4j;

import org.apache.log4j.Logger;

/**
 * *  测试logger是否可以继承父类，看很多项目中写的每个类都有，有疑问。
 * *  测试可以
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class Son extends Parent {

    @Override
    public void ToLog( ) {
        who = "Son";
        super.ToLog();
        logger.warn("这是son单独的一条日志");
    }
}