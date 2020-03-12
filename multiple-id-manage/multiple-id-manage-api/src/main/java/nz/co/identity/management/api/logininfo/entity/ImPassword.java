package nz.co.identity.management.api.logininfo.entity;
import java.util.Date;
import java.util.Map;

/**
 * password information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImPassword {
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
     * password
     *
     * @since Staveware Core Ver.5.3
     */
    private String password;

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
     * password must change flg
     *
     * @since Staveware Core Ver.5.3
     */
    private Short passwordMustChangeFlg;

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
     *        password key information entity
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
     *        password key information entity
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
     * password 's getter.
     *
     * @return password
     * @since Staveware Core Ver.5.3
     */
    public String getPassword() {
        return password;
    }

    /**
     * password 's setter.
     *
     * @param password
     *        password information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPassword(String password) {
        if (password != null) {
            this.password = password.trim();
        } else {
            this.password = null;
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
     *        password information entity
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
     *        password information entity
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
     * passwordMustChangeFlg 's getter.
     *
     * @return passwordMustChangeFlg
     * @since Staveware Core Ver.5.3
     */
    public Short getPasswordMustChangeFlg() {
        return passwordMustChangeFlg;
    }

    /**
     * passwordMustChangeFlg 's setter.
     *
     * @param passwordMustChangeFlg
     *        password information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPasswordMustChangeFlg(Short passwordMustChangeFlg) {
        this.passwordMustChangeFlg = passwordMustChangeFlg;
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