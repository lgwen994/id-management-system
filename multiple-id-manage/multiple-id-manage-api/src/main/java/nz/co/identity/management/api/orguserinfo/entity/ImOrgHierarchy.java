
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * orghierarchy information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImOrgHierarchy {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_ORG_HIERARCHY_SERIAL_TABLE";
    /**
     * hierarchy id
     *
     * @since Staveware Core Ver.5.3
     */
    private String hierarchyId;

    /**
     * hierarchy code
     *
     * @since Staveware Core Ver.5.3
     */
    private String hierarchyCode;

    /**
     * org id
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgId;

    /**
     * high org id
     *
     * @since Staveware Core Ver.5.3
     */
    private String highOrgId;

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
     * hierarchyId 's getter.
     *
     * @return hierarchyId
     * @since Staveware Core Ver.5.3
     */
    public String getHierarchyId() {
        return hierarchyId;
    }

    /**
     * hierarchyId 's setter.
     *
     * @param hierarchyId
     *        hierarchy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setHierarchyId(String hierarchyId) {
        if (hierarchyId != null) {
            this.hierarchyId = hierarchyId.trim();
        } else {
            this.hierarchyId = null;
        }
    }

    /**
     * hierarchyCode 's getter.
     *
     * @return hierarchyCode
     * @since Staveware Core Ver.5.3
     */
    public String getHierarchyCode() {
        return hierarchyCode;
    }

    /**
     * hierarchyCode 's setter.
     *
     * @param hierarchyCode
     *        hierarchy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setHierarchyCode(String hierarchyCode) {
        if (hierarchyCode != null) {
            this.hierarchyCode = hierarchyCode.trim();
        } else {
            this.hierarchyCode = null;
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
     *        hierarchy information entity
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
     * highOrgId 's getter.
     *
     * @return highOrgId
     * @since Staveware Core Ver.5.3
     */
    public String getHighOrgId() {
        return highOrgId;
    }

    /**
     * highOrgId 's setter.
     *
     * @param highOrgId
     *        hierarchy information entity
     * @since Staveware Core Ver.5.3
     */
    public void setHighOrgId(String highOrgId) {
        if (highOrgId != null) {
            this.highOrgId = highOrgId.trim();
        } else {
            this.highOrgId = null;
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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
     *        hierarchy information entity
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