
package nz.co.identity.management.api.logininfo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.exception.ImRecordInexistenceException;
import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.dao.ImLoginControlInfoDAO;
import nz.co.identity.management.api.logininfo.dao.ImUserLoginInfoDAO;
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;
import nz.co.identity.management.api.logininfo.entity.ImPassword;
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;
import nz.co.identity.management.api.logininfo.mapper.ImLoginControlInfoMapper;
import nz.co.identity.management.api.logininfo.mapper.ImPasswordMapper;
import nz.co.identity.management.api.logininfo.mapper.ImUserLoginInfoMapper;

/**
 *
 * the implementation class of role's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfLoginControlInfoWithoutHistoryDAO")
public class ImLoginControlInfoWithoutHistoryDAOImpl
        implements ImLoginControlInfoDAO {

    /**
     * stvIdmfLoginControlInfoWithHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfLoginControlInfoWithHistoryDAO")
    private ImLoginControlInfoDAO stvIdmfLoginControlInfoWithHistoryDAO;

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
    public ImLoginControlInfo registerLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {
        return stvIdmfLoginControlInfoWithHistoryDAO
                .registerLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginControlInfo updateLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {
        return stvIdmfLoginControlInfoWithHistoryDAO
                .updateLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {

        return stvIdmfLoginControlInfoWithHistoryDAO
                .deleteLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {

        // ログインID、会社コードがログイン制御情報に存在しない場合
        if (idmfLoginControlInfoMapper
                .selectByPrimaryKey(loginControlInfo) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + loginControlInfo.getLoginId() + "] and company code["
                    + loginControlInfo.getCompanyCode()
                    + "] is not in LoginControlInfo.");
        }

        List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
                .selectByLoginIdAndCompanyCode(loginControlInfo.getLoginId(),
                        loginControlInfo.getCompanyCode());

        // ログインID、会社コードがユーザ_ログイン情報マスタに存在しない場合
        if (CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            throw new ImRecordInexistenceException("The loginId["
                    + loginControlInfo.getLoginId() + "] and company code["
                    + loginControlInfo.getCompanyCode()
                    + "] is not in UserLoginInfo.");
        }
        ImPassword idmfPassword = new ImPassword();
        idmfPassword.setLoginId(loginControlInfo.getLoginId());
        idmfPassword.setCompanyCode(loginControlInfo.getCompanyCode());
        // ログインID、会社コードがパスワードに存在しない場合
        if (idmfPasswordMapper.selectByPrimaryKey(idmfPassword) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + loginControlInfo.getLoginId() + "] and company code["
                    + loginControlInfo.getCompanyCode()
                    + "] is not in Password.");
        }

        if (!CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            ImUserLoginInfo userLoginInfo = new ImUserLoginInfo();
            userLoginInfo.setLoginId(loginControlInfo.getLoginId());
            userLoginInfo.setCompanyCode(loginControlInfo.getCompanyCode());
            // 削除失敗
            if (!stvIdmfUserLoginInfoWithoutHistoryDAO
                    .deleteForceUserLoginInfo(userLoginInfo)) {
                throw new ImRuntimeException(
                        "Delete userLoginInfo data from DB with failed.");
            }
        }

        if (idmfLoginControlInfoMapper
                .deleteByPrimaryKey(loginControlInfo) == 0) {
            throw new ImRuntimeException(
                    "Delete loginControlInfo data from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginControlInfo getLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {
        return stvIdmfLoginControlInfoWithHistoryDAO
                .getLoginControlInfo(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImLoginControlInfo> searchLoginControlInfo(
            ImLoginControlInfo loginControlInfo, Integer pageNum,
            Integer pageSize, List<String> sort) {
        return stvIdmfLoginControlInfoWithHistoryDAO.searchLoginControlInfo(
                loginControlInfo, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHistoryType() {
        return BaseDAO.HISTORY_TYPE_OFF;
    }
}
