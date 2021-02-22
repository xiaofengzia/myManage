package com.xiaofengzi.wxapi.db.domain;

import java.time.LocalDateTime;

public class AccountUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.user_account
     *
     * @mbg.generated
     */
    private String userAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.IS_DELETE
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.CREATED_DATE
     *
     * @mbg.generated
     */
    private LocalDateTime createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_user.MODIFIED_DATE
     *
     * @mbg.generated
     */
    private LocalDateTime modifiedDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.id
     *
     * @return the value of account_user.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.id
     *
     * @param id the value for account_user.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.user_name
     *
     * @return the value of account_user.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.user_name
     *
     * @param userName the value for account_user.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.user_account
     *
     * @return the value of account_user.user_account
     *
     * @mbg.generated
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.user_account
     *
     * @param userAccount the value for account_user.user_account
     *
     * @mbg.generated
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.password
     *
     * @return the value of account_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.password
     *
     * @param password the value for account_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.mobile
     *
     * @return the value of account_user.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.mobile
     *
     * @param mobile the value for account_user.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.remark
     *
     * @return the value of account_user.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.remark
     *
     * @param remark the value for account_user.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.IS_DELETE
     *
     * @return the value of account_user.IS_DELETE
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.IS_DELETE
     *
     * @param isDelete the value for account_user.IS_DELETE
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.CREATED_DATE
     *
     * @return the value of account_user.CREATED_DATE
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.CREATED_DATE
     *
     * @param createdDate the value for account_user.CREATED_DATE
     *
     * @mbg.generated
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_user.MODIFIED_DATE
     *
     * @return the value of account_user.MODIFIED_DATE
     *
     * @mbg.generated
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_user.MODIFIED_DATE
     *
     * @param modifiedDate the value for account_user.MODIFIED_DATE
     *
     * @mbg.generated
     */
    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}