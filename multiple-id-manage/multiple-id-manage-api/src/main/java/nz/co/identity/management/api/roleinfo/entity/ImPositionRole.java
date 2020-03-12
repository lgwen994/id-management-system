package nz.co.identity.management.api.roleinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * PositionRole information Entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImPositionRole {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_POSITION_ROLE_SERIAL_TABLE";

    /**
     * position role id
     *
     * @since Staveware Core Ver.5.3
     */
    private String positionRoleId;

    /**
     * position role code
     *
     * @since Staveware Core Ver.5.3
     */
    private String positionRoleCode;

    /**
     * position id
     *
     * @since Staveware Core Ver.5.3
     */
    private String positionId;

    /**
     * role id
     *
     * @since Staveware Core Ver.5.3
     */
    private String roleId;

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
     * PositionRoleId's getter.
     *
     * @return PositionRoleId
     * @since Staveware Core Ver.5.3
     */
    public String getPositionRoleId() {
        return positionRoleId;
    }

    /**
     * PositionRoleId's setter.
     *
     * @param positionRoleId
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPositionRoleId(String positionRoleId) {
        if (positionRoleId != null) {
            this.positionRoleId = positionRoleId.trim();
        } else {
            this.positionRoleId = null;
        }
    }

    /**
     * PositionRoleCode's getter.
     *
     * @return PositionRoleCode
     * @since Staveware Core Ver.5.3
     */
    public String getPositionRoleCode() {
        return positionRoleCode;
    }

    /**
     * PositionRoleCode's setter.
     *
     * @param positionRoleCode
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPositionRoleCode(String positionRoleCode) {
        if (positionRoleCode != null) {
            this.positionRoleCode = positionRoleCode.trim();
        } else {
            this.positionRoleCode = null;
        }
    }

    /**
     * PositionId's getter.
     *
     * @return PositionId
     * @since Staveware Core Ver.5.3
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * PositionId's setter.
     *
     * @param positionId
     *        organizational information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPositionId(String positionId) {
        if (positionId != null) {
            this.positionId = positionId.trim();
        } else {
            this.positionId = null;
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