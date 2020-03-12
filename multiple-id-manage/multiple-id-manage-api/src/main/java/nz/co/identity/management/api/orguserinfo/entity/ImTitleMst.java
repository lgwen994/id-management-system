
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * title information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImTitleMst {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_TITLE_MST_SERIAL_TABLE";
    /**
     * title id
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleId;

    /**
     * title code
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleCode;

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
     * title name
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleName;

    /**
     * title type
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleType;

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
     * titleId 's getter.
     *
     * @return titleId
     * @since Staveware Core Ver.5.3
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * titleId 's setter.
     *
     * @param titleId
     *        title information entity
     * @since Staveware Core Ver.5.3
     */
    public void setTitleId(String titleId) {
        if (titleId != null) {
            this.titleId = titleId.trim();
        } else {
            this.titleId = null;
        }
    }

    /**
     * titleCode 's getter.
     *
     * @return titleCode
     * @since Staveware Core Ver.5.3
     */
    public String getTitleCode() {
        return titleCode;
    }

    /**
     * titleCode 's setter.
     *
     * @param titleCode
     *        title information entity
     * @since Staveware Core Ver.5.3
     */
    public void setTitleCode(String titleCode) {
        if (titleCode != null) {
            this.titleCode = titleCode.trim();
        } else {
            this.titleCode = null;
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
     *        title information entity
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
     *        title information entity
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
     *        title information entity
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
     *        title information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * titleName 's getter.
     *
     * @return titleName
     * @since Staveware Core Ver.5.3
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * titleName 's setter.
     *
     * @param titleName
     *        title information entity
     * @since Staveware Core Ver.5.3
     */
    public void setTitleName(String titleName) {
        if (titleName != null) {
            this.titleName = titleName.trim();
        } else {
            this.titleName = null;
        }
    }

    /**
     * titleType 's getter.
     *
     * @return titleType
     * @since Staveware Core Ver.5.3
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * titleType 's setter.
     *
     * @param titleType
     *        title information entity
     * @since Staveware Core Ver.5.3
     */
    public void setTitleType(String titleType) {
        if (titleType != null) {
            this.titleType = titleType.trim();
        } else {
            this.titleType = null;
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
     *        title information entity
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
     *        title information entity
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
     *        title information entity
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
     *        title information entity
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
     *        title information entity
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
     *        title information entity
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