
package nz.co.identity.management.api.roleinfo.dao.impl;

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
import nz.co.identity.management.api.roleinfo.dao.ImRoleNameMstDAO;
import nz.co.identity.management.api.roleinfo.entity.ImRoleMst;
import nz.co.identity.management.api.roleinfo.entity.ImRoleNameMst;
import nz.co.identity.management.api.roleinfo.mapper.ImRoleMstMapper;
import nz.co.identity.management.api.roleinfo.mapper.ImRoleNameMstMapper;

/**
 *
 * the implementation class of roleName's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfRoleNameMstWithHistoryDAO")
public class ImRoleNameMstWithHistoryDAOImpl
        implements ImRoleNameMstDAO {

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
     * {@inheritDoc}
     */
    @Override
    public ImRoleNameMst registerRoleName(ImRoleNameMst roleNameMst) {

        ImRoleMst stvIdmfRoleMst = stvIdmfRoleMstMapper
                .selectByPrimaryKey(roleNameMst.getRoleId());
        // ロールIDがロールマスタに存在しない場合
        if (stvIdmfRoleMst == null) {
            throw new ImRecordInexistenceException("The roleId["
                    + roleNameMst.getRoleId() + "] is not in RoleMst.");
        }
        String locale = roleNameMst.getLocale();
        if (StringUtils.isEmpty(locale)) {
            locale = LocaleContextHolder.getLocale().toString();
        }
        ImRoleNameMst stvIdmfRoleNameMst = stvIdmfRoleNameMstMapper
                .selectByRoleIdByLocale(roleNameMst.getRoleId(), locale);
        if (stvIdmfRoleNameMst != null) {
            throw new ImRuntimeException(
                    "Insert role name data into DB with failed because of same locale.");
        } else {
            // ロールIDとロケールがロール名マスタに存在しない場合，登録処理(INSERT)

            // entityのidがnullの場合、自動採番してDBに登録
            if (StringUtils.isEmpty(roleNameMst.getRoleNameId())) {
                roleNameMst.setRoleNameId(serialGenerator
                        .selectSerial(ImRoleNameMst.SERIAL_TABLE));
            } else {

                // 重複するidを登録する場合に、例外をthrow
                if (stvIdmfRoleNameMstMapper.selectByPrimaryKey(
                        roleNameMst.getRoleNameId()) != null) {
                    throw new ImRuntimeException(
                            "The roleNameId[" + roleNameMst.getRoleNameId()
                                    + "] has been exists in RoleName.");
                }
            }

            if (StringUtils.isEmpty(roleNameMst.getCreatedUser())) {
                roleNameMst.setCreatedUser(CommonUtils.getLoginUser());
            }
            if (roleNameMst.getActiveStartTime() == null) {
                roleNameMst.setActiveStartTime(CommonUtils.getSystemTime());
            }
            roleNameMst.setCreatedTime(CommonUtils.getSystemTime());
            roleNameMst.setLocale(locale);
            roleNameMst.setUpdatedTime(null);
            roleNameMst.setUpdatedUser(null);
            roleNameMst.setVersionNo(0);
            roleNameMst.setDeletedFlg(Short.valueOf("0"));
            if (stvIdmfRoleNameMstMapper.insert(roleNameMst) == 0) {
                throw new ImRuntimeException(
                        "Insert role name data into DB with failed.");
            }
        }

        return roleNameMst;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleNameMst updateRoleName(ImRoleNameMst roleNameMst) {
        ImRoleMst idmfRoleMst = stvIdmfRoleMstMapper
                .selectByPrimaryKey(roleNameMst.getRoleId());
        // ロールIDがロールマスタに存在しない場合
        if (idmfRoleMst == null) {
            throw new ImRecordInexistenceException("The roleId["
                    + roleNameMst.getRoleId() + "] is not in RoleMst.");

        }

        // ロール名IDにより、バージョンがロール名マスタに存在しない場合
        ImRoleNameMst selectResult = stvIdmfRoleNameMstMapper
                .selectByPrimaryKey(roleNameMst.getRoleNameId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The RoleNameId["
                    + roleNameMst.getRoleNameId() + "] is not in RoleNameMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            Integer inputVersionNo = roleNameMst.getVersionNo();
            // ロール名IDにより、バージョンがロール名マスタに存在しない場合
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            roleNameMst.setUpdatedTime(CommonUtils.getSystemTime());
            String updatedUser = roleNameMst.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                roleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }

            roleNameMst.setVersionNo(roleNameMst.getVersionNo() + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleNameMst", roleNameMst);
            map.put("versionBase", inputVersionNo);
            if (stvIdmfRoleNameMstMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update role name data into DB with failed.");
            }
        }
        return roleNameMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRoleName(ImRoleNameMst roleNameMst) {

        String roleId = roleNameMst.getRoleId();
        String locale = roleNameMst.getLocale();

        // ロールIDで指定されたロールの名称をロール名マスタから削除する。
        Integer versionNo;
        if (StringUtils.isEmpty(locale)) {
            List<ImRoleNameMst> stvIdmfRoleNameMstList = stvIdmfRoleNameMstMapper
                    .selectByRoleId(roleId);
            // ロールIDがロール名マスタに存在しない場合
            if (CollectionUtils.isEmpty(stvIdmfRoleNameMstList)) {
                throw new ImRecordInexistenceException(
                        "The roleId[" + roleId + "] is not in RoleNameMst.");
            }

            for (ImRoleNameMst stvIdmfRoleNameMst : stvIdmfRoleNameMstList) {
                versionNo = stvIdmfRoleNameMst.getVersionNo();
                stvIdmfRoleNameMst.setUpdatedTime(CommonUtils.getSystemTime());
                stvIdmfRoleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
                stvIdmfRoleNameMst.setVersionNo(versionNo + 1);
                stvIdmfRoleNameMst.setDeletedFlg(Short.valueOf("1"));

                Map<String, Object> mapRoleName = new HashMap<String, Object>();
                mapRoleName.put("roleNameMst", stvIdmfRoleNameMst);
                mapRoleName.put("versionBase", versionNo);
                if (stvIdmfRoleNameMstMapper
                        .updateByPrimaryKeySelective(mapRoleName) == 0) {
                    throw new ImRuntimeException(
                            "Delete role name data by logic from DB with failed.");
                }
            }
        } else {
            // ロケールが指定されている場合は、当該ロケールの名称のみ削除する。
            ImRoleNameMst roleName = stvIdmfRoleNameMstMapper
                    .selectByRoleIdByLocale(roleId, locale);
            if (roleName == null) {
                throw new ImRecordInexistenceException(
                        "The roleId[" + roleId + "] and locale[" + locale
                                + "] is not in RoleNameMst.");
            }
            versionNo = roleName.getVersionNo();

            // バージョン 排他処理
            this.checkVersionNo(versionNo, roleNameMst.getVersionNo());

            roleName.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(roleName.getUpdatedUser())) {
                roleName.setUpdatedUser(CommonUtils.getLoginUser());
            }
            roleName.setVersionNo(versionNo + 1);
            roleName.setDeletedFlg(Short.valueOf("1"));
            roleName.setLocale("");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleNameMst", roleName);
            map.put("versionBase", versionNo);
            if (stvIdmfRoleNameMstMapper
                    .updateByPrimaryKeySelective(map) == 0) {
                throw new ImRuntimeException(
                        "Delete role name data by logic from DB with failed.");
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceRoleName(ImRoleNameMst roleNameMst) {
        roleNameMst.setUpdatedTime(CommonUtils.getSystemTime());
        roleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
        roleNameMst.setVersionNo(roleNameMst.getVersionNo() + 1);
        roleNameMst.setDeletedFlg(Short.valueOf("1"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleNameMst", roleNameMst);
        if (stvIdmfRoleNameMstMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete role name data by logic from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImRoleNameMst> getRoleName(String roleId) {
        return stvIdmfRoleNameMstMapper.selectByRoleId(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleNameMst getRoleNameById(String roleNameId) {
        return stvIdmfRoleNameMstMapper.selectByPrimaryKey(roleNameId);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImRoleNameMst> getRoleNameByRoleCode(String roleCode) {
        return stvIdmfRoleNameMstMapper.selectByRoleCode(roleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImRoleNameMst> searchRoleName(
            ImRoleNameMst roleNameMst, Integer pageNum, Integer pageSize,
            List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleNameMst", roleNameMst);
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(stvIdmfRoleNameMstMapper.selectCountByRoleNameMst(map)
                .longValue());
        // ページの初期化
        Page<ImRoleNameMst> page = new Page<ImRoleNameMst>(
                stvIdmfRoleNameMstMapper.selectByRoleNameMst(map), pageable);
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