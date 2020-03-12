
package nz.co.identity.management.api.orguserinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImUserMst;

/**
 * according to the user form of CRUD operations.
 * 
 * @since Staveware Core Ver.5.3
 *
 */
public interface ImUserMstDAO extends BaseDAO {

    /**
     * register the user
     *
     * @param record
     *        user entity
     * @return result of user
     * @since Staveware Core Ver.5.3
     */
    ImUserMst registerUser(ImUserMst record);

    /**
     * update the user by user id
     *
     * @param record
     *        user entity
     * @return result of user
     * @since Staveware Core Ver.5.3
     */
    ImUserMst updateUser(ImUserMst record);

    /**
     * delete the user by user id
     *
     * @param userMst
     *        user entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteUser(ImUserMst userMst);

    /**
     * delete force the user by user id
     *
     * @param userMst
     *        user entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceUser(ImUserMst userMst);

    /**
     * get user by user id
     *
     * @param userId
     *        user id
     * @return result of user
     * @since Staveware Core Ver.5.3
     */
    ImUserMst getUser(String userId);

    /**
     * get user by user code
     *
     * @param userCode
     *        user code
     * @return result of user
     * @since Staveware Core Ver.5.3
     */
    ImUserMst getUserByUserCode(String userCode);

    /**
     * search the user by user properties
     *
     * @param userMst
     *        user entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of user list
     * @since Staveware Core Ver.5.3
     */
    Page<ImUserMst> searchUser(ImUserMst userMst, Integer pageNum,
            Integer pageSize, List<String> sort);
}
