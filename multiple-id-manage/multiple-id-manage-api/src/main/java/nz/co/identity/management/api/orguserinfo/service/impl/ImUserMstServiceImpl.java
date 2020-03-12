package nz.co.identity.management.api.orguserinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.dao.ImUserMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImUserMst;
import nz.co.identity.management.api.orguserinfo.service.ImUserMstService;

/**
 *
 * The implementation class of user's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImUserMstServiceImpl extends BaseService<ImUserMstDAO>
        implements ImUserMstService {

    /**
     * the list of StvIdmfUserMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImUserMstDAO> stvIdmfUserMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.orguserinfo:off}")
    private String historyUserinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserMst registerUser(ImUserMst userMst) {
        return getDAO(historyUserinfo, stvIdmfUserMstDAOList)
                .registerUser(userMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserMst updateUser(ImUserMst userMst) {
        return getDAO(historyUserinfo, stvIdmfUserMstDAOList)
                .updateUser(userMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUser(ImUserMst userMst) {

        return getDAO(historyUserinfo, stvIdmfUserMstDAOList)
                .deleteUser(userMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceUser(ImUserMst userMst) {

        return getDAO(historyUserinfo, stvIdmfUserMstDAOList)
                .deleteForceUser(userMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserMst getUser(String userId) {

        return getDAO(historyUserinfo, stvIdmfUserMstDAOList).getUser(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserMst getUserByUserCode(String userCode) {

        return getDAO(historyUserinfo, stvIdmfUserMstDAOList)
                .getUserByUserCode(userCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImUserMst> searchUser(ImUserMst userMst,
            Integer pageNum, Integer pageSize, List<String> sort) {

        return getDAO(historyUserinfo, stvIdmfUserMstDAOList)
                .searchUser(userMst, pageNum, pageSize, sort);
    }

}
