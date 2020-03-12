package nz.co.identity.management.api.logininfo.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.dao.ImLoginPolicyDAO;
import nz.co.identity.management.api.logininfo.entity.ImLoginPolicy;
import nz.co.identity.management.api.logininfo.service.ImLoginPolicyService;

/**
 *
 * the implementation class of loginpolicy's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImLoginPolicyServiceImpl
        extends BaseService<ImLoginPolicyDAO>
        implements ImLoginPolicyService {

    /**
     * stvIdmfLoginPolicyDAOList
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImLoginPolicyDAO> stvIdmfLoginPolicyDAOList;

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
    public ImLoginPolicy getLoginPolicyByCode(String loginPolicyCode) {
        return getDAO(historyLogininfo, stvIdmfLoginPolicyDAOList)
                .getLoginPolicyByCode(loginPolicyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteLoginPolicy(ImLoginPolicy loginPolicy) {
        return getDAO(historyLogininfo, stvIdmfLoginPolicyDAOList)
                .deleteLoginPolicy(loginPolicy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy getLoginPolicy(String loginPolicyId) {
        return getDAO(historyLogininfo, stvIdmfLoginPolicyDAOList)
                .getLoginPolicy(loginPolicyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy updateLoginPolicy(
            ImLoginPolicy loginPolicy) {
        return getDAO(historyLogininfo, stvIdmfLoginPolicyDAOList)
                .updateLoginPolicy(loginPolicy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImLoginPolicy> searchLoginPolicy(
            ImLoginPolicy loginPolicy, Integer pageNum, Integer pageSize,
            List<String> sort) {
        return getDAO(historyLogininfo, stvIdmfLoginPolicyDAOList)
                .searchLoginPolicy(loginPolicy, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy registerLoginPolicy(
            ImLoginPolicy loginPolicy) {
        return getDAO(historyLogininfo, stvIdmfLoginPolicyDAOList)
                .registerLoginPolicy(loginPolicy);
    }

}
