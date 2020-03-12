package nz.co.identity.management.api.orguserinfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;

/**
 * according to the userName form of CRUD operations, return a valid data.<br>
 * userName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImUserNameMstService {

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
     * @return result of userName
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceUserName(ImUserNameMst userNameMst);

    /**
     * get userName by user id
     *
     * @param userId
     *        userId
     * @return result of userName
     * @since Staveware Core Ver.5.3
     */
    List<ImUserNameMst> getUserName(String userId);

    /**
     * get userName by userName id
     *
     * @param userNameId
     *        userNameId
     * @return result of userName
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