package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImTitleNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImTitleNameMstService;

/**
 *
 * The implementation class of titleName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImTitleNameMstServiceImpl
        extends BaseService<ImTitleNameMstDAO>
        implements ImTitleNameMstService {

    /**
     * the list of StvIdmfRoleNameMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImTitleNameMstDAO> stvIdmfTitleNameMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.orguserinfo:off}")
    private String historyTitleNameinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleNameMst registerTitleName(
            ImTitleNameMst titleNameMst) {
        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .registerTitleName(titleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleNameMst updateTitleName(
            ImTitleNameMst titleNameMst) {
        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .updateTitleName(titleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteTitleName(ImTitleNameMst titleName) {

        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .deleteTitleName(titleName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceTitleName(ImTitleNameMst titleNameMst) {

        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .deleteForceTitleName(titleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImTitleNameMst> getTitleName(String titleId) {

        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .getTitleName(titleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleNameMst getTitleNameById(String titleNameId) {

        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .getTitleNameById(titleNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImTitleNameMst> getTitleNameByTitleCode(String titleCode) {
        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .getTitleNameByTitleCode(titleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImTitleNameMst> searchTitleName(
            ImTitleNameMst titleNameMst, Integer pageNum, Integer pageSize,
            List<String> sort) {
        return getDAO(historyTitleNameinfo, stvIdmfTitleNameMstDAOList)
                .searchTitleName(titleNameMst, pageNum, pageSize, sort);
    }

}
