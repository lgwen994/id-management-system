package nz.co.identity.management.api.logininfo.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import nz.co.identity.management.api.common.BaseDAO;
import nz.co.identity.management.api.common.CommonUtils;
import nz.co.identity.management.api.common.exception.ImOptimisticLockingFailureException;
import nz.co.identity.management.api.common.exception.ImRecordInexistenceException;
import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.api.common.mapper.SerialGenerator;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.common.page.Pageable;
import nz.co.identity.management.api.logininfo.dao.ImUserLoginInfoDAO;
import nz.co.identity.management.api.logininfo.entity.ImPassword;
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;
import nz.co.identity.management.api.logininfo.mapper.ImPasswordMapper;
import nz.co.identity.management.api.logininfo.mapper.ImUserLoginInfoMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImUserMstMapper;

/**
 *
 * the implementation class of user login info's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfUserLoginInfoWithoutHistoryDAO")
public class ImUserLoginInfoWithoutHistoryDAOImpl
        implements ImUserLoginInfoDAO {

    /**
     * serial Generator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * idmfUserLoginInfoMapper
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
     * idmfUserMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserMstMapper idmfUserMstMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo registerUserLoginInfo(
            ImUserLoginInfo userLoginInfo) {

        // ユーザIDがユーザマスタに存在しない場合
        if (idmfUserMstMapper
                .selectByPrimaryKey(userLoginInfo.getUserId()) == null) {
            throw new ImRecordInexistenceException("The userId["
                    + userLoginInfo.getUserId() + "] is not in UserMst.");
        }

        ImPassword idmfPassword = new ImPassword();
        idmfPassword.setLoginId(userLoginInfo.getLoginId());
        idmfPassword.setCompanyCode(userLoginInfo.getCompanyCode());
        // ログインID、会社コードがパスワードに存在しない場合
        if (idmfPasswordMapper.selectByPrimaryKey(idmfPassword) == null) {
            throw new ImRecordInexistenceException("The loginId["
                    + userLoginInfo.getLoginId() + "] and company code["
                    + userLoginInfo.getCompanyCode() + "] is not in Password.");
        }

        // entityのidがnullの場合、自動採番してDBに登録
        if (StringUtils.isEmpty(userLoginInfo.getUserLoginInfoId())) {
            userLoginInfo.setUserLoginInfoId(serialGenerator
                    .selectSerial(ImUserLoginInfo.SERIAL_TABLE));
        } else {

            // 重複するidを登録する場合に、例外をthrow
            if (idmfUserLoginInfoMapper.selectByPrimaryKey(
                    userLoginInfo.getUserLoginInfoId()) != null) {
                throw new ImRuntimeException("The userLoginInfoId["
                        + userLoginInfo.getUserLoginInfoId()
                        + "] has been exists in UserLoginInfo.");
            }
        }

        userLoginInfo.setCreatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(userLoginInfo.getCreatedUser())) {
            userLoginInfo.setCreatedUser(CommonUtils.getLoginUser());
        }
        if (userLoginInfo.getActiveStartTime() == null) {
            userLoginInfo.setActiveStartTime(CommonUtils.getSystemTime());
        }
        userLoginInfo.setUpdatedTime(null);
        userLoginInfo.setUpdatedUser(null);
        List<ImUserLoginInfo> selectResult = idmfUserLoginInfoMapper
                .selectByUserLoginInfoCode(
                        userLoginInfo.getUserLoginInfoCode());
        if (CollectionUtils.isEmpty(selectResult)) {
            userLoginInfo.setVersionNo(0);
            userLoginInfo.setDeletedFlg(Short.valueOf("0"));
            if (idmfUserLoginInfoMapper.insert(userLoginInfo) == 0) {
                throw new ImRuntimeException(
                        "Insert userLoginInfo data into DB with failed.");
            }
        } else {
            throw new ImRuntimeException("The same code with failed.");
        }

        return userLoginInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo updateUserLoginInfo(
            ImUserLoginInfo userLoginInfo) {

        ImUserLoginInfo selectResult = idmfUserLoginInfoMapper
                .selectByPrimaryKey(userLoginInfo.getUserLoginInfoId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The userLoginInfoId[" + userLoginInfo.getUserLoginInfoId()
                            + "] is not in IdmfuserLoginInfo.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            // 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
            String code = userLoginInfo.getUserLoginInfoCode();
            if (code != null
                    && !selectResult.getUserLoginInfoCode().equals(code)) {
                throw new ImRecordInexistenceException(
                        "There is a record with disaccorded code[" + code
                                + "]");
            }
            Integer inputVersionNo = userLoginInfo.getVersionNo();
            if (!inputVersionNo.equals(selectResult.getVersionNo())) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            userLoginInfo.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(userLoginInfo.getUpdatedUser())) {
                userLoginInfo.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            int version = idmfUserLoginInfoMapper
                    .selectMaxVersion(userLoginInfo.getUserLoginInfoCode());
            userLoginInfo.setVersionNo(version + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userLoginInfo", userLoginInfo);
            map.put("versionBase", inputVersionNo);

            int updateflg = idmfUserLoginInfoMapper.updateByPrimaryKey(map);
            // 更新失敗
            if (updateflg == 0) {
                throw new ImRuntimeException(
                        "Update userLoginInfo data into DB with failed.");
            }
        }

        return userLoginInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserLoginInfo(ImUserLoginInfo userLoginInfo) {
        ImUserLoginInfo selectResult = idmfUserLoginInfoMapper
                .selectByPrimaryKey(userLoginInfo.getUserLoginInfoId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The userLoginInfoId[" + userLoginInfo.getUserLoginInfoId()
                            + "] is not in UserLoginInfo.");
        }

        if (!selectResult.getVersionNo().equals(userLoginInfo.getVersionNo())) {
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }

        // 削除失敗
        if (idmfUserLoginInfoMapper.deleteByUserLoginInfoIdByVersion(
                userLoginInfo.getUserLoginInfoId(),
                userLoginInfo.getVersionNo()) == 0) {
            throw new ImRuntimeException(
                    "Delete userLoginInfo data from DB with failed.");
        }

        return true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceUserLoginInfo(
            ImUserLoginInfo userLoginInfo) {

        if (idmfUserLoginInfoMapper.deleteByLoginIdAndCompanyCode(
                userLoginInfo.getLoginId(),
                userLoginInfo.getCompanyCode()) == 0) {
            throw new ImRuntimeException(
                    "Delete userLoginInfo data from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo getUserLoginInfo(String userLoginId) {
        return idmfUserLoginInfoMapper.selectByPrimaryKey(userLoginId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserLoginInfo getUserLoginInfoByCode(String userLoginCode) {
        List<ImUserLoginInfo> result = idmfUserLoginInfoMapper
                .selectByUserLoginInfoCode(userLoginCode);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImUserLoginInfo> searchUserLoginInfo(
            ImUserLoginInfo userLoginInfo, Integer pageNum,
            Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userLoginInfo", userLoginInfo);
        map.put("pageSize", pageable.getPageSize());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(idmfUserLoginInfoMapper
                .selectCountByUserLoginInfo(map).longValue());
        // ページの初期化
        Page<ImUserLoginInfo> page = new Page<ImUserLoginInfo>(
                idmfUserLoginInfoMapper.selectUserLoginInfo(map), pageable);
        // ページ情報
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHistoryType() {
        return BaseDAO.HISTORY_TYPE_OFF;
    }

}
