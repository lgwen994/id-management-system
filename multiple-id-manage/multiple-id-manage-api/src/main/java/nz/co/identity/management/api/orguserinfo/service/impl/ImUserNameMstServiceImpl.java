package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImUserNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImUserNameMstService;

/**
 *
 * The implementation class of userName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImUserNameMstServiceImpl
        extends BaseService<ImUserNameMstDAO>
        implements ImUserNameMstService {

    /**
     * the list of StvIdmfRoleNameMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImUserNameMstDAO> stvIdmfUserNameMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.orguserinfo:off}")
    private String historyUserNameinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserNameMst registerUserName(ImUserNameMst userNameMst) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .registerUserName(userNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserNameMst updateUserName(ImUserNameMst userNameMst) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .updateUserName(userNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserName(ImUserNameMst userNameMst) {

        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .deleteUserName(userNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceUserName(ImUserNameMst userNameMst) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .deleteForceUserName(userNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImUserNameMst> getUserName(String userId) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .getUserName(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserNameMst getUserNameById(String userNameId) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .getUserNameById(userNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImUserNameMst> getUserNameByUserCode(String userCode) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .getUserNameByUserCode(userCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImUserNameMst> searchUserName(
            ImUserNameMst userNameMst, Integer pageNum, Integer pageSize,
            List<String> sort) {
        return getDAO(historyUserNameinfo, stvIdmfUserNameMstDAOList)
                .searchUserName(userNameMst, pageNum, pageSize, sort);

    }

}
