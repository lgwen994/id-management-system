package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImOrgMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;
import nz.co.identity.management.api.orguserinfo.service.ImOrgMstService;

/**
 *
 * The implementation class of organization's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImOrgMstServiceImpl extends BaseService<ImOrgMstDAO>
        implements ImOrgMstService {

    // private StvIdmfOrgMstMapper mapper;
    /**
     * IdmfOrgMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImOrgMstDAO> stvIdmfOrgMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.orguserinfo:off}")
    private String historyOrginfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgMst getOrgByOrgCode(String orgCode) {

        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList)
                .getOrgByOrgCode(orgCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteOrg(ImOrgMst orgMst) {

        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList).deleteOrg(orgMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgMst getOrg(String orgId) {

        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList).getOrg(orgId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgMst updateOrg(ImOrgMst orgMst) {
        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList).updateOrg(orgMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImOrgMst> searchOrg(ImOrgMst orgMst, Integer pageNum,
            Integer pageSize, List<String> sort) {

        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList).searchOrg(orgMst,
                pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgMst registerOrg(ImOrgMst orgMst) {
        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList).registerOrg(orgMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceOrg(ImOrgMst orgMst) {

        return getDAO(historyOrginfo, stvIdmfOrgMstDAOList)
                .deleteForceOrg(orgMst);
    }

}
