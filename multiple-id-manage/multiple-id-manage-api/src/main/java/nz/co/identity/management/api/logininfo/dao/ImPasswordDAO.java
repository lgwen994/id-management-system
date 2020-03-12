package nz.co.identity.management.api.logininfo.dao;
import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImPassword;

/**
 * according to the password form of CRUD operations, return a valid data.<br>
 * password's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImPasswordDAO extends BaseDAO {

    /**
     * delete the password by password id
     *
     * @param password
     *        password entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deletePassword(ImPassword password);

    /**
     * delete force the password by login id and company code
     *
     * @param password
     *        password entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForcePassword(ImPassword password);

    /**
     * get password by password id
     *
     * @param password
     *        password entity
     * @return result of password
     * @since Staveware Core Ver.5.3
     */
    ImPassword getPassword(ImPassword password);

    /**
     * update the password by password id
     *
     * @param password
     *        password entity
     * @return result of password
     * @since Staveware Core Ver.5.3
     */
    ImPassword updatePassword(ImPassword password);

    /**
     * search the password by password properties
     *
     * @param password
     *        password entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of password list
     * @since Staveware Core Ver.5.3
     */
    Page<ImPassword> searchPassword(ImPassword password,
            Integer pageNum, Integer pageSize, List<String> sort);

    /**
     * register the password
     *
     * @param password
     *        password entity
     * @return result of password
     * @since Staveware Core Ver.5.3
     */
    ImPassword registerPassword(ImPassword password);
}
