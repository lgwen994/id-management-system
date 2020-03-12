package nz.co.identity.management.api.logininfo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.dao.ImPasswordPolicyDAO;
import nz.co.identity.management.api.logininfo.entity.ImPasswordPolicy;
import nz.co.identity.management.api.logininfo.service.ImPasswordPolicyService;

/**
 *
 * the implementation class of passwordpolicy's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImPasswordPolicyServiceImpl
        extends BaseService<ImPasswordPolicyDAO>
        implements ImPasswordPolicyService {

    /**
     * stvIdmfPasswordPolicyDAOList
     * 
     * @since Staveware Core Ver.5.3
     */
    @Autowired
    private List<ImPasswordPolicyDAO> stvIdmfPasswordPolicyDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.logininfo:off}")
    private String historyLogininfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy getPasswordPolicyByCode(
            String passwordPolicyCode) {
        return getDAO(historyLogininfo, stvIdmfPasswordPolicyDAOList)
                .getPasswordPolicyByCode(passwordPolicyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePasswordPolicy(ImPasswordPolicy passwordPolicy) {
        return getDAO(historyLogininfo, stvIdmfPasswordPolicyDAOList)
                .deletePasswordPolicy(passwordPolicy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy getPasswordPolicy(String passwordPolicyId) {
        return getDAO(historyLogininfo, stvIdmfPasswordPolicyDAOList)
                .getPasswordPolicy(passwordPolicyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy updatePasswordPolicy(
            ImPasswordPolicy passwordPolicy) {
        return getDAO(historyLogininfo, stvIdmfPasswordPolicyDAOList)
                .updatePasswordPolicy(passwordPolicy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPasswordPolicy> searchPasswordPolicy(
            ImPasswordPolicy passwordPolicy, Integer pageNum,
            Integer pageSize, List<String> sort) {
        return getDAO(historyLogininfo, stvIdmfPasswordPolicyDAOList)
                .searchPasswordPolicy(passwordPolicy, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy registerPasswordPolicy(
            ImPasswordPolicy passwordPolicy) {
        return getDAO(historyLogininfo, stvIdmfPasswordPolicyDAOList)
                .registerPasswordPolicy(passwordPolicy);
    }

}
