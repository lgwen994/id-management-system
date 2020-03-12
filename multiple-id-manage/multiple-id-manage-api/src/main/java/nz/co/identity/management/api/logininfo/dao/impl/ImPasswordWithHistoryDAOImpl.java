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
@Service(value = "stvIdmfPasswordWithHistoryDAO")
public class ImPasswordWithHistoryDAOImpl implements ImPasswordDAO {

    /**
     * stvIdmfUserLoginInfoWithHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfUserLoginInfoWithHistoryDAO")
    private ImUserLoginInfoDAO stvIdmfUserLoginInfoWithHistoryDAO;

    /**
     * idmfPasswordMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordMapper idmfPasswordMapper;

    /**
     * IdmfUserLoginInfoMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserLoginInfoMapper idmfUserLoginInfoMapper;

    /**
     * IdmfLoginControlInfoMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImLoginControlInfoMapper idmfLoginControlInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword registerPassword(ImPassword password) {
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

        List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
                .selectByLoginIdAndCompanyCode(password.getLoginId(),
                        password.getCompanyCode());

        // ログインID、会社コードがユーザ_ログイン情報マスタに存在しない場合
        if (CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "] and company code["
                    + password.getCompanyCode() + "] is not in UserLoginInfo.");
        }

        if (idmfPasswordMapper.selectByPrimaryKey(password) != null) {
            throw new ImRuntimeException(
                    "The loginId[" + password.getLoginId()
                            + "] and company code[" + password.getCompanyCode()
                            + "] has been exists in Password.");
        } else {
            if (idmfPasswordMapper.insert(password) == 0) {
                throw new ImRuntimeException(
                        "Insert password data into DB with failed.");
            }
        }
        return password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword updatePassword(ImPassword password) {

        if (idmfPasswordMapper.selectByPrimaryKey(password) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "] and company code["
                    + password.getCompanyCode() + "] is not in Password.");
        }

        List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
                .selectByLoginIdAndCompanyCode(password.getLoginId(),
                        password.getCompanyCode());
        // ログインID、会社コードがユーザ_ログイン情報マスタに存在しない場合
        if (CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "] and company code["
                    + password.getCompanyCode() + "] is not in UserLoginInfo.");
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

        if (idmfPasswordMapper.updateByPrimaryKey(password) == 0) {
            throw new ImRuntimeException(
                    "Update password data into DB with failed.");
        }
        return password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePassword(ImPassword password) {
        if (idmfPasswordMapper.selectByPrimaryKey(password) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "and company code["
                    + password.getCompanyCode() + "] ] is not in Password.");
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

        List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
                .selectByLoginIdAndCompanyCode(password.getLoginId(),
                        password.getCompanyCode());

        // ログインID、会社コードがユーザ_ログイン情報マスタに存在しない場合
        if (CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            throw new ImRecordInexistenceException("The loginId["
                    + password.getLoginId() + "] and company code["
                    + password.getCompanyCode() + "] is not in UserLoginInfo.");
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
        return idmfPasswordMapper.selectByPrimaryKey(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPassword> searchPassword(ImPassword password,
            Integer pageNum, Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("password", password);
        map.put("pageSize", pageable.getPageSize());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(
                idmfPasswordMapper.selectCountByPassword(map).longValue());
        // ページの初期化
        Page<ImPassword> page = new Page<ImPassword>(
                idmfPasswordMapper.selectPassword(map), pageable);
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
