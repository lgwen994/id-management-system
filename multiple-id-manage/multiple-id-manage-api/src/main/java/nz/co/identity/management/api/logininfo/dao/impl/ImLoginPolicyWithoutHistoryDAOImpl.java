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
import nz.co.identity.management.api.logininfo.dao.ImLoginPolicyDAO;
import nz.co.identity.management.api.logininfo.entity.ImLoginPolicy;
import nz.co.identity.management.api.logininfo.mapper.ImLoginPolicyMapper;

/**
 *
 * the implementation class of login policy's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
public class ImLoginPolicyWithoutHistoryDAOImpl
        implements ImLoginPolicyDAO {

    /**
     * serial Generator
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * idmfLoginPolicyMapper
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImLoginPolicyMapper idmfLoginPolicyMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy registerLoginPolicy(
            ImLoginPolicy loginPolicy) {

        // entityのidがnullの場合、自動採番してDBに登録
        if (StringUtils.isEmpty(loginPolicy.getLoginPolicyId())) {
            loginPolicy.setLoginPolicyId(serialGenerator
                    .selectSerial(ImLoginPolicy.SERIAL_TABLE));
        } else {

            // 重複するidを登録する場合に、例外をthrow
            if (idmfLoginPolicyMapper.selectByPrimaryKey(
                    loginPolicy.getLoginPolicyId()) != null) {
                throw new ImRuntimeException(
                        "The loginPolicyId[" + loginPolicy.getLoginPolicyId()
                                + "] has been exists in LoginPolicy.");
            }
        }

        loginPolicy.setCreatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(loginPolicy.getCreatedUser())) {
            loginPolicy.setCreatedUser(CommonUtils.getLoginUser());
        }
        if (loginPolicy.getActiveStartTime() == null) {
            loginPolicy.setActiveStartTime(CommonUtils.getSystemTime());
        }
        loginPolicy.setUpdatedTime(null);
        loginPolicy.setUpdatedUser(null);

        List<ImLoginPolicy> selectResult = idmfLoginPolicyMapper
                .selectByLoginPolicyCode(loginPolicy.getLoginPolicyCode());
        if (CollectionUtils.isEmpty(selectResult)) {
            loginPolicy.setVersionNo(0);
            loginPolicy.setDeletedFlg(Short.valueOf("0"));
            if (idmfLoginPolicyMapper.insert(loginPolicy) == 0) {
                throw new ImRuntimeException(
                        "Insert loginPolicy data into DB with failed.");
            }
        } else {
            throw new ImRuntimeException("The same code with failed.");
        }

        return loginPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy updateLoginPolicy(
            ImLoginPolicy loginPolicy) {
        ImLoginPolicy selectResult = idmfLoginPolicyMapper
                .selectByPrimaryKey(loginPolicy.getLoginPolicyId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The loginPolicyId["
                    + loginPolicy.getLoginPolicyId() + "] is not in LoginPolicy.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            // 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
            String code = loginPolicy.getLoginPolicyCode();
            if (code != null
                    && !selectResult.getLoginPolicyCode().equals(code)) {
                throw new ImRecordInexistenceException(
                        "There is a record with disaccorded code[" + code
                                + "]");
            }
            Integer inputVersionNo = loginPolicy.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            loginPolicy.setUpdatedTime(CommonUtils.getSystemTime());
            String updatedUser = loginPolicy.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                loginPolicy.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            loginPolicy.setVersionNo(idmfLoginPolicyMapper
                    .selectMaxVersion(loginPolicy.getLoginPolicyCode()) + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("loginPolicy", loginPolicy);
            map.put("versionBase", inputVersionNo);

            // 更新失敗
            if (idmfLoginPolicyMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update loginPolicy data into DB with failed.");
            }
        }

        return loginPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteLoginPolicy(ImLoginPolicy loginPolicy) {
        String loginPolicyId = loginPolicy.getLoginPolicyId();
        ImLoginPolicy selectResult = idmfLoginPolicyMapper
                .selectByPrimaryKey(loginPolicyId);
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The loginPolicyId[" + loginPolicy.getLoginPolicyId()
                            + "] is not in LoginPolicy.");
        }
        Integer inputVersionNo = loginPolicy.getVersionNo();
        if (!selectResult.getVersionNo().equals(inputVersionNo)) {
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }

        // 削除失敗
        if (idmfLoginPolicyMapper.deleteByLoginPolicyIdByVersion(loginPolicyId,
                inputVersionNo) == 0) {
            throw new ImRuntimeException(
                    "Delete loginPolicy data from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy getLoginPolicy(String loginPolicyId) {
        return idmfLoginPolicyMapper.selectByPrimaryKey(loginPolicyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImLoginPolicy getLoginPolicyByCode(String loginPolicyCode) {

        List<ImLoginPolicy> result = idmfLoginPolicyMapper
                .selectByLoginPolicyCode(loginPolicyCode);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImLoginPolicy> searchLoginPolicy(
            ImLoginPolicy loginPolicy, Integer pageNum, Integer pageSize,
            List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginPolicy", loginPolicy);
        map.put("pageSize", pageable.getPageSize());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(idmfLoginPolicyMapper.selectCountByLoginPolicy(map)
                .longValue());
        // ページの初期化
        Page<ImLoginPolicy> page = new Page<ImLoginPolicy>(
                idmfLoginPolicyMapper.selectLoginPolicy(map), pageable);
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
