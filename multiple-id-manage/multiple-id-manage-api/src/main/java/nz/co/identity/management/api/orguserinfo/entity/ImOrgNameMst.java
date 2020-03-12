
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * orgname information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImOrgNameMst {
    
    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_ORG_NAME_MST_SERIAL_TABLE";

    /**
     * orgNameId
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgNameId;

    /**
     * orgId
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgId;

    /**
     * locale
     *
     * @since Staveware Core Ver.5.3
     */
    private String locale;

    /**
     * orgName
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgName;

    /**
     * activeStartTime
     *
     * @since Staveware Core Ver.5.3
     */
    private Date activeStartTime;

    /**
     * activeEndTime
     *
     * @since Staveware Core Ver.5.3
     */
    private Date activeEndTime;

    /**
     * createdTime
     *
     * @since Staveware Core Ver.5.3
     */
    private Date createdTime;

    /**
     * createdUser
     *
     * @since Staveware Core Ver.5.3
     */
    private String createdUser;

    /**
     * updatedTime
     *
     * @since Staveware Core Ver.5.3
     */
    private Date updatedTime;

    /**
     * updatedUser
     *
     * @since Staveware Core Ver.5.3
     */
    private String updatedUser;

    /**
     * deletedFlg
     *
     * @since Staveware Core Ver.5.3
     */
    private Short deletedFlg;

    /**
     * versionNo
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
     * orgNameId 's getter.
     *
     * @return orgNameId
     * @since Staveware Core Ver.5.3
     */
    public String getOrgNameId() {
        return orgNameId;
    }

    /**
     * orgNameId 's setter.
     *
     * @param orgNameId
     *        orgName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setOrgNameId(String orgNameId) {
        if (orgNameId != null) {
            this.orgNameId = orgNameId.trim();
        } else {
            this.orgNameId = null;
        }
    }

    /**
     * orgId 's getter.
     *
     * @return orgId
     * @since Staveware Core Ver.5.3
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * orgId 's setter.
     *
     * @param orgId
     *        orgName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setOrgId(String orgId) {
        if (orgId != null) {
            this.orgId = orgId.trim();
        } else {
            this.orgId = null;
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
     *        orgName information entity
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
     * orgName 's getter.
     *
     * @return orgName
     * @since Staveware Core Ver.5.3
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * orgName 's setter.
     *
     * @param orgName
     *        orgName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setOrgName(String orgName) {
        if (orgName != null) {
            this.orgName = orgName.trim();
        } else {
            this.orgName = null;
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
     *        orgName information entity
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
     *        orgName information entity
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
     *        orgName information entity
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
     *        orgName information entity
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
     *        orgName information entity
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
     *        orgName information entity
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
     *        orgName information entity
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
     *        orgName information entity
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