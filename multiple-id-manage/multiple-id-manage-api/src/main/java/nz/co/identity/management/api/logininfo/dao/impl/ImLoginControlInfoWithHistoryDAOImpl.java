
package nz.co.identity.management.api.logininfo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.CommonUtils;
import nz.co.identity.management.api.common.exception.ImRecordInexistenceException;
import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.common.page.Pageable;
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
 * the implementation class of login control info's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfLoginControlInfoWithHistoryDAO")
public class ImLoginControlInfoWithHistoryDAOImpl
        implements ImLoginControlInfoDAO {

    /**
     * stvIdmfUserLoginInfoWithHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfUserLoginInfoWithHistoryDAO")
    private ImUserLoginInfoDAO stvIdmfUserLoginInfoWithHistoryDAO;

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

        if (loginControlInfo.getAccountActiveStartTime() == null) {
            loginControlInfo
                    .setAccountActiveStartTime(CommonUtils.getSystemTime());
        }
        if (idmfLoginControlInfoMapper
                .selectByPrimaryKey(loginControlInfo) != null) {
            throw new ImRuntimeException("The loginId["
                    + loginControlInfo.getLoginId() + "] and company code["
                    + loginControlInfo.getCompanyCode()
                    + "] has been exists in LoginControlInfo.");
        } else {
            if (idmfLoginControlInfoMapper.insert(loginControlInfo) == 0) {
                throw new ImRuntimeException(
                        "Insert loginControlInfo data into DB with failed.");
            }
        }

        return loginControlInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginControlInfo updateLoginControlInfo(
            ImLoginControlInfo loginControlInfo) {

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
        // ログインID、会社コードがログイン制御情報に存在しない場合
        if (idmfLoginControlInfoMapper
                .selectByPrimaryKey(loginControlInfo) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + loginControlInfo.getLoginId() + "] and company code["
                    + loginControlInfo.getCompanyCode()
                    + "] is not in LoginControlInfo.");
        }

        if (idmfLoginControlInfoMapper
                .updateByPrimaryKey(loginControlInfo) == 0) {
            throw new ImRuntimeException(
                    "Update loginControlInfo data into DB with failed.");
        }
        return loginControlInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteLoginControlInfo(
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

        // 削除失敗
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
        } else {
            boolean retDel = false;
            for (ImUserLoginInfo stvIdmfUserLoginInfo : stvIdmfUserLoginInfoList) {
                retDel = stvIdmfUserLoginInfoWithHistoryDAO
                        .deleteForceUserLoginInfo(stvIdmfUserLoginInfo);
                if (!retDel) {
                    throw new ImRuntimeException(
                            "Delete userLoginInfo data from DB with failed.");
                }
            }
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
        return idmfLoginControlInfoMapper.selectByPrimaryKey(loginControlInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImLoginControlInfo> searchLoginControlInfo(
            ImLoginControlInfo loginControlInfo, Integer pageNum,
            Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginControlInfo", loginControlInfo);
        map.put("pageSize", pageable.getPageSize());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(idmfLoginControlInfoMapper
                .selectCountByLoginControlInfo(map).longValue());
        // ページの初期化
        Page<ImLoginControlInfo> page = new Page<ImLoginControlInfo>(
                idmfLoginControlInfoMapper.selectByLoginControlInfo(map),
                pageable);
        // ページ情報
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHistoryType() {
        return BaseDAO.HISTORY_TYPE_ON;
    }
}
