package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImTitleMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleMst;
import nz.co.identity.management.api.orguserinfo.service.ImTitleMstService;

/**
 *
 * The implementation class of title's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImTitleMstServiceImpl extends BaseService<ImTitleMstDAO>
        implements ImTitleMstService {

    /**
     * the list of StvIdmfTitleMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImTitleMstDAO> stvIdmfTitleMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.orguserinfo:off}")
    private String historyTitleinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleMst registerTitle(ImTitleMst titleMst) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .registerTitle(titleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleMst updateTitle(ImTitleMst titleMst) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .updateTitle(titleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteTitle(ImTitleMst titleMst) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .deleteTitle(titleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceTitle(ImTitleMst titleMst) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .deleteForceTitle(titleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleMst getTitle(String titleId) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .getTitle(titleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleMst getTitleByTitleCode(String titleCode) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .getTitleByTitleCode(titleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImTitleMst> searchTitle(ImTitleMst titleMst,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return getDAO(historyTitleinfo, stvIdmfTitleMstDAOList)
                .searchTitle(titleMst, pageNum, pageSize, sort);
    }

}
