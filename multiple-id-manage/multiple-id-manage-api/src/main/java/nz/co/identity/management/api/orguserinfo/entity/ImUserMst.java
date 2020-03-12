
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * user information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImUserMst {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_USER_MST_SERIAL_TABLE";

    /**
     * user id
     *
     * @since Staveware Core Ver.5.3
     */
    private String userId;

    /**
     * user code
     *
     * @since Staveware Core Ver.5.3
     */
    private String userCode;

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
     * user name
     *
     * @since Staveware Core Ver.5.3
     */
    private String userName;

    /**
     * mail
     *
     * @since Staveware Core Ver.5.3
     */
    private String mail;

    /**
     * tel
     *
     * @since Staveware Core Ver.5.3
     */
    private String tel;

    /**
     * fax
     *
     * @since Staveware Core Ver.5.3
     */
    private String fax;

    /**
     * locale
     *
     * @since Staveware Core Ver.5.3
     */
    private String locale;

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
     *        user information entity
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
     * userCode 's getter.
     *
     * @return userCode
     * @since Staveware Core Ver.5.3
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * userCode 's setter.
     *
     * @param userCode
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUserCode(String userCode) {
        if (userCode != null) {
            this.userCode = userCode.trim();
        } else {
            this.userCode = null;
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
     *        user information entity
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
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * userName 's getter.
     *
     * @return userName
     * @since Staveware Core Ver.5.3
     */
    public String getUserName() {
        return userName;
    }

    /**
     * userName 's setter.
     *
     * @param userName
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUserName(String userName) {
        if (userName != null) {
            this.userName = userName.trim();
        } else {
            this.userName = null;
        }
    }

    /**
     * mail 's getter.
     *
     * @return mail
     * @since Staveware Core Ver.5.3
     */
    public String getMail() {
        return mail;
    }

    /**
     * mail 's setter.
     *
     * @param mail
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setMail(String mail) {
        if (mail != null) {
            this.mail = mail.trim();
        } else {
            this.mail = null;
        }
    }

    /**
     * tel 's getter.
     *
     * @return tel
     * @since Staveware Core Ver.5.3
     */
    public String getTel() {
        return tel;
    }

    /**
     * tel 's setter.
     *
     * @param tel
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setTel(String tel) {
        if (tel != null) {
            this.tel = tel.trim();
        } else {
            this.tel = null;
        }
    }

    /**
     * fax 's getter.
     *
     * @return fax
     * @since Staveware Core Ver.5.3
     */
    public String getFax() {
        return fax;
    }

    /**
     * fax 's setter.
     *
     * @param fax
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setFax(String fax) {
        if (fax != null) {
            this.fax = fax.trim();
        } else {
            this.fax = null;
        }
    }

    /**
     * locale 's getter.
     *
     * @return locale
     * @since Staveware Core Ver.5.3
     */
    public String getLocale() {
        return locale;
    }

    /**
     * locale 's setter.
     *
     * @param locale
     *        user information entity
     * @since Staveware Core Ver.5.3
     */
    public void setLocale(String locale) {
        if (locale != null) {
            this.locale = locale.trim();
        } else {
            this.locale = null;
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
     *        user information entity
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
     *        user information entity
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
     *        user information entity
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
     *        user information entity
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
     *        user information entity
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
     *        user information entity
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