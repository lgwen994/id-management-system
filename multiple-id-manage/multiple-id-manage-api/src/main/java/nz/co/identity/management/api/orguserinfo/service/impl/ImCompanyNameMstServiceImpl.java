package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImCompanyNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImCompanyNameMstService;

/**
 *
 * The implementation class of companyName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImCompanyNameMstServiceImpl
        extends BaseService<ImCompanyNameMstDAO>
        implements ImCompanyNameMstService {

    /**
     * IdmfCompanyMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImCompanyNameMstDAO> stvIdmfCompanyNameMstDAOList;

    /**
     * historyCompanyinfo
     * 
     * @since Staveware Core Ver.5.3
     * 
     */
    @Value("${history.orguserinfo:off}")
    private String historyCompanyNameinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyNameMst registerCompanyName(
            ImCompanyNameMst companyNameMst) {
        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .registerCompanyName(companyNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyNameMst updateCompanyName(
            ImCompanyNameMst companyNameMst) {
        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .updateCompanyName(companyNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteCompanyName(ImCompanyNameMst companyName) {

        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .deleteCompanyName(companyName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImCompanyNameMst> getCompanyName(String companyId) {

        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .getCompanyName(companyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImCompanyNameMst getCompanyNameById(String companyNameId) {

        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .getCompanyNameById(companyNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImCompanyNameMst> getCompanyNameByCompanyCode(
            String companyCode) {
        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .getCompanyNameByCompanyCode(companyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImCompanyNameMst> searchCompanyName(
            ImCompanyNameMst companyNameMst, Integer pageNum,
            Integer pageSize, List<String> sort) {
        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .searchCompanyName(companyNameMst, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceCompanyName(ImCompanyNameMst companyName) {

        return getDAO(historyCompanyNameinfo, stvIdmfCompanyNameMstDAOList)
                .deleteForceCompanyName(companyName);
    }

}
