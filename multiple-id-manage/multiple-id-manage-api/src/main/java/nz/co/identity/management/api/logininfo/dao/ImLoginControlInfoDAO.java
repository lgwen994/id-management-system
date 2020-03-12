/**
 * @(#)StvIdmfLoginControlInfoDAO.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：according to the role form of CRUD operations.
 * クラス名：StvIdmfLoginControlInfoDAO
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
package nz.co.identity.management.api.logininfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;

/**
 * according to the logincontrolinfo form of CRUD operations, return a valid
 * data.<br>
 * logincontrolinfo's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImLoginControlInfoDAO extends BaseDAO {

    /**
     * delete the logincontrolinfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteLoginControlInfo(ImLoginControlInfo loginControlInfo);

    /**
     * delete force the logincontrolinfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceLoginControlInfo(
            ImLoginControlInfo loginControlInfo);

    /**
     * get LoginControlInfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return result of logincontrolinfo
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo getLoginControlInfo(
            ImLoginControlInfo loginControlInfo);

    /**
     * update the LoginControlInfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return result of logincontrolinfo
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo updateLoginControlInfo(
            ImLoginControlInfo loginControlInfo);

    /**
     * search the logincontrolinfo by action properties
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of logincontrolinfo list
     * @since Staveware Core Ver.5.3
     */
    Page<ImLoginControlInfo> searchLoginControlInfo(
            ImLoginControlInfo loginControlInfo, Integer pageNum,
            Integer pageSize, List<String> sort);

    /**
     * register the logincontrolinfo
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return result of logincontrolinfo
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo registerLoginControlInfo(
            ImLoginControlInfo loginControlInfo);
}
