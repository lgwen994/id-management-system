package nz.co.identity.management.api.logininfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;

/**
 * according to the userlogininfo form of CRUD operations, return a valid
 * data.<br>
 * userlogininfo's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImUserLoginInfoService {

    /**
     * get userlogininfo by userlogininfo code
     *
     * @param userLoginCode
     *        userLogin code
     * @return result of userlogininfo
     * @since Staveware Core Ver.5.3
     */
    ImUserLoginInfo getUserLoginInfoByCode(String userLoginCode);

    /**
     * delete the userlogininfo by userlogininfo id
     *
     * @param userLoginInfo
     *        userlogininfo entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteUserLoginInfo(ImUserLoginInfo userLoginInfo);

    /**
     * get userlogininfo by userlogininfo id
     *
     * @param userLoginId
     *        userLogin id
     * @return result of userlogininfo
     * @since Staveware Core Ver.5.3
     */
    ImUserLoginInfo getUserLoginInfo(String userLoginId);

    /**
     * update the userlogininfo by userlogininfo id
     *
     * @param userLoginInfo
     *        userlogininfo entity
     * @return result of userlogininfo
     * @since Staveware Core Ver.5.3
     */
    ImUserLoginInfo updateUserLoginInfo(
            ImUserLoginInfo userLoginInfo);

    /**
     * paging search the userlogininfo by userlogininfo properties
     *
     * @param userLoginInfo
     *        userlogininfo entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of userlogininfo list
     * @since Staveware Core Ver.5.3
     */
    Page<ImUserLoginInfo> searchUserLoginInfo(
            ImUserLoginInfo userLoginInfo, Integer pageNum,
            Integer pageSize, List<String> sort);

    /**
     * register the userlogininfo
     *
     * @param userLoginInfo
     *        userlogininfo entity
     * @return result of userlogininfo
     * @since Staveware Core Ver.5.3
     */
    ImUserLoginInfo registerUserLoginInfo(
            ImUserLoginInfo userLoginInfo);

}