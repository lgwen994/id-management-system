
package nz.co.identity.management.api.roleinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.roleinfo.entity.ImRoleNameMst;

/**
 * according to the roleName form of CRUD operations, return a valid data.<br>
 * RoleName's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImRoleNameMstDAO extends BaseDAO {

    /**
     * register the roleNameMst
     *
     * @param roleNameMst
     *        roleNameMst entity
     * @return result of roleNameMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleNameMst registerRoleName(ImRoleNameMst roleNameMst);

    /**
     * update the roleNameMst by roleNameMst id
     *
     * @param roleNameMst
     *        roleNameMst entity
     * @return result of roleNameMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleNameMst updateRoleName(ImRoleNameMst roleNameMst);

    /**
     * delete the roleNameMst by roleNameMst id
     *
     * @param roleNameMst
     *        roleNameMst entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteRoleName(ImRoleNameMst roleNameMst);

    /**
     * delete the roleNameMst by roleNameMst id
     *
     * @param roleNameMst
     *        roleNameMst id
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceRoleName(ImRoleNameMst roleNameMst);

    /**
     * get roleNameMst by role id
     *
     * @param roleId
     *        role id
     * @return result of roleNameMst
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleNameMst> getRoleName(String roleId);

    /**
     * get roleNameMst by roleNameMst id
     *
     * @param roleNameId
     *        roleNameMst id
     * @return result of roleNameMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleNameMst getRoleNameById(String roleNameId);

    /**
     * get roleNameMst by roleMst code
     *
     * @param roleCode
     *        roleMst code
     * @return result of RoleNameMst
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleNameMst> getRoleNameByRoleCode(String roleCode);

    /**
     * search the roleNameMst by roleNameMst properties
     *
     * @param roleNameMst
     *        roleNameMst entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of roleMst list
     * @since Staveware Core Ver.5.3
     */
    Page<ImRoleNameMst> searchRoleName(ImRoleNameMst roleNameMst,
            Integer pageNum, Integer pageSize, List<String> sort);

}