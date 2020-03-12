
package nz.co.identity.management.api.orguserinfo.entity;

import java.util.Date;
import java.util.Map;

/**
 * position information entity class.
 *
 * @since Staveware Core Ver.5.3
 */
public class ImPosition {

    /**
     * serial table name
     * 
     * @since Staveware Core Ver.5.3
     */
    public final static String SERIAL_TABLE = "IDMF_POSITION_SERIAL_TABLE";

    /**
     * position id
     *
     * @since Staveware Core Ver.5.3
     */
    private String positionId;

    /**
     * position code
     *
     * @since Staveware Core Ver.5.3
     */
    private String positionCode;

    /**
     * user id
     *
     * @since Staveware Core Ver.5.3
     */
    private String userId;

    /**
     * org id
     *
     * @since Staveware Core Ver.5.3
     */
    private String orgId;

    /**
     * title id
     *
     * @since Staveware Core Ver.5.3
     */
    private String titleId;

    /**
     * concurrent flg
     *
     * @since Staveware Core Ver.5.3
     */
    private Short concurrentFlg;

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
     * positionId 's getter.
     *
     * @return positionId
     * @since Staveware Core Ver.5.3
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * positionId 's setter.
     *
     * @param positionId
     *        position information entity
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
     * positionCode 's getter.
     *
     * @return positionCode
     * @since Staveware Core Ver.5.3
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * positionCode 's setter.
     *
     * @param positionCode
     *        position information entity
     * @since Staveware Core Ver.5.3
     */
    public void setPositionCode(String positionCode) {
        if (positionCode != null) {
            this.positionCode = positionCode.trim();
        } else {
            this.positionCode = null;
        }
    }

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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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
     * concurrentFlg 's getter.
     *
     * @return concurrentFlg
     * @since Staveware Core Ver.5.3
     */
    public Short getConcurrentFlg() {
        return concurrentFlg;
    }

    /**
     * concurrentFlg 's setter.
     *
     * @param concurrentFlg
     *        position information entity
     * @since Staveware Core Ver.5.3
     */
    public void setConcurrentFlg(Short concurrentFlg) {
        this.concurrentFlg = concurrentFlg;
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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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
     *        position information entity
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