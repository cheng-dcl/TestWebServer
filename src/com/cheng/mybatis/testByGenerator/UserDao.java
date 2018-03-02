package com.cheng.mybatis.testByGenerator;

import com.cheng.log4j.LoggerType;
import com.cheng.mybatis.test.mappers.ConfigUserMapper;
import com.cheng.mybatis.test.model.ConfigUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

/**
 * *  User对象Dao层处理类
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class UserDao {
    public static final Logger logger = Logger.getLogger(LoggerType.MYBATIS);

    public boolean addUser(ConfigUser user)
    {
        SqlSession session = DbTool.getSession();
        ConfigUserMapper mapper = session.getMapper(ConfigUserMapper.class);


        try{
            int index = mapper.insert(user);
            boolean b = index > 0 ? true : false;
            logger.info( b ? "Add User =>" + user.toString() : "Add User => False"  );
            session.commit();
            return b;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public boolean deleteUser(int id)
    {
        SqlSession session = DbTool.getSession();
        ConfigUserMapper mapper = session.getMapper(ConfigUserMapper.class);

        try{
            int index = mapper.deleteByPrimaryKey(id);
            boolean b = index > 0 ? true : false;
            logger.info( b ? "Delete User => Success by Id" + id : "Add User => False"  );
            session.commit();
            return b;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public ConfigUser selectUserById(int id)
    {
        SqlSession session = DbTool.getSession();
        ConfigUserMapper mapper = session.getMapper(ConfigUserMapper.class);

        try{
            ConfigUser user = mapper.selectByPrimaryKey(id);
            logger.info( user != null ? "Select User => " + user.toString() : "Select User => False"  );
            session.commit();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public boolean updateUser(ConfigUser user){
        SqlSession session = DbTool.getSession();
        ConfigUserMapper mapper = session.getMapper(ConfigUserMapper.class);

        try{
            int index = mapper.updateByPrimaryKey(user);
            boolean b = index > 0 ? true : false;
            logger.info( b ? "Update User => " + user.toString() : "Update User => False"  );
            session.commit();
            return b;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return false;
        }finally {
            session.close();
        }
    }
}