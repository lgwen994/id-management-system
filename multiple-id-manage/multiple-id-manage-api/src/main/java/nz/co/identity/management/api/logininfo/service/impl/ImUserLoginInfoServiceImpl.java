package nz.co.identity.management.api.logininfo.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.dao.ImUserLoginInfoDAO;
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;
import nz.co.identity.management.api.logininfo.service.ImUserLoginInfoService;

/**
 *
 * the implementation class of userlogininfo's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImUserLoginInfoServiceImpl
        extends BaseService<ImUserLoginInfoDAO>
        implements ImUserLoginInfoService {

    /**
     * stvIdmfUserLoginInfoDAO' list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImUserLoginInfoDAO> stvIdmfUserLoginInfoDAOList;

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
    public ImUserLoginInfo getUserLoginInfoByCode(String userLoginCode) {
        return getDAO(historyLogininfo, stvIdmfUserLoginInfoDAOList)
                .getUserLoginInfoByCode(userLoginCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserLoginInfo(ImUserLoginInfo userLoginInfo) {
        return getDAO(historyLogininfo, stvIdmfUserLoginInfoDAOList)
                .deleteUserLoginInfo(userLoginInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo getUserLoginInfo(String userLoginId) {
        return getDAO(historyLogininfo, stvIdmfUserLoginInfoDAOList)
                .getUserLoginInfo(userLoginId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo updateUserLoginInfo(
            ImUserLoginInfo userLoginInfo) {
        return getDAO(historyLogininfo, stvIdmfUserLoginInfoDAOList)
                .updateUserLoginInfo(userLoginInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImUserLoginInfo> searchUserLoginInfo(
            ImUserLoginInfo userLoginInfo, Integer pageNum,
            Integer pageSize, List<String> sort) {
        return getDAO(historyLogininfo, stvIdmfUserLoginInfoDAOList)
                .searchUserLoginInfo(userLoginInfo, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo registerUserLoginInfo(
            ImUserLoginInfo userLoginInfo) {
        return getDAO(historyLogininfo, stvIdmfUserLoginInfoDAOList)
                .registerUserLoginInfo(userLoginInfo);
    }

}
