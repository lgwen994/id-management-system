package nz.co.identity.management.api.orguserinfo.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.i18n.LocaleContextHolder;
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
import nz.co.identity.management.api.orguserinfo.dao.ImUserNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImUserMst;
import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImUserMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImUserNameMstMapper;

/**
 * Get user name information by access DB.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfUserNameWithHistoryDAO")
public class ImUserNameWithHistoryDAOImpl
        implements ImUserNameMstDAO {

    /**
     * serial Generator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * idmfTitleMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserMstMapper idmfUserMstMapper;

    /**
     * IdmfTitleNameMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserNameMstMapper idmfUserNameMstMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserNameMst registerUserName(ImUserNameMst userNameMst) {
        // ユーザIDがユーザマスタに存在しない場合
        if (idmfUserMstMapper
                .selectByPrimaryKey(userNameMst.getUserId()) == null) {
            throw new ImRecordInexistenceException("The userId["
                    + userNameMst.getUserId() + "] is not in UserMst.");
        }
        String locale = userNameMst.getLocale();
        if (StringUtils.isEmpty(locale)) {
            locale = LocaleContextHolder.getLocale().toString();
        }
        // ロールIDとロケールがロール名マスタに存在する場合，登録済みの異常とする
        ImUserNameMst stvIdmfUserNameMst = idmfUserNameMstMapper
                .selectByUserIdByLocale(userNameMst.getUserId(), locale);
        if (stvIdmfUserNameMst != null) {
            throw new ImRuntimeException(
                    "Insert user name data into DB with failed because of same locale.");
        } else {

            // ロールIDとロケールがロール名マスタに存在しない場合，登録処理(INSERT)

            // entityのidがnullの場合、自動採番してDBに登録
            if (StringUtils.isEmpty(userNameMst.getUserNameId())) {
                userNameMst.setUserNameId(serialGenerator
                        .selectSerial(ImUserNameMst.SERIAL_TABLE));
            } else {

                // 重複するidを登録する場合に、例外をthrow
                if (idmfUserNameMstMapper.selectByPrimaryKey(
                        userNameMst.getUserNameId()) != null) {
                    throw new ImRuntimeException(
                            "The userNameId[" + userNameMst.getUserNameId()
                                    + "] has been exists in UserName.");
                }
            }

            if (StringUtils.isEmpty(userNameMst.getCreatedUser())) {
                userNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            if (userNameMst.getActiveStartTime() == null) {
                userNameMst.setActiveStartTime(CommonUtils.getSystemTime());
            }
            userNameMst.setCreatedTime(CommonUtils.getSystemTime());
            userNameMst.setVersionNo(0);
            userNameMst.setDeletedFlg(Short.valueOf("0"));
            userNameMst.setLocale(locale);
            userNameMst.setUpdatedTime(null);
            userNameMst.setUpdatedUser(null);

            if (idmfUserNameMstMapper.insert(userNameMst) == 0) {
                throw new ImRuntimeException(
                        "Insert user name data into DB with failed.");
            }
        }

        return userNameMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserNameMst updateUserName(ImUserNameMst userNameMst) {
        ImUserMst stvIdmfUserMst = idmfUserMstMapper
                .selectByPrimaryKey(userNameMst.getUserId());
        // ユーザIDがユーザマスタに存在しない場合
        if (stvIdmfUserMst == null) {
            throw new ImRecordInexistenceException("The userId["
                    + userNameMst.getUserId() + "] is not in UserMst.");
        }
        // ユーザ名IDにより、バージョンがユーザ名マスタに存在しない場合
        ImUserNameMst selectResult = idmfUserNameMstMapper
                .selectByPrimaryKey(userNameMst.getUserNameId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The UserNameId["
                    + userNameMst.getUserNameId() + "] is not in userNameMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            Integer inputVersionNo = userNameMst.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            userNameMst.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(userNameMst.getUpdatedUser())) {
                userNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }

            userNameMst.setVersionNo(userNameMst.getVersionNo() + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userNameMst", userNameMst);
            map.put("versionBase", inputVersionNo);
            int updateFlg = idmfUserNameMstMapper.updateByPrimaryKey(map);
            if (updateFlg == 0) {
                throw new ImRuntimeException(
                        "Update user name data into DB with failed.");
            }
        }

        return userNameMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserName(ImUserNameMst userNameMst) {
        int versionNo = 0;
        String locale = userNameMst.getLocale();
        // ユーザIDで指定されたロールの名称をユーザ名マスタから削除する。
        if (StringUtils.isEmpty(locale)) {
            List<ImUserNameMst> stvIdmfUserNameMstList = idmfUserNameMstMapper
                    .selectByUserId(userNameMst.getUserId());
            // 役職IDが役職名マスタに存在しない場合
            if (CollectionUtils.isEmpty(stvIdmfUserNameMstList)) {
                throw new ImRecordInexistenceException("The userId["
                        + userNameMst.getUserId() + "] is not in userName.");
            }
            for (ImUserNameMst stvIdmfUserNameMst : stvIdmfUserNameMstList) {
                versionNo = stvIdmfUserNameMst.getVersionNo();
                stvIdmfUserNameMst.setUpdatedTime(CommonUtils.getSystemTime());
                stvIdmfUserNameMst.setUpdatedUser(CommonUtils.getLoginUser());
                stvIdmfUserNameMst
                        .setVersionNo(stvIdmfUserNameMst.getVersionNo() + 1);
                stvIdmfUserNameMst.setDeletedFlg(Short.valueOf("1"));

                Map<String, Object> mapuserName = new HashMap<String, Object>();
                mapuserName.put("userNameMst", stvIdmfUserNameMst);
                mapuserName.put("versionBase", versionNo);
                if (idmfUserNameMstMapper
                        .updateByPrimaryKeySelective(mapuserName) == 0) {
                    throw new ImRuntimeException(
                            "Delete user name data by logic from DB with failed.");
                }
            }
        } else {
            // ロケールが指定されている場合は、当該ロケールの名称のみ削除する。
            ImUserNameMst userName = idmfUserNameMstMapper
                    .selectByUserIdByLocale(userNameMst.getUserId(), locale);
            if (userName == null) {
                throw new ImRecordInexistenceException("The userId["
                        + userNameMst.getUserId() + "] and locale[" + locale
                        + "] is not in userName.");
            }
            versionNo = userName.getVersionNo();

            // バージョン 排他処理
            this.checkVersionNo(versionNo, userNameMst.getVersionNo());

            userName.setUpdatedTime(CommonUtils.getSystemTime());

            String updatedUser = userName.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                userName.setUpdatedUser(CommonUtils.getLoginUser());
            }
            userName.setVersionNo(versionNo + 1);
            userName.setDeletedFlg(Short.valueOf("1"));
            userName.setLocale("");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userNameMst", userName);
            map.put("versionBase", versionNo);
            if (idmfUserNameMstMapper.updateByPrimaryKeySelective(map) == 0) {
                throw new ImRuntimeException(
                        "Delete user name data by logic from DB with failed.");
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceUserName(ImUserNameMst userNameMst) {

        userNameMst.setUpdatedTime(CommonUtils.getSystemTime());
        userNameMst.setUpdatedUser(CommonUtils.getLoginUser());
        userNameMst.setVersionNo(userNameMst.getVersionNo() + 1);
        userNameMst.setDeletedFlg(Short.valueOf("1"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNameMst", userNameMst);

        if (idmfUserNameMstMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete user name data by logic from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImUserNameMst> getUserName(String userId) {
        return idmfUserNameMstMapper.selectByUserId(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ImUserNameMst getUserNameById(String userNameId) {
        return idmfUserNameMstMapper.selectByPrimaryKey(userNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImUserNameMst> getUserNameByUserCode(String userCode) {
        return idmfUserNameMstMapper.selectByUserCode(userCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImUserNameMst> searchUserName(
            ImUserNameMst userNameMst, Integer pageNum, Integer pageSize,
            List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNameMst", userNameMst);
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(idmfUserNameMstMapper.selectCountByUserNameMst(map)
                .longValue());
        // ページの初期化
        Page<ImUserNameMst> page = new Page<ImUserNameMst>(
                idmfUserNameMstMapper.selectByUserNameMst(map), pageable);
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
