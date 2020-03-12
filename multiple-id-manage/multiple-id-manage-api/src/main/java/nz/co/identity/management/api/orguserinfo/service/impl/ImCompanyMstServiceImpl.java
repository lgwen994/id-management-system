package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImCompanyMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyMst;
import nz.co.identity.management.api.orguserinfo.service.ImCompanyMstService;

/**
 *
 * The implementation class of company's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImCompanyMstServiceImpl extends
        BaseService<ImCompanyMstDAO> implements ImCompanyMstService {

    /**
     * the list of StvIdmfCompanyMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImCompanyMstDAO> stvIdmfCompanyMstDAOList;

    /**
     * historyCompanyinfo
     * 
     * @since Staveware Core Ver.5.3
     * 
     */
    @Value("${history.orguserinfo:off}")
    private String historyCompanyinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyMst registerCompany(ImCompanyMst companyMst) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .registerCompany(companyMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyMst updateCompany(ImCompanyMst companyMst) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .updateCompany(companyMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteCompany(ImCompanyMst companyMst) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .deleteCompany(companyMst);
    }

    @Override
    public boolean deleteForceCompany(ImCompanyMst companyMst) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .deleteForceCompany(companyMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyMst getCompany(String companyId) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .getCompany(companyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyMst getCompanyByCode(String companyCode) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .getCompanyByCode(companyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImCompanyMst> searchCompany(ImCompanyMst companyMst,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return getDAO(historyCompanyinfo, stvIdmfCompanyMstDAOList)
                .searchCompany(companyMst, pageNum, pageSize, sort);
    }

}
