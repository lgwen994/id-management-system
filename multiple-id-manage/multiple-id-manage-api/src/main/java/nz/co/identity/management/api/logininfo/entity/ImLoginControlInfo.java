package nz.co.identity.management.api.logininfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * logincontrol information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImLoginControlInfo {
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
     * loginId 's getter.
     *
     * @return loginId
     * @since Staveware Core Ver.5.3
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * account inactive flg
     *
     * @since Staveware Core Ver.5.3
     */
    private Short accountInactiveFlg;

    /**
     * account active start time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date accountActiveStartTime;

    /**
     * account active end time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date accountActiveEndTime;

    /**
     * password failure count
     *
     * @since Staveware Core Ver.5.3
     */
    private Short passwordFailureCount;

    /**
     * password failure reset time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date passwordFailureResetTime;

    /**
     * lockout end time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date lockoutEndTime;

    /**
     * last login time
     *
     * @since Staveware Core Ver.5.3
     */
    private Date lastLoginTime;

    /**
     * extend map
     * 
     * @since Staveware Core Ver.5.3
     */
    private Map<String, Object> extendsMap;

    /**
     * loginId 's setter.
     *
     * @param loginId
     *        login control information entity
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
     *        login control information entity
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
     * accountInactiveFlg 's getter.
     *
     * @return accountInactiveFlg
     * @since Staveware Core Ver.5.3
     */
    public Short getAccountInactiveFlg() {
        return accountInactiveFlg;
    }

    /**
     * accountInactiveFlg 's setter.
     *
     * @param accountInactiveFlg
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setAccountInactiveFlg(Short accountInactiveFlg) {
        this.accountInactiveFlg = accountInactiveFlg;
    }

    /**
     * accountActiveStartTime 's getter.
     *
     * @return accountActiveStartTime
     * @since Staveware Core Ver.5.3
     */
    public Date getAccountActiveStartTime() {
        return accountActiveStartTime;
    }

    /**
     * accountActiveStartTime 's setter.
     *
     * @param accountActiveStartTime
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setAccountActiveStartTime(Date accountActiveStartTime) {
        this.accountActiveStartTime = accountActiveStartTime;
    }

    /**
     * accountActiveEndTime 's getter.
     *
     * @return accountActiveEndTime
     * @since Staveware Core Ver.5.3
     */
    public Date getAccountActiveEndTime() {
        return accountActiveEndTime;
    }

    /**
     * accountActiveEndTime 's setter.
     *
     * @param accountActiveEndTime
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setAccountActiveEndTime(Date accountActiveEndTime) {
        this.accountActiveEndTime = accountActiveEndTime;
    }

    /**
     * passwordFailureCount 's getter.
     *
     * @return passwordFailureCount
     * @since Staveware Core Ver.5.3
     */
    public Short getPasswordFailureCount() {
        return passwordFailureCount;
    }

    /**
     * passwordFailureCount 's setter.
     *
     * @param passwordFailureCount
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordFailureCount(Short passwordFailureCount) {
        this.passwordFailureCount = passwordFailureCount;
    }

    /**
     * passwordFailureResetTime 's getter.
     *
     * @return passwordFailureResetTime
     * @since Staveware Core Ver.5.3
     */
    public Date getPasswordFailureResetTime() {
        return passwordFailureResetTime;
    }

    /**
     * passwordFailureResetTime 's setter.
     *
     * @param passwordFailureResetTime
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordFailureResetTime(Date passwordFailureResetTime) {
        this.passwordFailureResetTime = passwordFailureResetTime;
    }

    /**
     * lockoutEndTime 's getter.
     *
     * @return lockoutEndTime
     * @since Staveware Core Ver.5.3
     */
    public Date getLockoutEndTime() {
        return lockoutEndTime;
    }

    /**
     * lockoutEndTime 's setter.
     *
     * @param lockoutEndTime
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLockoutEndTime(Date lockoutEndTime) {
        this.lockoutEndTime = lockoutEndTime;
    }

    /**
     * lastLoginTime 's getter.
     *
     * @return lastLoginTime
     * @since Staveware Core Ver.5.3
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * lastLoginTime 's setter.
     *
     * @param lastLoginTime
     *        login control information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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