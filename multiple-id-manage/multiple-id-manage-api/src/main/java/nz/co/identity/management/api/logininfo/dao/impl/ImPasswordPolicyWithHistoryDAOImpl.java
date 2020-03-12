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
import nz.co.identity.management.api.logininfo.dao.ImPasswordPolicyDAO;
import nz.co.identity.management.api.logininfo.entity.ImPasswordPolicy;
import nz.co.identity.management.api.logininfo.mapper.ImPasswordPolicyMapper;

/**
 *
 * the implementation class of password policy's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
public class ImPasswordPolicyWithHistoryDAOImpl
        implements ImPasswordPolicyDAO {

    /**
     * serial Generator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * idmfPasswordPolicyMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordPolicyMapper idmfPasswordPolicyMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy registerPasswordPolicy(
            ImPasswordPolicy passwordPolicy) {

        List<ImPasswordPolicy> stvIdmfPasswordPolicyList = idmfPasswordPolicyMapper
                .selectByPasswordPolicyCode(
                        passwordPolicy.getPasswordPolicyCode());
        if (CollectionUtils.isEmpty(stvIdmfPasswordPolicyList)) {
            passwordPolicy.setVersionNo(0);
        } else {
            // 新規追加するレコードのコードと同じコードを持つレコードが存在し、有効期間が被る場合、例外をスローする。
            Date inputEndTime = passwordPolicy.getActiveEndTime();
            Date inputStartTime = passwordPolicy.getActiveStartTime();
            Date starTime = null;
            Date endTime = null;
            for (ImPasswordPolicy stvIdmfPasswordPolicy : stvIdmfPasswordPolicyList) {
                endTime = stvIdmfPasswordPolicy.getActiveEndTime();
                starTime = stvIdmfPasswordPolicy.getActiveStartTime();
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
            passwordPolicy
                    .setVersionNo(idmfPasswordPolicyMapper.selectMaxVersion(
                            passwordPolicy.getPasswordPolicyCode()) + 1);

        }

        // entityのidがnullの場合、自動採番してDBに登録
        if (StringUtils.isEmpty(passwordPolicy.getPasswordPolicyId())) {
            passwordPolicy.setPasswordPolicyId(serialGenerator
                    .selectSerial(ImPasswordPolicy.SERIAL_TABLE));
        } else {

            // 重複するidを登録する場合に、例外をthrow
            if (idmfPasswordPolicyMapper.selectByPrimaryKey(
                    passwordPolicy.getPasswordPolicyId()) != null) {
                throw new ImRuntimeException("The passwordPolicyId["
                        + passwordPolicy.getPasswordPolicyId()
                        + "] has been exists in PasswordPolicy.");
            }
        }

        passwordPolicy.setCreatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(passwordPolicy.getCreatedUser())) {
            passwordPolicy.setCreatedUser(CommonUtils.getLoginUser());
        }
        if (passwordPolicy.getActiveStartTime() == null) {
            passwordPolicy.setActiveStartTime(CommonUtils.getSystemTime());
        }
        passwordPolicy.setUpdatedTime(null);
        passwordPolicy.setUpdatedUser(null);
        passwordPolicy.setDeletedFlg(Short.valueOf("0"));
        if (idmfPasswordPolicyMapper.insert(passwordPolicy) == 0) {
            throw new ImRuntimeException(
                    "Insert passwordPolicy data into DB with failed.");
        }
        return passwordPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy updatePasswordPolicy(
            ImPasswordPolicy passwordPolicy) {

        ImPasswordPolicy selectResult = idmfPasswordPolicyMapper
                .selectByPrimaryKey(passwordPolicy.getPasswordPolicyId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The passwordPolicyId["
                            + passwordPolicy.getPasswordPolicyId()
                            + "] is not in IdmfPasswordPolicy.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            // 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
            String code = passwordPolicy.getPasswordPolicyCode();
            if (code != null
                    && !selectResult.getPasswordPolicyCode().equals(code)) {
                throw new ImRecordInexistenceException(
                        "There is a record with disaccorded code[" + code
                                + "]");
            }
            Integer inputVersionNo = passwordPolicy.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            passwordPolicy.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(passwordPolicy.getUpdatedUser())) {
                passwordPolicy.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            int version = idmfPasswordPolicyMapper
                    .selectMaxVersion(passwordPolicy.getPasswordPolicyCode());
            passwordPolicy.setVersionNo(version + 1);
            passwordPolicy.setDeletedFlg(Short.valueOf("0"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("passwordPolicy", passwordPolicy);
            map.put("versionBase", inputVersionNo);

            // 更新失敗
            if (idmfPasswordPolicyMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update passwordPolicy data into DB with failed.");
            }
        }

        return passwordPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePasswordPolicy(ImPasswordPolicy passwordPolicy) {
        ImPasswordPolicy selectResult = idmfPasswordPolicyMapper
                .selectByPrimaryKey(passwordPolicy.getPasswordPolicyId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The passwordPolicyId["
                            + passwordPolicy.getPasswordPolicyId()
                            + "] is not in IdmfPasswordPolicy.");
        }
        Integer inputVersionNo = passwordPolicy.getVersionNo();
        if (!selectResult.getVersionNo().equals(inputVersionNo)) {
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }

        passwordPolicy.setUpdatedTime(CommonUtils.getSystemTime());
        String updatedUser = passwordPolicy.getUpdatedUser();
        if (StringUtils.isEmpty(updatedUser)) {
            passwordPolicy.setUpdatedUser(CommonUtils.getLoginUser());
        }
        // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
        passwordPolicy.setVersionNo(idmfPasswordPolicyMapper
                .selectMaxVersion(selectResult.getPasswordPolicyCode()) + 1);
        passwordPolicy.setDeletedFlg(Short.valueOf("1"));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("passwordPolicy", passwordPolicy);
        map.put("versionBase", inputVersionNo);

        int deleteFlg = idmfPasswordPolicyMapper
                .updateByPrimaryKeySelective(map);
        // 論理削除失敗
        if (deleteFlg == 0) {
            throw new ImRuntimeException(
                    "Delete passwordPolicy data by logic from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy getPasswordPolicy(String passwordPolicyId) {
        return idmfPasswordPolicyMapper.selectByPrimaryKey(passwordPolicyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPasswordPolicy getPasswordPolicyByCode(
            String passwordPolicyCode) {
        List<ImPasswordPolicy> result = idmfPasswordPolicyMapper
                .selectByPasswordPolicyCode(passwordPolicyCode);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPasswordPolicy> searchPasswordPolicy(
            ImPasswordPolicy passwordPolicy, Integer pageNum,
            Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("passwordPolicy", passwordPolicy);
        map.put("pageSize", pageable.getPageSize());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(idmfPasswordPolicyMapper
                .selectCountByPasswordPolicy(map).longValue());
        // ページの初期化
        Page<ImPasswordPolicy> page = new Page<ImPasswordPolicy>(
                idmfPasswordPolicyMapper.selectPasswordPolicy(map), pageable);
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
