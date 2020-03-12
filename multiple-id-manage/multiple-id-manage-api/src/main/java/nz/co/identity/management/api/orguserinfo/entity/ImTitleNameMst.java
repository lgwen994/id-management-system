
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * titlename information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImTitleNameMst {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_TITLE_NAME_MST_SERIAL_TABLE";

    /**
     * title name id
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleNameId;

    /**
     * title id
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleId;

    /**
     * locale
     *
     * @since Staveware Core Ver.5.3
     */
    private String locale;

    /**
     * title name
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleName;

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
     * TitleNameId's getter.
     *
     * @return TitleNameId
     * @since Staveware Core Ver.5.3
     */
    public String getTitleNameId() {
        return titleNameId;
    }

    /**
     * TitleNameId's setter.
     *
     * @param titleNameId
     *        titleName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setTitleNameId(String titleNameId) {
        if (titleNameId != null) {
            this.titleNameId = titleNameId.trim();
        } else {
            this.titleNameId = null;
        }
    }

    /**
     * TitleId's getter.
     *
     * @return titleId
     * @since Staveware Core Ver.5.3
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * TitleId's setter.
     *
     * @param titleId
     *        titleName information entity
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
     * Locale's getter.
     *
     * @return locale
     * @since Staveware Core Ver.5.3
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Locale's setter.
     *
     * @param locale
     *        titleName information entity
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
     * TitleName's getter.
     *
     * @return titleName
     * @since Staveware Core Ver.5.3
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * TitleName's setter.
     *
     * @param titleName
     *        titleName information entity
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
     * ActiveStartTime's getter.
     *
     * @return activeStartTime
     * @since Staveware Core Ver.5.3
     */
    public Date getActiveStartTime() {
        return activeStartTime;
    }

    /**
     * ActiveStartTime's setter.
     *
     * @param activeStartTime
     *        titleName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveStartTime(Date activeStartTime) {
        this.activeStartTime = activeStartTime;
    }

    /**
     * ActiveEndTime's getter.
     *
     * @return activeEndTime
     * @since Staveware Core Ver.5.3
     */
    public Date getActiveEndTime() {
        return activeEndTime;
    }

    /**
     * ActiveEndTime's setter.
     *
     * @param activeEndTime
     *        titleName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * CreatedTime's getter.
     *
     * @return createdTime
     * @since Staveware Core Ver.5.3
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * CreatedTime's setter.
     *
     * @param createdTime
     *        titleName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * CreatedUser's getter.
     *
     * @return createdUser
     * @since Staveware Core Ver.5.3
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * CreatedUser's setter.
     *
     * @param createdUser
     *        titleName information entity
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
     * UpdatedTime's getter.
     *
     * @return updatedTime
     * @since Staveware Core Ver.5.3
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * UpdatedTime's setter.
     *
     * @param updatedTime
     *        titleName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * UpdatedUser's getter.
     *
     * @return UpdatedUser
     * @since Staveware Core Ver.5.3
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * UpdatedUser's setter.
     *
     * @param updatedUser
     *        titleName information entity
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
     * DeletedFlg's getter.
     *
     * @return DeletedFlg
     * @since Staveware Core Ver.5.3
     */
    public Short getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * DeletedFlg's setter.
     *
     * @param deletedFlg
     *        titleName information entity
     * @since Staveware Core Ver.5.3
     */
    public void setDeletedFlg(Short deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * VersionNo's getter.
     *
     * @return versionNo
     * @since Staveware Core Ver.5.3
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * VersionNo's setter.
     *
     * @param versionNo
     *        titleName information entity
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