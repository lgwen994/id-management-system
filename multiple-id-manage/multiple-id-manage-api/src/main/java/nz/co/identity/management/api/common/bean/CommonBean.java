/**
 * @(#)CommonBean.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：CommonBean is all tables common field information Bean class of the database.
 * クラス名：CommonBean
 *
 *   ver     変更日         所属       担当者        変更内容
 * ──────────────────────────────────
 *  V1.0   2017/10/08      Neusoft    初版
 *
 *┌─────────────────────────────────┐
 *│  本技術情報には当社の機密情報が含まれておりますので、当社の      │
 *│  書面による承諾がなく第三者に開示することはできません。          │
 *│  また、当社の承諾を得た場合であっても、本技術情報は外国為替      │
 *│  及び外国貿易管理法に定める特定技術に該当するため、非居住者に    │
 *│  提供する場合には、同法に基づく許可を要することがあります。      │
 *│                      東芝デジタルソリューションズ  株式会社      │
 *└─────────────────────────────────┘
 */
package nz.co.identity.management.api.common.bean;

import java.io.Serializable;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <code>CommonBean</code>is all tables common field information Bean class of
 * the database.
 *
 * @since Staveware Core Ver.5.3
 */
public abstract class CommonBean implements Serializable {

    /**
     * serialVersionUID
     *
     * @since Staveware Core Ver.5.3
     */
    private static final long serialVersionUID = -2486209336908690469L;

    /**
     * created time
     *
     * @since Staveware Core Ver.5.3
     */
    @JsonIgnore
    private Time createdTime;

    /**
     * created user
     *
     * @since Staveware Core Ver.5.3
     */
    @JsonIgnore
    private String createdUser;

    /**
     * update time
     *
     * @since Staveware Core Ver.5.3
     */
    @JsonIgnore
    private Time updateTime;

    /**
     * update user.
     *
     * @since Staveware Core Ver.5.3
     */
    @JsonIgnore
    private String updateUser;

    /**
     * created time's getter.
     *
     * @return Time created time
     * @since Staveware Core Ver.5.3
     */
    public Time getCreatedTime() {
        return createdTime;
    }

    /**
     * created time's setter.
     *
     * @param createdTime
     *        created time
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedTime(Time createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * create user's getter.
     *
     * @return String create user
     * @since Staveware Core Ver.5.3
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * create user's setter.
     *
     * @param createdUser
     *        create user
     * @since Staveware Core Ver.5.3
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * update time's getter.
     *
     * @return Time update time
     * @since Staveware Core Ver.5.3
     */
    public Time getUpdateTime() {
        return updateTime;
    }

    /**
     * update time's setter.
     *
     * @param updateTime
     *        update time
     * @since Staveware Core Ver.5.3
     */
    public void setUpdateTime(Time updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * update user's getter.
     *
     * @return String update user
     * @since Staveware Core Ver.5.3
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * update user's setter.
     *
     * @param updateUser
     *        update user
     * @since Staveware Core Ver.5.3
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
