package nz.co.identity.management.api.logininfo.entity;
import java.util.Date;
import java.util.Map;

/**
 * loginpolicy information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImLoginPolicy {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_LOGIN_POLICY_SERIAL_TABLE";
    /**
     * login policy id
     *
     * @since Staveware Core Ver.5.3
     */
    private String loginPolicyId;

    /**
     * login policy code
     *
     * @since Staveware Core Ver.5.3
     */
    private String loginPolicyCode;

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
     * password expire term
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer passwordExpireTerm;

    /**
     * password max failure
     *
     * @since Staveware Core Ver.5.3
     */
    private Short passwordMaxFailure;

    /**
     * password failure reset term
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer passwordFailureResetTerm;

    /**
     * lockout term
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer lockoutTerm;

    /**
     * max session limit
     *
     * @since Staveware Core Ver.5.3
     */
    private Long maxSessionLimit;

    /**
     * permit time
     *
     * @since Staveware Core Ver.5.3
     */
    private String permitTime;

    /**
     * permit domain
     *
     * @since Staveware Core Ver.5.3
     */
    private String permitDomain;

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
     * loginPolicyId 's getter.
     *
     * @return loginPolicyId
     * @since Staveware Core Ver.5.3
     */
    public String getLoginPolicyId() {
        return loginPolicyId;
    }

    /**
     * loginPolicyId 's setter.
     *
     * @param loginPolicyId
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLoginPolicyId(String loginPolicyId) {
        if (loginPolicyId != null) {
            this.loginPolicyId = loginPolicyId.trim();
        } else {
            this.loginPolicyId = null;
        }
    }

    /**
     * loginPolicyCode 's getter.
     *
     * @return loginPolicyCode
     * @since Staveware Core Ver.5.3
     */
    public String getLoginPolicyCode() {
        return loginPolicyCode;
    }

    /**
     * loginPolicyCode 's setter.
     *
     * @param loginPolicyCode
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLoginPolicyCode(String loginPolicyCode) {
        if (loginPolicyCode != null) {
            this.loginPolicyCode = loginPolicyCode.trim();
        } else {
            this.loginPolicyCode = null;
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
     *        login policy information entity
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
     *        login policy information entity
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
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * passwordExpireTerm 's getter.
     *
     * @return passwordExpireTerm
     * @since Staveware Core Ver.5.3
     */
    public Integer getPasswordExpireTerm() {
        return passwordExpireTerm;
    }

    /**
     * passwordExpireTerm 's setter.
     *
     * @param passwordExpireTerm
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordExpireTerm(Integer passwordExpireTerm) {
        this.passwordExpireTerm = passwordExpireTerm;
    }

    /**
     * passwordMaxFailure 's getter.
     *
     * @return passwordMaxFailure
     * @since Staveware Core Ver.5.3
     */
    public Short getPasswordMaxFailure() {
        return passwordMaxFailure;
    }

    /**
     * passwordMaxFailure 's setter.
     *
     * @param passwordMaxFailure
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordMaxFailure(Short passwordMaxFailure) {
        this.passwordMaxFailure = passwordMaxFailure;
    }

    /**
     * passwordFailureResetTerm 's getter.
     *
     * @return passwordFailureResetTerm
     * @since Staveware Core Ver.5.3
     */
    public Integer getPasswordFailureResetTerm() {
        return passwordFailureResetTerm;
    }

    /**
     * passwordFailureResetTerm 's setter.
     *
     * @param passwordFailureResetTerm
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordFailureResetTerm(Integer passwordFailureResetTerm) {
        this.passwordFailureResetTerm = passwordFailureResetTerm;
    }

    /**
     * lockoutTerm 's getter.
     *
     * @return lockoutTerm
     * @since Staveware Core Ver.5.3
     */
    public Integer getLockoutTerm() {
        return lockoutTerm;
    }

    /**
     * lockoutTerm 's setter.
     *
     * @param lockoutTerm
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLockoutTerm(Integer lockoutTerm) {
        this.lockoutTerm = lockoutTerm;
    }

    /**
     * maxSessionLimit 's getter.
     *
     * @return maxSessionLimit
     * @since Staveware Core Ver.5.3
     */
    public Long getMaxSessionLimit() {
        return maxSessionLimit;
    }

    /**
     * maxSessionLimit 's setter.
     *
     * @param maxSessionLimit
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setMaxSessionLimit(Long maxSessionLimit) {
        this.maxSessionLimit = maxSessionLimit;
    }

    /**
     * permitTime 's getter.
     *
     * @return permitTime
     * @since Staveware Core Ver.5.3
     */
    public String getPermitTime() {
        return permitTime;
    }

    /**
     * permitTime 's setter.
     *
     * @param permitTime
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPermitTime(String permitTime) {
        if (permitTime != null) {
            this.permitTime = permitTime.trim();
        } else {
            this.permitTime = null;
        }
    }

    /**
     * permitDomain 's getter.
     *
     * @return permitDomain
     * @since Staveware Core Ver.5.3
     */
    public String getPermitDomain() {
        return permitDomain;
    }

    /**
     * permitDomain 's setter.
     *
     * @param permitDomain
     *        login policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPermitDomain(String permitDomain) {
        if (permitDomain != null) {
            this.permitDomain = permitDomain.trim();
        } else {
            this.permitDomain = null;
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
     *        login policy information entity
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
     *        login policy information entity
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
     *        login policy information entity
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
     *        login policy information entity
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
     *        login policy information entity
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
     *        login policy information entity
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