
package nz.co.identity.management.api.logininfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.dao.ImLoginControlInfoDAO;
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;
import nz.co.identity.management.api.logininfo.service.ImLoginControlInfoService;

/**
 *
 * the implementation class of logincontrolinfo's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImLoginControlInfoServiceImpl
        extends BaseService<ImLoginControlInfoDAO>
        implements ImLoginControlInfoService {

    /**
     * stvIdmfLoginControlInfoDAOList
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImLoginControlInfoDAO> stvIdmfLoginControlInfoDAOList;

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
    public boolean deleteLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {

        return getDAO(historyLogininfo, stvIdmfLoginControlInfoDAOList)
                .deleteLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {

        return getDAO(historyLogininfo, stvIdmfLoginControlInfoDAOList)
                .deleteForceLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginControlInfo getLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {

        return getDAO(historyLogininfo, stvIdmfLoginControlInfoDAOList)
                .getLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginControlInfo updateLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {
        return getDAO(historyLogininfo, stvIdmfLoginControlInfoDAOList)
                .updateLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImLoginControlInfo> searchLoginControlInfo(
            ImLoginControlInfo loginControlInfo, Integer pageNum,
            Integer pageSize, List<String> sort) {

        return getDAO(historyLogininfo, stvIdmfLoginControlInfoDAOList)
                .searchLoginControlInfo(loginControlInfo, pageNum, pageSize,
                        sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginControlInfo registerLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {
        return getDAO(historyLogininfo, stvIdmfLoginControlInfoDAOList)
                .registerLoginControlInfo(loginControlInfo);
    }

}
