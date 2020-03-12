package nz.co.identity.management.api.orguserinfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;

/**
 * according to the orgName form of CRUD operations, return a valid data.<br>
 * orgName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImOrgNameMstService {

    /**
     * register the orgName
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return result of orgName
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst registerOrgName(ImOrgNameMst orgNameMst);

    /**
     * update the orgName by orgName id
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return result of orgName
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst updateOrgName(ImOrgNameMst orgNameMst);

    /**
     * delete the orgName by orgName id
     *
     * @param orgName
     *        orgName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteOrgName(ImOrgNameMst orgName);

    /**
     * delete force the orgNameMst by orgNameMst id
     *
     * @param orgNameMst
     *        orgNameMst entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceOrgName(ImOrgNameMst orgNameMst);

    /**
     * get orgName by org id
     *
     * @param orgId
     *        orgId
     * @return result of orgName
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgNameMst> getOrgName(String orgId);

    /**
     * get orgName by org id
     *
     * @param orgNameId
     *        orgNameId
     * @return result of orgName
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