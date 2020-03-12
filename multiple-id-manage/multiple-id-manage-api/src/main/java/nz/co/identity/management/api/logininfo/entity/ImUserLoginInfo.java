package nz.co.identity.management.api.logininfo.entity;
import java.util.Date;
import java.util.Map;

/**
 * userlogininfo information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImUserLoginInfo {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_USER_LOGIN_INFO_SERIAL_TABLE";
    /**
     * user login info id
     *
     * @since Staveware Core Ver.5.3
     */
    private String userLoginInfoId;

    /**
     * user login info code
     *
     * @since Staveware Core Ver.5.3
     */
    private String userLoginInfoCode;

    /**
     * user id
     *
     * @since Staveware Core Ver.5.3
     */
    private String userId;

    /**
     * login id
     *
     * @since Staveware Core Ver.5.3
     */
    private String loginId;

    /**
     * company code
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyCode;

    /**
     * active start time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date activeStartTime;

    /**
     * active end time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date activeEndTime;

    /**
     * created time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date createdTime;

    /**
     * created user
     *
     * @since Staveware Core Ver.5.3
     */
    private String createdUser;

    /**
     * updated time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date updatedTime;

    /**
     * updated user
     *
     * @since Staveware Core Ver.5.3
     */
    private String updatedUser;

    /**
     * deleted flg
     *
     * @since Staveware Core Ver.5.3
     */
    private Short deletedFlg;

    /**
     * version no
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer versionNo;

    /**
     * extend map
     * 
     * @since Staveware Core Ver.5.3
     */
    private Map<String, Object> extendsMap;

    /**
     * userLoginInfoId 's getter.
     *
     * @return userLoginInfoId
     * @since Staveware Core Ver.5.3
     */
    public String getUserLoginInfoId() {
        return userLoginInfoId;
    }

    /**
     * userLoginInfoId 's setter.
     *
     * @param userLoginInfoId
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUserLoginInfoId(String userLoginInfoId) {
        if (userLoginInfoId != null) {
            this.userLoginInfoId = userLoginInfoId.trim();
        } else {
            this.userLoginInfoId = null;
        }
    }

    /**
     * userLoginInfoCode 's getter.
     *
     * @return userLoginInfoCode
     * @since Staveware Core Ver.5.3
     */
    public String getUserLoginInfoCode() {
        return userLoginInfoCode;
    }

    /**
     * userLoginInfoCode 's setter.
     *
     * @param userLoginInfoCode
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUserLoginInfoCode(String userLoginInfoCode) {
        if (userLoginInfoCode != null) {
            this.userLoginInfoCode = userLoginInfoCode.trim();
        } else {
            this.userLoginInfoCode = null;
        }
    }

    /**
     * userId 's getter.
     *
     * @return userId
     * @since Staveware Core Ver.5.3
     */
    public String getUserId() {
        return userId;
    }

    /**
     * userId 's setter.
     *
     * @param userId
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUserId(String userId) {
        if (userId != null) {
            this.userId = userId.trim();
        } else {
            this.userId = null;
        }
    }

    /**
     * loginId 's getter.
     *
     * @return loginId
     * @since Staveware Core Ver.5.3
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * loginId 's setter.
     *
     * @param loginId
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLoginId(String loginId) {
        if (loginId != null) {
            this.loginId = loginId.trim();
        } else {
            this.loginId = null;
        }
    }

    /**
     * companyCode 's getter.
     *
     * @return companyCode
     * @since Staveware Core Ver.5.3
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * companyCode 's setter.
     *
     * @param companyCode
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCompanyCode(String companyCode) {
        if (companyCode != null) {
            this.companyCode = companyCode.trim();
        } else {
            this.companyCode = null;
        }
    }

    /**
     * activeStartTime 's getter.
     *
     * @return activeStartTime
     * @since Staveware Core Ver.5.3
     */
    public Date getActiveStartTime() {
        return activeStartTime;
    }

    /**
     * activeStartTime 's setter.
     *
     * @param activeStartTime
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveStartTime(Date activeStartTime) {
        this.activeStartTime = activeStartTime;
    }

    /**
     * activeEndTime 's getter.
     *
     * @return activeEndTime
     * @since Staveware Core Ver.5.3
     */
    public Date getActiveEndTime() {
        return activeEndTime;
    }

    /**
     * activeEndTime 's setter.
     *
     * @param activeEndTime
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * createdTime 's getter.
     *
     * @return createdTime
     * @since Staveware Core Ver.5.3
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * createdTime 's setter.
     *
     * @param createdTime
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * createdTime 's getter.
     *
     * @return createdTime
     * @since Staveware Core Ver.5.3
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * createdUser 's setter.
     *
     * @param createdUser
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedUser(String createdUser) {
        if (createdUser != null) {
            this.createdUser = createdUser.trim();
        } else {
            this.createdUser = null;
        }
    }

    /**
     * updatedTime 's getter.
     *
     * @return updatedTime
     * @since Staveware Core Ver.5.3
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * updatedTime 's setter.
     *
     * @param updatedTime
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * updatedUser 's getter.
     *
     * @return updatedUser
     * @since Staveware Core Ver.5.3
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * updatedUser 's setter.
     *
     * @param updatedUser
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUpdatedUser(String updatedUser) {
        if (updatedUser != null) {
            this.updatedUser = updatedUser.trim();
        } else {
            this.updatedUser = null;
        }
    }

    /**
     * deletedFlg 's getter.
     *
     * @return deletedFlg
     * @since Staveware Core Ver.5.3
     */
    public Short getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * deletedFlg 's setter.
     *
     * @param deletedFlg
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setDeletedFlg(Short deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * versionNo 's getter.
     *
     * @return versionNo
     * @since Staveware Core Ver.5.3
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * versionNo 's setter.
     *
     * @param versionNo
     *        user login information entity
     * @since Staveware Core Ver.5.3
     */
    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * extend map's getter.
     *
     * @return extend map
     * @since Staveware Core Ver.5.3
     */
    public Map<String, Object> getExtendsMap() {
        return extendsMap;
    }

    /**
     * extend map's setter.
     *
     * @param extendsMap
     *        extend map
     * @since Staveware Core Ver.5.3
     */
    public void setExtendsMap(Map<String, Object> extendsMap) {
        this.extendsMap = extendsMap;
    }
}