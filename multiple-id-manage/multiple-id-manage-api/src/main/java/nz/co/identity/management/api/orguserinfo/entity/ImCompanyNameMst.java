
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * companyname information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImCompanyNameMst {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_COMPANY_NAME_MST_SERIAL_TABLE";

    /**
     * company name id
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyNameId;

    /**
     * company id
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyId;

    /**
     * locale
     *
     * @since Staveware Core Ver.5.3
     */
    private String locale;

    /**
     * company name
     *
     * @since Staveware Core Ver.5.3
     */
    private String companyName;

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
     * companyNameId 's getter.
     *
     * @return companyNameId
     * @since Staveware Core Ver.5.3
     */
    public String getCompanyNameId() {
        return companyNameId;
    }

    /**
     * companyNameId 's setter.
     *
     * @param companyNameId
     *        companyName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCompanyNameId(String companyNameId) {
        if (companyNameId != null) {
            this.companyNameId = companyNameId.trim();
        } else {
            this.companyNameId = null;
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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
     *        companyName information entity
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