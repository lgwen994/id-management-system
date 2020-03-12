
package nz.co.identity.management.api.orguserinfo.dao;

import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;

/**
 * according to the user name form of CRUD operations.
 * 
 * @since Staveware Core Ver.5.3
 *
 */
public interface ImUserNameMstDAO extends BaseDAO {
    /**
     * register the userName
     *
     * @param record
     *        userName entity
     * @return result of userName
     * @since Staveware Core Ver.5.3
     */
    ImUserNameMst registerUserName(ImUserNameMst record);

    /**
     * update the userName by userName id
     *
     * @param record
     *        userName entity
     * @return result of userName
     * @since Staveware Core Ver.5.3
     */
    ImUserNameMst updateUserName(ImUserNameMst record);

    /**
     * delete the userName by userName id
     *
     * @param userNameMst
     *        userName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteUserName(ImUserNameMst userNameMst);

    /**
     * delete force the userName by userName id
     *
     * @param userNameMst
     *        userName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceUserName(ImUserNameMst userNameMst);

    /**
     * get userName by user id
     *
     * @param userId
     *        userId
     * @return result of userId
     * @since Staveware Core Ver.5.3
     */
    List<ImUserNameMst> getUserName(String userId);

    /**
     * get userName by userName id
     *
     * @param userNameId
     *        userNameId
     * @return result of userId
     * @since Staveware Core Ver.5.3
     */
    ImUserNameMst getUserNameById(String userNameId);

    /**
     * get userNameMst by title code
     *
     * @param userCode
     *        user code
     * @return result of userNameMst
     * @since Staveware Core Ver.5.3
     */
    List<ImUserNameMst> getUserNameByUserCode(String userCode);

    /**
     * search the userNameMst by userNameMst properties
     *
     * @param userNameMst
     *        userNameMst entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of userNameMst list
     * @since Staveware Core Ver.5.3
     */
    Page<ImUserNameMst> searchUserName(ImUserNameMst userNameMst,
            Integer pageNum, Integer pageSize, List<String> sort);
}
