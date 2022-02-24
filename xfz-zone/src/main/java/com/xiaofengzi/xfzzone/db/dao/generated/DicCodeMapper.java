package com.xiaofengzi.xfzzone.db.dao.generated;

import com.xiaofengzi.xfzzone.db.domain.DicCode;
import com.xiaofengzi.xfzzone.db.domain.DicCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DicCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    int countByExample(DicCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    int deleteByExample(DicCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    int insert(DicCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    int insertSelective(DicCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    List<DicCode> selectByExample(DicCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DicCode record, @Param("example") DicCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_code
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DicCode record, @Param("example") DicCodeExample example);
}