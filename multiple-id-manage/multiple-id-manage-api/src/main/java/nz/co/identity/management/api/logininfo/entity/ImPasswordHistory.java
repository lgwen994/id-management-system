package nz.co.identity.management.api.logininfo.entity;
import java.util.Date;
import java.util.Map;

/**
 * passwordhistory information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImPasswordHistory {
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
     * version no
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer versionNo;

    /**
     * password history
     *
     * @since Staveware Core Ver.5.3
     */
    private String passwordHistory;

    /**
     * password changed time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date passwordChangedTime;

    /**
     * password changed user
     *
     * @since Staveware Core Ver.5.3
     */
    private String passwordChangedUser;

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
     * extend map
     * 
     * @since Staveware Core Ver.5.3
     */
    private Map<String, Object> extendsMap;

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
     *        password history information entity
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
     *        password history information entity
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
     *        password history information entity
     * @since Staveware Core Ver.5.3
     */
    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * passwordHistory 's getter.
     *
     * @return passwordHistory
     * @since Staveware Core Ver.5.3
     */
    public String getPasswordHistory() {
        return passwordHistory;
    }

    /**
     * passwordHistory 's setter.
     *
     * @param passwordHistory
     *        password history information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordHistory(String passwordHistory) {
        if (passwordHistory != null) {
            this.passwordHistory = passwordHistory.trim();
        } else {
            this.passwordHistory = null;
        }
    }

    /**
     * passwordChangedTime 's getter.
     *
     * @return passwordChangedTime
     * @since Staveware Core Ver.5.3
     */
    public Date getPasswordChangedTime() {
        return passwordChangedTime;
    }

    /**
     * passwordChangedTime 's setter.
     *
     * @param passwordChangedTime
     *        password history information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordChangedTime(Date passwordChangedTime) {
        this.passwordChangedTime = passwordChangedTime;
    }

    /**
     * passwordChangedUser 's getter.
     *
     * @return passwordChangedUser
     * @since Staveware Core Ver.5.3
     */
    public String getPasswordChangedUser() {
        return passwordChangedUser;
    }

    /**
     * passwordChangedUser 's setter.
     *
     * @param passwordChangedUser
     *        password history information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordChangedUser(String passwordChangedUser) {
        if (passwordChangedUser != null) {
            this.passwordChangedUser = passwordChangedUser.trim();
        } else {
            this.passwordChangedUser = null;
        }
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
     *        password history information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * createdUser 's getter.
     *
     * @return createdUser
     * @since Staveware Core Ver.5.3
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * createdUser 's setter.
     *
     * @param createdUser
     *        password history information entity
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