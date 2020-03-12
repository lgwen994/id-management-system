package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImOrgNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImOrgNameMstService;

/**
 *
 * The implementation class of orgName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImOrgNameMstServiceImpl extends
        BaseService<ImOrgNameMstDAO> implements ImOrgNameMstService {
    /**
     * the list of StvIdmfOrgNameMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImOrgNameMstDAO> stvIdmfOrgNameMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.orguserinfo:off}")
    private String historyOrgNameinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgNameMst registerOrgName(ImOrgNameMst orgNameMst) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .registerOrgName(orgNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgNameMst updateOrgName(ImOrgNameMst orgNameMst) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .updateOrgName(orgNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteOrgName(ImOrgNameMst orgName) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .deleteOrgName(orgName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceOrgName(ImOrgNameMst orgNameMst) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .deleteForceOrgName(orgNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImOrgNameMst> getOrgName(String orgId) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .getOrgName(orgId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImOrgNameMst getOrgNameById(String orgNameId) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .getOrgNameById(orgNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImOrgNameMst> getOrgNameByOrgCode(String orgCode) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .getOrgNameByOrgCode(orgCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImOrgNameMst> searchOrgName(ImOrgNameMst orgNameMst,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return getDAO(historyOrgNameinfo, stvIdmfOrgNameMstDAOList)
                .searchOrgName(orgNameMst, pageNum, pageSize, sort);
    }

}
