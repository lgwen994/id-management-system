package nz.co.identity.management.api.logininfo.service;

import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;

/**
 * according to the logincontrol form of CRUD operations, return a valid
 * data.<br>
 * logincontrol's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImLoginControlInfoService {

    /**
     * delete the logincontrolinfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteLoginControlInfo(ImLoginControlInfo loginControlInfo);

    /**
     * delete force the logincontrolinfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceLoginControlInfo(
            ImLoginControlInfo loginControlInfo);

    /**
     * get LoginControlInfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return result of logincontrolinfo
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo getLoginControlInfo(
            ImLoginControlInfo loginControlInfo);

    /**
     * update the LoginControlInfo by login id and company code
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return result of logincontrolinfo
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo updateLoginControlInfo(
            ImLoginControlInfo loginControlInfo);

    /**
     * search the logincontrolinfo by action properties
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of logincontrolinfo list
     * @since Staveware Core Ver.5.3
     */
    Page<ImLoginControlInfo> searchLoginControlInfo(
            ImLoginControlInfo loginControlInfo, Integer pageNum,
            Integer pageSize, List<String> sort);

    /**
     * register the logincontrolinfo
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return result of logincontrolinfo
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo registerLoginControlInfo(
            ImLoginControlInfo loginControlInfo);
}