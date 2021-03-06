
package nz.co.identity.management.api.roleinfo.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
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
import nz.co.identity.management.api.roleinfo.dao.ImPositionRoleDAO;
import nz.co.identity.management.api.roleinfo.dao.ImRoleMstDAO;
import nz.co.identity.management.api.roleinfo.dao.ImRoleNameMstDAO;
import nz.co.identity.management.api.roleinfo.entity.ImPositionRole;
import nz.co.identity.management.api.roleinfo.entity.ImRoleMst;
import nz.co.identity.management.api.roleinfo.entity.ImRoleNameMst;
import nz.co.identity.management.api.roleinfo.mapper.ImPositionRoleMapper;
import nz.co.identity.management.api.roleinfo.mapper.ImRoleMstMapper;
import nz.co.identity.management.api.roleinfo.mapper.ImRoleNameMstMapper;

/**
 *
 * the implementation class of role's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
public class ImRoleMstWithHistoryDAOImpl implements ImRoleMstDAO {

    /**
     * serial Generator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * IdmfRoleMst Mapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImRoleMstMapper stvIdmfRoleMstMapper;

    /**
     * IdmfRoleNameMst Mapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImRoleNameMstMapper stvIdmfRoleNameMstMapper;

    /**
     * IdmfPositionRole Mapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPositionRoleMapper stvIdmfPositionRoleMapper;

    /**
     * stvIdmfPositionRoleWithHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfPositionRoleWithHistoryDAO")
    private ImPositionRoleDAO stvIdmfPositionRoleWithHistoryDAO;

    /**
     * stvIdmfRoleNameMstWithHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfRoleNameMstWithHistoryDAO")
    private ImRoleNameMstDAO stvIdmfRoleNameMstWithHistoryDAO;

    /**
     * locale.enabled
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${locale.enabled:off}")
    private String localeEnabled;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImRoleMst registerRole(ImRoleMst roleMst) {

        // ロールコードチェック
        List<ImRoleMst> stvIdmfRoleMstList = stvIdmfRoleMstMapper
                .selectByRoleCode(roleMst.getRoleCode());
        if (CollectionUtils.isEmpty(stvIdmfRoleMstList)) {
            roleMst.setVersionNo(0);
        } else {
            // 新規追加するレコードのコードと同じコードを持つレコードが存在し、有効期間が被る場合、例外をスローする。
            Date roleEndTime = roleMst.getActiveEndTime();
            Date roleStartTime = roleMst.getActiveStartTime();
            Date endTime = null;
            Date starTime = null;
            for (ImRoleMst stvIdmfRoleMst : stvIdmfRoleMstList) {
                endTime = stvIdmfRoleMst.getActiveEndTime();
                starTime = stvIdmfRoleMst.getActiveStartTime();

                if (roleStartTime.getTime() > starTime.getTime()) {
                    if (endTime == null
                            || roleStartTime.getTime() < endTime.getTime()) {
                        throw new ImRuntimeException(
                                "There is a record with active start time or active end time covered.");
                    }
                } else {
                    if (roleEndTime == null
                            || roleEndTime.getTime() > starTime.getTime()) {
                        throw new ImRuntimeException(
                                "There is a record with active start time or active end time covered.");
                    }
                }
            }
            // 新規追加するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            roleMst.setVersionNo(
                    stvIdmfRoleMstMapper.selectMaxVersion(roleMst.getRoleCode())
                            + 1);
        }

        // entityのidがnullの場合、自動採番してDBに登録
        if (StringUtils.isEmpty(roleMst.getRoleId())) {
            roleMst.setRoleId(
                    serialGenerator.selectSerial(ImRoleMst.SERIAL_TABLE));
        } else {

            // 重複するidを登録する場合に、例外をthrow
            if (stvIdmfRoleMstMapper
                    .selectByPrimaryKey(roleMst.getRoleId()) != null) {
                throw new ImRuntimeException("The roleId["
                        + roleMst.getRoleId() + "] has been exists in Role.");
            }
        }

        roleMst.setCreatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(roleMst.getCreatedUser())) {
            roleMst.setCreatedUser(CommonUtils.getLoginUser());
        }
        if (roleMst.getActiveStartTime() == null) {
            roleMst.setActiveStartTime(CommonUtils.getSystemTime());
        }
        roleMst.setUpdatedTime(null);
        roleMst.setUpdatedUser(null);
        roleMst.setDeletedFlg(Short.valueOf("0"));
        if (stvIdmfRoleMstMapper.insert(roleMst) == 0) {
            throw new ImRuntimeException(
                    "Insert role data into DB with failed.");
        }

        return roleMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleMst updateRole(ImRoleMst roleMst) {

        // ロールIDがロールマスタに存在しない場合
        ImRoleMst selectResult = stvIdmfRoleMstMapper
                .selectByPrimaryKey(roleMst.getRoleId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The roleId["
                    + roleMst.getRoleId() + "] is not in RoleMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            // 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
            String code = roleMst.getRoleCode();
            if (code != null && !selectResult.getRoleCode().equals(code)) {
                throw new ImRecordInexistenceException(
                        "There is a record with disaccorded code[" + code
                                + "]");
            }
            Integer inputVersionNo = roleMst.getVersionNo();
            // ロールコードにより、バージョン 排他処理
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                // ロールコードにより、指定したバージョンがバージョンがロールマスタに存在しない場合
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            roleMst.setUpdatedTime(CommonUtils.getSystemTime());
            String updatedUser = roleMst.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                roleMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            roleMst.setVersionNo(
                    stvIdmfRoleMstMapper.selectMaxVersion(roleMst.getRoleCode())
                            + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleMst", roleMst);
            map.put("versionBase", inputVersionNo);

            // 更新失敗
            if (stvIdmfRoleMstMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update role data into DB with failed.");
            }
        }

        return roleMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRole(ImRoleMst roleMst) {
        ImRoleMst stvIdmfRoleMst = stvIdmfRoleMstMapper
                .selectByPrimaryKey(roleMst.getRoleId());

        // ロールIDがロールマスタに存在しない場合
        if (stvIdmfRoleMst == null) {
            throw new ImRecordInexistenceException("The roleId["
                    + roleMst.getRoleId() + "] is not in RoleMst.");
        }
        // ロール名マスタ、ポジション_ロールに該当するロールIDが存在する場合
        List<ImRoleNameMst> stvIdmfRoleNameMstList = stvIdmfRoleNameMstMapper
                .selectByRoleId(roleMst.getRoleId());
        List<ImPositionRole> stvIdmfPositionRoleList = stvIdmfPositionRoleMapper
                .selectByRoleId(roleMst.getRoleId());
        if ((!CollectionUtils.isEmpty(stvIdmfRoleNameMstList))
                || (!CollectionUtils.isEmpty(stvIdmfPositionRoleList))) {
            throw new ImRecordInexistenceException(
                    "The roleId[" + roleMst.getRoleId()
                            + "] is in RoleNameMst or PositionRole.");
        }

        // ロールコードにより、バージョン 排他処理
        Integer inputVersionNo = roleMst.getVersionNo();
        if (!stvIdmfRoleMst.getVersionNo().equals(inputVersionNo)) {
            // ポジションIDにより、指定したバージョンがポジションに存在しない場合
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }

        roleMst.setUpdatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(roleMst.getUpdatedUser())) {
            roleMst.setUpdatedUser(CommonUtils.getLoginUser());
        }
        // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
        roleMst.setVersionNo(stvIdmfRoleMstMapper
                .selectMaxVersion(stvIdmfRoleMst.getRoleCode()) + 1);
        roleMst.setDeletedFlg(Short.valueOf("1"));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleMst", roleMst);
        map.put("versionBase", inputVersionNo);

        // 論理削除失敗
        if (stvIdmfRoleMstMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete role data by logic from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceRole(ImRoleMst roleMst) {
        // ロールIDがロールマスタに存在しない場合
        if (stvIdmfRoleMstMapper
                .selectByPrimaryKey(roleMst.getRoleId()) == null) {
            throw new ImRecordInexistenceException("The roleId["
                    + roleMst.getRoleId() + "] is not in RoleMst.");
        }

        // ロール名マスタ、ポジション_ロールに該当するロールIDが存在する場合、入力ロールIDにより、ロールマスタと対応するロール名マスタ、ポジション_ロールの情報を物理削除する。
        List<ImRoleNameMst> stvIdmfRoleNameMstList = stvIdmfRoleNameMstMapper
                .selectByRoleId(roleMst.getRoleId());
        // ロール名マスタ
        if (!CollectionUtils.isEmpty(stvIdmfRoleNameMstList)) {
            for (ImRoleNameMst stvIdmfRoleNameMst : stvIdmfRoleNameMstList) {
                if (!stvIdmfRoleNameMstWithHistoryDAO
                        .deleteForceRoleName(stvIdmfRoleNameMst)) {
                    throw new ImRuntimeException(
                            "Delete role name data by logic from DB with failed.");
                }
            }
        }
        List<ImPositionRole> stvIdmfPositionRoleList = stvIdmfPositionRoleMapper
                .selectByRoleId(roleMst.getRoleId());
        // ポジション_ロールマスタ
        if (!CollectionUtils.isEmpty(stvIdmfPositionRoleList)) {
            for (ImPositionRole stvIdmfPositionRole : stvIdmfPositionRoleList) {
                if (!stvIdmfPositionRoleWithHistoryDAO
                        .deleteForcePositionRole(stvIdmfPositionRole)) {
                    throw new ImRuntimeException(
                            "Delete position role data by logic from DB with failed.");
                }
            }
        }

        roleMst.setUpdatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(roleMst.getUpdatedUser())) {
            roleMst.setUpdatedUser(CommonUtils.getLoginUser());
        }
        // 更新するレコードのバージョン ＋1 の値を、バージョンに設定する。
        roleMst.setVersionNo(roleMst.getVersionNo() + 1);
        roleMst.setDeletedFlg(Short.valueOf("1"));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleMst", roleMst);

        // 論理削除失敗
        if (stvIdmfRoleMstMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete role data by logic from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleMst getRole(String roleId) {

        // 利用ロケールが指定されている場合
        ImRoleMst stvIdmfRoleMst = stvIdmfRoleMstMapper
                .selectByRoleIdByLocale(roleId,
                        LocaleContextHolder.getLocale().toString());
        if (stvIdmfRoleMst == null) {
            // 利用ロケールが指定されていない場合
            stvIdmfRoleMst = stvIdmfRoleMstMapper.selectByPrimaryKey(roleId);
        }
        return stvIdmfRoleMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleMst getRoleByRoleCode(String roleCode) {

        // 利用ロケールが指定されている場合
        List<ImRoleMst> stvIdmfRoleMstList = stvIdmfRoleMstMapper
                .selectByRoleCodeByLocale(roleCode,
                        LocaleContextHolder.getLocale().toString());
        if (CollectionUtils.isEmpty(stvIdmfRoleMstList)) {
            // 利用ロケールが指定されていない場合
            stvIdmfRoleMstList = stvIdmfRoleMstMapper
                    .selectByRoleCode(roleCode);
        }
        // 検索失敗
        if (CollectionUtils.isEmpty(stvIdmfRoleMstList)) {
            return null;
        }
        return stvIdmfRoleMstList.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImRoleMst> searchRole(ImRoleMst roleMst,
            Integer pageNum, Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleMst", roleMst);
        map.put("locale", LocaleContextHolder.getLocale().toString());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        List<ImRoleMst> stvIdmfRoleMstList = null;
        if (localeEnabled.equals("on")) {
            // 全件件数を設定
            pageable.setTotal(stvIdmfRoleMstMapper
                    .selectCountByRoleMstByLocale(map).longValue());
            // 利用ロケールが指定されている場合
            stvIdmfRoleMstList = stvIdmfRoleMstMapper
                    .selectByRoleMstByLocale(map);
        }
        if (CollectionUtils.isEmpty(stvIdmfRoleMstList)) {

            // 全件件数を設定
            pageable.setTotal(
                    stvIdmfRoleMstMapper.selectCountByRoleMst(map).longValue());
            // 利用ロケールが指定されていない場合
            stvIdmfRoleMstList = stvIdmfRoleMstMapper.selectByRoleMst(map);
        }
        // ページの初期化
        Page<ImRoleMst> page = new Page<ImRoleMst>(stvIdmfRoleMstList,
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