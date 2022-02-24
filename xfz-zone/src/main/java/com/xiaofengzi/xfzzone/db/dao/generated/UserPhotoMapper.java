package com.xiaofengzi.xfzzone.db.dao.generated;

import com.xiaofengzi.xfzzone.db.domain.UserPhoto;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPhotoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    int countByExample(UserPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    int deleteByExample(UserPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    int insert(UserPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    int insertSelective(UserPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    List<UserPhoto> selectByExample(UserPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserPhoto record, @Param("example") UserPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_photo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserPhoto record, @Param("example") UserPhotoExample example);
}