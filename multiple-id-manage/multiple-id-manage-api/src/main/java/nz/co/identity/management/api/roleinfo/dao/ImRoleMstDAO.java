
package nz.co.identity.management.api.roleinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.roleinfo.entity.ImRoleMst;

/**
 * according to the role form of CRUD operations, return a valid data.<br>
 * Role's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImRoleMstDAO extends BaseDAO {

    /**
     * register the roleMst
     *
     * @param roleMst
     *        rolemst entity
     * @return result of roleMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleMst registerRole(ImRoleMst roleMst);

    /**
     * update the roleMst by roleMst id
     *
     * @param roleMst
     *        roleMst entity
     * @return result of roleMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleMst updateRole(ImRoleMst roleMst);

    /**
     * delete the roleMst by roleMst id
     *
     * @param roleMst
     *        roleMst entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteRole(ImRoleMst roleMst);

    /**
     * delete the roleMst by roleMst id
     *
     * @param roleMst
     *        roleMst entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceRole(ImRoleMst roleMst);

    /**
     * get roleMst by roleMst id
     *
     * @param roleId
     *        role id
     * @return result of roleMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleMst getRole(String roleId);

    /**
     * get roleMst by roleMst code
     *
     * @param roleCode
     *        role code
     * @return result of RoleMst
     * @since Staveware Core Ver.5.3
     */
    ImRoleMst getRoleByRoleCode(String roleCode);

    /**
     * search the roleMst by roleMst properties
     *
     * @param roleMst
     *        role entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of roleMst list
     * @since Staveware Core Ver.5.3
     */
    Page<ImRoleMst> searchRole(ImRoleMst roleMst, Integer pageNum,
            Integer pageSize, List<String> sort);

}