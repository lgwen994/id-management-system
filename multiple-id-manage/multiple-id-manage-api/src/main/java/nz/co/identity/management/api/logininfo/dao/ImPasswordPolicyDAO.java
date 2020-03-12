package nz.co.identity.management.api.logininfo.dao;
import java.util.List;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImPasswordPolicy;

/**
 * according to the passwordpolicy form of CRUD operations, return a valid
 * data.<br>
 * passwordpolicy's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImPasswordPolicyDAO extends BaseDAO {

    /**
     * get passwordpolicy by passwordpolicy code
     *
     * @param passwordPolicyCode
     *        passwordPolicy code
     * @return result of passwordpolicy
     * @since Staveware Core Ver.5.3
     */
    ImPasswordPolicy getPasswordPolicyByCode(String passwordPolicyCode);

    /**
     * delete the passwordpolicy by passwordpolicy id
     *
     * @param passwordPolicy
     *        passwordpolicy entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deletePasswordPolicy(ImPasswordPolicy passwordPolicy);

    /**
     * get passwordpolicy by passwordpolicy id
     *
     * @param passwordPolicyId
     *        passwordPolicy id
     * @return result of passwordpolicy
     * @since Staveware Core Ver.5.3
     */
    ImPasswordPolicy getPasswordPolicy(String passwordPolicyId);

    /**
     * update the passwordpolicy by passwordpolicy id
     *
     * @param passwordPolicy
     *        passwordpolicy entity
     * @return result of passwordpolicy
     * @since Staveware Core Ver.5.3
     */
    ImPasswordPolicy updatePasswordPolicy(
            ImPasswordPolicy passwordPolicy);

    /**
     * search the passwordpolicy by passwordpolicy properties
     *
     * @param passwordPolicy
     *        passwordpolicy entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of passwordpolicy list
     * @since Staveware Core Ver.5.3
     */
    Page<ImPasswordPolicy> searchPasswordPolicy(
            ImPasswordPolicy passwordPolicy, Integer pageNum,
            Integer pageSize, List<String> sort);

    /**
     * register the passwordpolicy
     *
     * @param passwordPolicy
     *        passwordpolicy entity
     * @return result of passwordpolicy
     * @since Staveware Core Ver.5.3
     */
    ImPasswordPolicy registerPasswordPolicy(
            ImPasswordPolicy passwordPolicy);

}
