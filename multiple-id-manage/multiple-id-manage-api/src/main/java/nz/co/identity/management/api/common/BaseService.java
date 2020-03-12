/**
 * @(#)BaseService.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the base service.
 * クラス名：BaseService
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

import java.util.Arrays;
import java.util.List;

/**
 * the base service.
 * 
 * @param <T>
 *        the type.
 * @since Staveware Core Ver.5.3
 *
 */
public class BaseService<T extends BaseDAO> {

    /**
     * get the DAO from daoList
     * 
     * @param historyFlag
     *        the flag of history type
     * @param daoList
     *        the list of DAO
     * @return the DAO by historyFlag
     * @since Staveware Core Ver.5.3
     */
    protected T getDAO(String historyFlag, List<T> daoList) {
        if (!Arrays.asList(new String[] { "on", "off" })
                .contains(historyFlag)) {
            historyFlag = "off";
        }
        T dao = null;
        for (T item : daoList) {
            if (item.getHistoryType().equals(historyFlag)) {
                dao = item;
                break;
            }
        }
        return dao;
    }

}
