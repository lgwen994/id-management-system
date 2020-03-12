
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * organization information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImOrgMst {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_ORG_MST_SERIAL_TABLE";
    /**
     * org id
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgId;

    /**
     * org code
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgCode;

    /**
     * company id
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyId;

    /**
     * group flg
     *
     * @since Staveware Core Ver.5.3
     */
    private Short groupFlg;

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
     * org name
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgName;

    /**
     * org type
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgType;

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
     * the user of updating data
     *
     * @since Staveware Core Ver.5.3
     */
    private String updatedUser;

    /**
     * flag for deleting
     *
     * @since Staveware Core Ver.5.3
     */
    private Short deletedFlg;

    /**
     * version number
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
     * orgId's getter.
     *
     * @return orgId
     * @since Staveware Core Ver.5.3
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * orgId's setter.
     *
     * @param orgId
     *        organizational information entity
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
     * orgCode's getter.
     *
     * @return orgCode
     * @since Staveware Core Ver.5.3
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * orgCode 's setter.
     *
     * @param orgCode
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setOrgCode(String orgCode) {
        if (orgCode != null) {
            this.orgCode = orgCode.trim();
        } else {
            this.orgCode = null;
        }
    }

    /**
     * companyId 's getter.
     *
     * @return companyId
     * @since Staveware Core Ver.5.3
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * companyId 's setter.
     *
     * @param companyId
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCompanyId(String companyId) {
        if (companyId != null) {
            this.companyId = companyId.trim();
        } else {
            this.companyId = null;
        }
    }

    /**
     * groupFlg 's getter.
     *
     * @return groupFlg
     * @since Staveware Core Ver.5.3
     */
    public Short getGroupFlg() {
        return groupFlg;
    }

    /**
     * groupFlg 's setter.
     *
     * @param groupFlg
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setGroupFlg(Short groupFlg) {
        this.groupFlg = groupFlg;
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
     *        organizational information entity
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
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
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
     *        organizational information entity
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
     * orgType 's getter.
     *
     * @return orgType
     * @since Staveware Core Ver.5.3
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * orgType 's setter.
     *
     * @param orgType
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setOrgType(String orgType) {
        if (orgType != null) {
            this.orgType = orgType.trim();
        } else {
            this.orgType = null;
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
     *        organizational information entity
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
     *        organizational information entity
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
     *        organizational information entity
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
     *        organizational information entity
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
     *        organizational information entity
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
     *        organizational information entity
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