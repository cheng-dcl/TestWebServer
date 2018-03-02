package com.cheng.mybatis.test;

import java.util.List;

/**
 * *  test对象Mapper接口
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public interface TestMapper {

    public int addTest(TestModel testModel) throws Exception;

    public int updateTest(TestModel testModel) throws  Exception;

    public TestModel selectTest(int id) throws  Exception;


    public int deleteTest(int id) throws  Exception;

    public List<TestModel> selectTests() throws  Exception;
}