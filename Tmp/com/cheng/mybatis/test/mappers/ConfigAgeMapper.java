package com.cheng.mybatis.test.mappers;

import com.cheng.mybatis.test.model.ConfigAge;
import com.cheng.mybatis.test.model.ConfigAgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigAgeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    long countByExample(ConfigAgeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int deleteByExample(ConfigAgeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int insert(ConfigAge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int insertSelective(ConfigAge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    List<ConfigAge> selectByExample(ConfigAgeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    ConfigAge selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int updateByExampleSelective(@Param("record") ConfigAge record, @Param("example") ConfigAgeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int updateByExample(@Param("record") ConfigAge record, @Param("example") ConfigAgeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int updateByPrimaryKeySelective(ConfigAge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_age
     *
     * @mbg.generated Sat Mar 03 00:24:22 CST 2018
     */
    int updateByPrimaryKey(ConfigAge record);
}