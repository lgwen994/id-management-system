
package nz.co.identity.management.api.roleinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.roleinfo.data.ImRoleAndPosition;
import nz.co.identity.management.api.roleinfo.entity.ImPositionRole;

/**
 * according to the positionRole form of CRUD operations, return a valid
 * data.<br>
 * positionRole's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImPositionRoleDAO extends BaseDAO {

    /**
     * register the positionRole
     *
     * @param positionRole
     *        positionRole entity
     * @return result of PositionRole
     * @since Staveware Core Ver.5.3
     */
    ImPositionRole registerPositionRole(ImPositionRole positionRole);

    /**
     * update the positionRole by positionRole id
     *
     * @param positionRole
     *        positionRole entity
     * @return result of positionRole
     * @since Staveware Core Ver.5.3
     */
    ImPositionRole updatePositionRole(ImPositionRole positionRole);

    /**
     * delete the positionRole by positionRole id
     *
     * @param positionRole
     *        positionRole entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deletePositionRole(ImPositionRole positionRole);

    /**
     * delete Force the positionRole by positionRole id
     *
     * @param positionRole
     *        positionRole entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForcePositionRole(ImPositionRole positionRole);

    /**
     * search the positionRole by positionRole properties
     *
     * @param position
     *        position entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of positionRole list
     * @since Staveware Core Ver.5.3*
     */
    Page<ImRoleAndPosition> searchRole(ImPosition position,
            Integer pageNum, Integer pageSize, List<String> sort);

    /**
     * get positionRole by positionRole code
     *
     * @param positionRoleCode
     *        positionRole code
     * @return result of positionRole
     * @since Staveware Core Ver.5.3
     */
    ImPositionRole getPositionRoleByPositionRoleCode(
            String positionRoleCode);

    /**
     * get positionRole by positionRole id
     *
     * @param positionRoleId
     *        positionRole id
     * @return result of positionRole
     * @since Staveware Core Ver.5.3
     */
    ImPositionRole getPositionRole(String positionRoleId);

    /**
     * search the positionRole by positionRole properties
     *
     * @param positionRole
     *        PositionRole entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of positionRole list
     * @since Staveware Core Ver.5.3
     */
    Page<ImPositionRole> searchPositionRole(
            ImPositionRole positionRole, Integer pageNum, Integer pageSize,
            List<String> sort);
}