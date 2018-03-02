package com.cheng.mybatis.test;

import com.cheng.mybatis.test.mappers.ConfigUserMapper;
import com.cheng.mybatis.test.model.ConfigUser;
import com.cheng.mybatis.testByGenerator.DbTool;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * *  test表Dao层处理类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class TestDao implements TestMapper {


    @Override
    public int addTest(TestModel testModel) throws Exception {
        SqlSession session = TestMain.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);


        try{
            int index = mapper.addTest(testModel);
            boolean b = index > 0 ? true : false;
            System.out.println( b ? "Add Test =>" + testModel.toString() : "Add Test => False"  );
            session.commit();
            return index;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public int updateTest(TestModel testModel) throws Exception {
        SqlSession session = TestMain.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);

        try{
            int index = mapper.updateTest(testModel);
            boolean b = index > 0 ? true : false;
            System.out.println( b ? "Update Test => " + testModel.toString() : "Update Test => False"  );
            session.commit();
            return index;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public TestModel selectTest(int id) throws Exception {
        SqlSession session = TestMain.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);

        try{
            TestModel test = mapper.selectTest(id);
            System.out.println( test != null ? "Select Test => " + test.toString() : "Select Test => False"  );
            session.commit();
            return test;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public int deleteTest(int id) throws Exception {
        SqlSession session = TestMain.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);


        try{
            int index = mapper.deleteTest(id);
            boolean b = index > 0 ? true : false;
            System.out.println( b ? "Delete Test => Success by Id" + id : "Delete Test => False"  );
            session.commit();
            return index;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public List<TestModel> selectTests() throws Exception {
        SqlSession session = TestMain.getSession();
        TestMapper mapper = session.getMapper(TestMapper.class);

        try{
            List<TestModel> test = mapper.selectTests();
            System.out.println( test != null ? "Select Tests => " + test.toString() : "Select Tests => False"  );
            session.commit();
            return test;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}