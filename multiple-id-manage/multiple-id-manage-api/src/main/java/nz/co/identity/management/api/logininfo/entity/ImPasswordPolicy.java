package nz.co.identity.management.api.logininfo.entity;
import java.util.Date;
import java.util.Map;

/**
 * passwordpolicy information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImPasswordPolicy {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_PASSWORD_POLICY_SERIAL_TABLE";

    /**
     * password policy id
     *
     * @since Staveware Core Ver.5.3
     */
    private String passwordPolicyId;

    /**
     * password policy code
     *
     * @since Staveware Core Ver.5.3
     */
    private String passwordPolicyCode;

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
     * password min length
     *
     * @since Staveware Core Ver.5.3
     */
    private Short passwordMinLength;

    /**
     * password letter type
     *
     * @since Staveware Core Ver.5.3
     */
    private String passwordLetterType;

    /**
     * password min letter type
     *
     * @since Staveware Core Ver.5.3
     */
    private Short passwordMinLetterType;

    /**
     * password in history
     *
     * @since Staveware Core Ver.5.3
     */
    private Short passwordInHistory;

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
     * passwordPolicyId 's getter.
     *
     * @return passwordPolicyId
     * @since Staveware Core Ver.5.3
     */
    public String getPasswordPolicyId() {
        return passwordPolicyId;
    }

    /**
     * passwordPolicyId 's setter.
     *
     * @param passwordPolicyId
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordPolicyId(String passwordPolicyId) {
        if (passwordPolicyId != null) {
            this.passwordPolicyId = passwordPolicyId.trim();
        } else {
            this.passwordPolicyId = null;
        }
    }

    /**
     * passwordPolicyCode 's getter.
     *
     * @return passwordPolicyCode
     * @since Staveware Core Ver.5.3
     */
    public String getPasswordPolicyCode() {
        return passwordPolicyCode;
    }

    /**
     * passwordPolicyCode 's setter.
     *
     * @param passwordPolicyCode
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordPolicyCode(String passwordPolicyCode) {
        if (passwordPolicyCode != null) {
            this.passwordPolicyCode = passwordPolicyCode.trim();
        } else {
            this.passwordPolicyCode = null;
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
     *        password policy information entity
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
     *        password policy information entity
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
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * passwordMinLength 's getter.
     *
     * @return passwordMinLength
     * @since Staveware Core Ver.5.3
     */
    public Short getPasswordMinLength() {
        return passwordMinLength;
    }

    /**
     * passwordMinLength 's setter.
     *
     * @param passwordMinLength
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordMinLength(Short passwordMinLength) {
        this.passwordMinLength = passwordMinLength;
    }

    /**
     * passwordLetterType 's getter.
     *
     * @return passwordLetterType
     * @since Staveware Core Ver.5.3
     */
    public String getPasswordLetterType() {
        return passwordLetterType;
    }

    /**
     * passwordLetterType 's setter.
     *
     * @param passwordLetterType
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordLetterType(String passwordLetterType) {
        if (passwordLetterType != null) {
            this.passwordLetterType = passwordLetterType.trim();
        } else {
            this.passwordLetterType = null;
        }
    }

    /**
     * passwordMinLetterType 's getter.
     *
     * @return passwordMinLetterType
     * @since Staveware Core Ver.5.3
     */
    public Short getPasswordMinLetterType() {
        return passwordMinLetterType;
    }

    /**
     * passwordMinLetterType 's setter.
     *
     * @param passwordMinLetterType
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordMinLetterType(Short passwordMinLetterType) {
        this.passwordMinLetterType = passwordMinLetterType;
    }

    /**
     * passwordInHistory 's getter.
     *
     * @return passwordInHistory
     * @since Staveware Core Ver.5.3
     */
    public Short getPasswordInHistory() {
        return passwordInHistory;
    }

    /**
     * passwordInHistory 's setter.
     *
     * @param passwordInHistory
     *        password policy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordInHistory(Short passwordInHistory) {
        this.passwordInHistory = passwordInHistory;
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
     *        password policy information entity
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
     *        password policy information entity
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
     *        password policy information entity
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
     *        password policy information entity
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
     *        password policy information entity
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
     *        password policy information entity
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