package test.model;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_user
 */
public class TUser {
    /**
     * Database Column Remarks:
     *   用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * Database Column Remarks:
     *   用户名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Database Column Remarks:
     *   1:普通用户, 2:管理员
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_type
     *
     * @mbg.generated
     */
    private Byte userType;

    /**
     * Database Column Remarks:
     *   0:未锁定, 1:锁定
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.locked
     *
     * @mbg.generated
     */
    private Byte locked;

    /**
     * Database Column Remarks:
     *   积分
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.credit
     *
     * @mbg.generated
     */
    private Integer credit;

    /**
     * Database Column Remarks:
     *   最后登陆时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.last_visit
     *
     * @mbg.generated
     */
    private Date lastVisit;

    /**
     * Database Column Remarks:
     *   最后登录IP
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.last_ip
     *
     * @mbg.generated
     */
    private String lastIp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_id
     *
     * @return the value of t_user.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_id
     *
     * @param userId the value for t_user.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_name
     *
     * @return the value of t_user.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_name
     *
     * @param userName the value for t_user.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_type
     *
     * @return the value of t_user.user_type
     *
     * @mbg.generated
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_type
     *
     * @param userType the value for t_user.user_type
     *
     * @mbg.generated
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.locked
     *
     * @return the value of t_user.locked
     *
     * @mbg.generated
     */
    public Byte getLocked() {
        return locked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.locked
     *
     * @param locked the value for t_user.locked
     *
     * @mbg.generated
     */
    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.credit
     *
     * @return the value of t_user.credit
     *
     * @mbg.generated
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.credit
     *
     * @param credit the value for t_user.credit
     *
     * @mbg.generated
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.last_visit
     *
     * @return the value of t_user.last_visit
     *
     * @mbg.generated
     */
    public Date getLastVisit() {
        return lastVisit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.last_visit
     *
     * @param lastVisit the value for t_user.last_visit
     *
     * @mbg.generated
     */
    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.last_ip
     *
     * @return the value of t_user.last_ip
     *
     * @mbg.generated
     */
    public String getLastIp() {
        return lastIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.last_ip
     *
     * @param lastIp the value for t_user.last_ip
     *
     * @mbg.generated
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }
}