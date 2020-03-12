package nz.co.identity.management.api.roleinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * roleName information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImRoleNameMst {
    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_ROLE_NAME_MST_SERIAL_TABLE";

    /**
     * roleNameId
     *
     * @since Staveware Core Ver.5.3
     */
    private String roleNameId;

    /**
     * roleId
     *
     * @since Staveware Core Ver.5.3
     */
    private String roleId;

    /**
     * locale
     *
     * @since Staveware Core Ver.5.3
     */
    private String locale;

    /**
     * roleName
     *
     * @since Staveware Core Ver.5.3
     */
    private String roleName;

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
     * RoleNameId's getter.
     *
     * @return RoleNameId
     * @since Staveware Core Ver.5.3
     */
    public String getRoleNameId() {
        return roleNameId;
    }

    /**
     * RoleNameId's setter.
     *
     * @param roleNameId
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setRoleNameId(String roleNameId) {
        if (roleNameId != null) {
            this.roleNameId = roleNameId.trim();
        } else {
            this.roleNameId = null;
        }
    }

    /**
     * RoleId's getter.
     *
     * @return RoleId
     * @since Staveware Core Ver.5.3
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * RoleId's setter.
     *
     * @param roleId
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setRoleId(String roleId) {
        if (roleId != null) {
            this.roleId = roleId.trim();
        } else {
            this.roleId = null;
        }
    }

    /**
     * Locale's getter.
     *
     * @return Locale
     * @since Staveware Core Ver.5.3
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Locale's setter.
     *
     * @param locale
     *        organizational information entity
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
     * RoleName's getter.
     *
     * @return RoleName
     * @since Staveware Core Ver.5.3
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * RoleName's setter.
     *
     * @param roleName
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setRoleName(String roleName) {
        if (roleName != null) {
            this.roleName = roleName.trim();
        } else {
            this.roleName = null;
        }
    }

    /**
     * ActiveStartTime's getter.
     *
     * @return ActiveStartTime
     * @since Staveware Core Ver.5.3
     */
    public Date getActiveStartTime() {
        return activeStartTime;
    }

    /**
     * ActiveStartTime's setter.
     *
     * @param activeStartTime
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveStartTime(Date activeStartTime) {
        this.activeStartTime = activeStartTime;
    }

    /**
     * ActiveEndTime's getter.
     *
     * @return ActiveEndTime
     * @since Staveware Core Ver.5.3
     */
    public Date getActiveEndTime() {
        return activeEndTime;
    }

    /**
     * ActiveEndTime's setter.
     *
     * @param activeEndTime
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    /**
     * CreatedTime's getter.
     *
     * @return CreatedTime
     * @since Staveware Core Ver.5.3
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * CreatedTime's setter.
     *
     * @param createdTime
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * CreatedUser's getter.
     *
     * @return CreatedUser
     * @since Staveware Core Ver.5.3
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * CreatedUser's setter.
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
     * UpdatedTime's getter.
     *
     * @return UpdatedTime
     * @since Staveware Core Ver.5.3
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * UpdatedTime's setter.
     *
     * @param updatedTime
     *        organizational information entity
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
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setDeletedFlg(Short deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * VersionNo's getter.
     *
     * @return VersionNo
     * @since Staveware Core Ver.5.3
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * VersionNo's setter.
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