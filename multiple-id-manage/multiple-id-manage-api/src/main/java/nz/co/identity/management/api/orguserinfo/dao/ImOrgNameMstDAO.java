
package nz.co.identity.management.api.orguserinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;

/**
 * according to the orgName form of CRUD operations, return a valid data.<br>
 * OrgName's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImOrgNameMstDAO extends BaseDAO {
    /**
     * register the orgNameMst
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return result of orgNameMst
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst registerOrgName(ImOrgNameMst orgNameMst);

    /**
     * update the orgNameMst by orgNameMst id
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return result of orgNameMst
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst updateOrgName(ImOrgNameMst orgNameMst);

    /**
     * delete the orgNameMst by orgNameMst id
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteOrgName(ImOrgNameMst orgNameMst);

    /**
     * delete the orgNameMst by orgNameMst id
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceOrgName(ImOrgNameMst orgNameMst);

    /**
     * get orgNameMst by org id
     *
     * @param orgId
     *        org id
     * @return result of orgNameMst
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgNameMst> getOrgName(String orgId);

    /**
     * get orgNameMst by orgNameMst id
     *
     * @param orgNameId
     *        org name id
     * @return result of orgNameMst
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst getOrgNameById(String orgNameId);

    /**
     * get orgNameMst by org code
     *
     * @param orgCode
     *        orgMst code
     * @return result of orgNameMst
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgNameMst> getOrgNameByOrgCode(String orgCode);

    /**
     * search the orgNameMst by orgNameMst properties
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of orgNameMst list
     * @since Staveware Core Ver.5.3
     */
    Page<ImOrgNameMst> searchOrgName(ImOrgNameMst orgNameMst,
            Integer pageNum, Integer pageSize, List<String> sort);
}
