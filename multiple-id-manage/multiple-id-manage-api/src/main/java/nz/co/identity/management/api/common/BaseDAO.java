/**
 * @(#)BaseDAO.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the base DAO.
 * クラス名：BaseDAO
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
package nz.co.identity.management.api.common;

import nz.co.identity.management.api.common.exception.ImOptimisticLockingFailureException;

/**
 * the base DAO.
 *
 * @since Staveware Core Ver.5.3
 *
 */
public interface BaseDAO {

    /**
     * status with history
     *
     * @since Staveware Core Ver.5.3
     */
    String HISTORY_TYPE_ON = "on";

    /**
     * status without history
     *
     * @since Staveware Core Ver.5.3
     */
    String HISTORY_TYPE_OFF = "off";

    /**
     * get the history type
     *
     * @return BaseDAO.HISTORY_TYPE_ON means status with history, "off" means
     *         status without history
     *
     * @since Staveware Core Ver.5.3
     */
    String getHistoryType();

    /**
     * check version number
     *
     * @param versionNo
     *        version number
     * @param inputVersionNo
     *        input version number
     * @since Staveware Core Ver.5.3
     */
    default void checkVersionNo(Integer versionNo, Integer inputVersionNo) {
        if (inputVersionNo != null && !versionNo.equals(inputVersionNo)) {
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }
    }
}
