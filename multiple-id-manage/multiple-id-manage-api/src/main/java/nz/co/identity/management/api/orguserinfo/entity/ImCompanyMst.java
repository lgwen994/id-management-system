
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * company information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImCompanyMst {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_COMPANY_MST_SERIAL_TABLE";
    /**
     * company id
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyId;

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
     * company name
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyName;

    /**
     * company type
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyType;

    /**
     * company comment
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyComment;

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
     *        company information entity
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
     *        company information entity
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
     *        company information entity
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
     *        company information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * companyName 's getter.
     *
     * @return companyName
     * @since Staveware Core Ver.5.3
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * companyName 's setter.
     *
     * @param companyName
     *        company information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCompanyName(String companyName) {
        if (companyName != null) {
            this.companyName = companyName.trim();
        } else {
            this.companyName = null;
        }
    }

    /**
     * companyType 's getter.
     *
     * @return companyType
     * @since Staveware Core Ver.5.3
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * companyType 's setter.
     *
     * @param companyType
     *        company information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCompanyType(String companyType) {
        if (companyType != null) {
            this.companyType = companyType.trim();
        } else {
            this.companyType = null;
        }
    }

    /**
     * companyComment 's getter.
     *
     * @return companyComment
     * @since Staveware Core Ver.5.3
     */
    public String getCompanyComment() {
        return companyComment;
    }

    /**
     * companyComment 's setter.
     *
     * @param companyComment
     *        company information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCompanyComment(String companyComment) {
        if (companyComment != null) {
            this.companyComment = companyComment.trim();
        } else {
            this.companyComment = null;
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
     *        company information entity
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
     *        company information entity
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
     *        company information entity
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
     *        company information entity
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
     *        company information entity
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
     *        company information entity
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