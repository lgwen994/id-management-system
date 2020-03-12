package nz.co.identity.management.api.logininfo.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.exception.ImRecordInexistenceException;
import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.dao.ImPasswordDAO;
import nz.co.identity.management.api.logininfo.dao.ImUserLoginInfoDAO;
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;
import nz.co.identity.management.api.logininfo.entity.ImPassword;
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;
import nz.co.identity.management.api.logininfo.mapper.ImLoginControlInfoMapper;
import nz.co.identity.management.api.logininfo.mapper.ImPasswordMapper;
import nz.co.identity.management.api.logininfo.mapper.ImUserLoginInfoMapper;

/**
 *
 * the implementation class of password's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfPasswordWithoutHistoryDAO")
public class ImPasswordWithoutHistoryDAOImpl
        implements ImPasswordDAO {

    /**
     * StvIdmfPasswordWithHistoryDAOImpl
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfPasswordWithHistoryDAO")
    private ImPasswordDAO stvIdmfPasswordWithHistoryDAO;

    /**
     * stvIdmfUserLoginInfoWithoutHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfUserLoginInfoWithoutHistoryDAO")
    private ImUserLoginInfoDAO stvIdmfUserLoginInfoWithoutHistoryDAO;

    /**
     * IdmfLoginControlInfoMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImLoginControlInfoMapper idmfLoginControlInfoMapper;

    /**
     * IdmfUserLoginInfoMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserLoginInfoMapper idmfUserLoginInfoMapper;

    /**
     * IdmfPasswordMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordMapper idmfPasswordMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword registerPassword(ImPassword password) {
        return stvIdmfPasswordWithHistoryDAO.registerPassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword updatePassword(ImPassword password) {
        return stvIdmfPasswordWithHistoryDAO.updatePassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePassword(ImPassword password) {
        return stvIdmfPasswordWithHistoryDAO.deletePassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForcePassword(ImPassword password) {
        if (idmfPasswordMapper.selectByPrimaryKey(password) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "and company code["
                    + password.getCompanyCode() + "] ] is not in Password.");
        }

        List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
                .selectByLoginIdAndCompanyCode(password.getLoginId(),
                        password.getCompanyCode());
        // ログインID、会社コードがユーザ_ログイン情報マスタに存在しない場合
        if (CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "] and company code["
                    + password.getCompanyCode() + "] is not in UserLoginInfo.");
        } else {
            ImUserLoginInfo userLoginInfo = new ImUserLoginInfo();
            userLoginInfo.setLoginId(password.getLoginId());
            userLoginInfo.setCompanyCode(password.getCompanyCode());
            // 削除失敗
            if (!stvIdmfUserLoginInfoWithoutHistoryDAO
                    .deleteForceUserLoginInfo(userLoginInfo)) {
                throw new ImRuntimeException(
                        "Delete userLoginInfo data from DB with failed.");
            }
        }
        ImLoginControlInfo loginControlInfo = new ImLoginControlInfo();
        loginControlInfo.setLoginId(password.getLoginId());
        loginControlInfo.setCompanyCode(password.getCompanyCode());

        // ログインID、会社コードがログイン制御情報に存在しない場合
        if (idmfLoginControlInfoMapper
                .selectByPrimaryKey(loginControlInfo) == null) {
            throw new ImRecordInexistenceException(
                    "The loginId[" + password.getLoginId()
                            + "] and company code[" + password.getCompanyCode()
                            + "] is not in LoginControlInfo.");
        }

        if (idmfPasswordMapper.deleteByPrimaryKey(password) == 0) {
            throw new ImRuntimeException(
                    "Delete password data from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword getPassword(ImPassword password) {
        return stvIdmfPasswordWithHistoryDAO.getPassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPassword> searchPassword(ImPassword password,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return stvIdmfPasswordWithHistoryDAO.searchPassword(password, pageNum,
                pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHistoryType() {
        return BaseDAO.HISTORY_TYPE_OFF;
    }

}
