package com.cheng.log4j;

import org.apache.log4j.Logger;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class Parent {
    public static final Logger logger = Logger.getLogger(LoggerType.OTHER);

    public String who = "father";
    private static int count = 0;

    public void ToLog() {
        // 日志优先级从小到大  ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF
        // 由于在配置中设定了WARN，所以下面只有优先级比WARN高的才会打印
        count++;
        logger.warn("Tolog Count = " + count);
        logger.trace("trace ->" + who);
        logger.debug("debug ->" + who);
        logger.info("info -> " + who);
        logger.warn("warn ->" + who);
        logger.error("error ->" + who);
        logger.fatal("fatal ->"+ who);
    }

}