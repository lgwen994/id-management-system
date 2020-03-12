package nz.co.identity.management.api.logininfo.dao.impl;
import java.util.Date;
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
@Service(value = "stvIdmfUserLoginInfoWithHistoryDAO")
public class ImUserLoginInfoWithHistoryDAOImpl
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

        List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
                .selectByUserLoginInfoCode(
                        userLoginInfo.getUserLoginInfoCode());
        if (CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
            userLoginInfo.setVersionNo(0);
        } else {
            // 新規追加するレコードのコードと同じコードを持つレコードが存在し、有効期間が被る場合、例外をスローする。
            Date inputEndTime = userLoginInfo.getActiveEndTime();
            Date inputStartTime = userLoginInfo.getActiveStartTime();
            Date starTime = null;
            Date endTime = null;
            for (ImUserLoginInfo stvIdmfUserLoginInfo : stvIdmfUserLoginInfoList) {
                endTime = stvIdmfUserLoginInfo.getActiveEndTime();
                starTime = stvIdmfUserLoginInfo.getActiveStartTime();

                if (inputStartTime.getTime() > starTime.getTime()) {
                    if (endTime == null
                            || inputStartTime.getTime() < endTime.getTime()) {
                        throw new ImRuntimeException(
                                "There is a record with active start time or active end time covered.");
                    }
                } else {
                    if (inputEndTime == null
                            || inputEndTime.getTime() > starTime.getTime()) {
                        throw new ImRuntimeException(
                                "There is a record with active start time or active end time covered.");
                    }
                }
            }
            // 新規追加するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            userLoginInfo.setVersionNo(idmfUserLoginInfoMapper.selectMaxVersion(
                    userLoginInfo.getUserLoginInfoCode()) + 1);

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
        userLoginInfo.setDeletedFlg(Short.valueOf("0"));
        if (idmfUserLoginInfoMapper.insert(userLoginInfo) == 0) {
            throw new ImRuntimeException(
                    "Insert userLoginInfo data into DB with failed.");
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
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            userLoginInfo.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(userLoginInfo.getUpdatedUser())) {
                userLoginInfo.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            userLoginInfo.setVersionNo(idmfUserLoginInfoMapper.selectMaxVersion(
                    userLoginInfo.getUserLoginInfoCode()) + 1);
            userLoginInfo.setDeletedFlg(Short.valueOf("0"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userLoginInfo", userLoginInfo);
            map.put("versionBase", inputVersionNo);

            // 更新失敗
            if (idmfUserLoginInfoMapper.updateByPrimaryKey(map) == 0) {
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
                            + "] is not in IdmfuserLoginInfo.");
        }
        int inputVersionNo = userLoginInfo.getVersionNo();
        if (!selectResult.getVersionNo().equals(inputVersionNo)) {
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }

        userLoginInfo.setUpdatedTime(CommonUtils.getSystemTime());
        String updatedUser = userLoginInfo.getUpdatedUser();
        if (StringUtils.isEmpty(updatedUser)) {
            userLoginInfo.setUpdatedUser(CommonUtils.getLoginUser());
        }
        // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
        userLoginInfo.setVersionNo(idmfUserLoginInfoMapper
                .selectMaxVersion(selectResult.getUserLoginInfoCode()) + 1);
        userLoginInfo.setDeletedFlg(Short.valueOf("1"));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userLoginInfo", userLoginInfo);
        map.put("versionBase", inputVersionNo);

        // 論理削除失敗
        if (idmfUserLoginInfoMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete userLoginInfo data by logic from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceUserLoginInfo(
            ImUserLoginInfo userLoginInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        userLoginInfo.setUpdatedTime(CommonUtils.getSystemTime());
        userLoginInfo.setUpdatedUser(CommonUtils.getLoginUser());
        userLoginInfo.setVersionNo(userLoginInfo.getVersionNo() + 1);
        userLoginInfo.setDeletedFlg(Short.valueOf("1"));
        map.put("userLoginInfo", userLoginInfo);
        if (idmfUserLoginInfoMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete userLoginInfo data by logic from DB with failed.");
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
        return BaseDAO.HISTORY_TYPE_ON;
    }

}
