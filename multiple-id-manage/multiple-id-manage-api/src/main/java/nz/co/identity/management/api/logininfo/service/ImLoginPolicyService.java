package nz.co.identity.management.api.logininfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImLoginPolicy;

/**
 * according to the loginpolicy form of CRUD operations, return a valid
 * data.<br>
 * loginpolicy's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImLoginPolicyService {
    /**
     * get Action by LoginPolicy code
     *
     * @param loginPolicyCode
     *        loginPolicy code
     * @return result of LoginPolicy
     * @since Staveware Core Ver.5.3
     */
    ImLoginPolicy getLoginPolicyByCode(String loginPolicyCode);

    /**
     * delete the LoginPolicy by LoginPolicy id
     *
     * @param loginPolicy
     *        loginPolicy entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteLoginPolicy(ImLoginPolicy loginPolicy);

    /**
     * get LoginPolicy by LoginPolicy id
     *
     * @param loginPolicyId
     *        loginPolicy id
     * @return result of Action
     * @since Staveware Core Ver.5.3
     */
    ImLoginPolicy getLoginPolicy(String loginPolicyId);

    /**
     * update the LoginPolicy by LoginPolicy id
     *
     * @param loginPolicy
     *        loginPolicy entity
     * @return result of LoginPolicy
     * @since Staveware Core Ver.5.3
     */
    ImLoginPolicy updateLoginPolicy(ImLoginPolicy loginPolicy);

    /**
     * paging search the LoginPolicy by LoginPolicy properties
     *
     * @param loginPolicy
     *        LoginPolicy entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of LoginPolicy list
     * @since Staveware Core Ver.5.3
     */
    Page<ImLoginPolicy> searchLoginPolicy(ImLoginPolicy loginPolicy,
            Integer pageNum, Integer pageSize, List<String> sort);

    /**
     * register the LoginPolicy
     *
     * @param loginPolicy
     *        loginPolicy entity
     * @return result of LoginPolicy
     * @since Staveware Core Ver.5.3
     */
    ImLoginPolicy registerLoginPolicy(ImLoginPolicy loginPolicy);
}