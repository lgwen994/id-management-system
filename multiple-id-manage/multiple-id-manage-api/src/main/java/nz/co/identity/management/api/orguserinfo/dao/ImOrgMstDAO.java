
package nz.co.identity.management.api.orguserinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;

/**
 * according to the org form of CRUD operations.
 * 
 * @since Staveware Core Ver.5.3
 *
 */
public interface ImOrgMstDAO extends BaseDAO {
    /**
     * get organization by organization code
     *
     * @param orgCode
     *        organization code
     * @return result of organization
     * @since Staveware Core Ver.5.3
     */
    ImOrgMst getOrgByOrgCode(String orgCode);

    /**
     * get organization by organization id
     *
     * @param orgId
     *        organization id
     * @return result of organization
     * @since Staveware Core Ver.5.3
     */
    ImOrgMst getOrg(String orgId);

    /**
     * update the organization by organization id
     *
     * @param orgMst
     *        organization entity
     * @return result of organization
     * @since Staveware Core Ver.5.3
     */
    ImOrgMst updateOrg(ImOrgMst orgMst);

    /**
     * search the organization by organization properties
     *
     * @param orgMst
     *        organization entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of organizations list
     * @since Staveware Core Ver.5.3
     */
    Page<ImOrgMst> searchOrg(ImOrgMst orgMst, Integer pageNum,
            Integer pageSize, List<String> sort);

    /**
     * register the organization
     *
     * @param orgMst
     *        organization entity
     * @return result of organization
     * @since Staveware Core Ver.5.3
     */
    ImOrgMst registerOrg(ImOrgMst orgMst);

    /**
     * delete the organization by organization id
     *
     * @param orgMst
     *        organization entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteOrg(ImOrgMst orgMst);

    /**
     * delete force the organization by organization id
     *
     * @param orgMst
     *        organization entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceOrg(ImOrgMst orgMst);

}
