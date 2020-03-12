
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
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.orguserinfo.mapper.ImPositionMapper;
import nz.co.identity.management.api.roleinfo.dao.ImPositionRoleDAO;
import nz.co.identity.management.api.roleinfo.data.ImRoleAndPosition;
import nz.co.identity.management.api.roleinfo.entity.ImPositionRole;
import nz.co.identity.management.api.roleinfo.mapper.ImPositionRoleMapper;
import nz.co.identity.management.api.roleinfo.mapper.ImRoleMstMapper;

/**
 *
 * the implementation class of positionRole's DAO interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfPositionRoleWithoutHistoryDAO")
public class ImPositionRoleWithoutHistoryDAOImpl
        implements ImPositionRoleDAO {

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
     * IdmfPosition Mapper
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPositionMapper stvIdmfPositionMapper;

    /**
     * IdmfPositionRole Mapper
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPositionRoleMapper stvIdmfPositionRoleMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole registerPositionRole(
            ImPositionRole positionRole) {
        // ポジションIDがポジションに存在しない場合
        if (stvIdmfPositionMapper
                .selectByPrimaryKey(positionRole.getPositionId()) == null) {
            throw new ImRecordInexistenceException("The positionId["
                    + positionRole.getPositionId() + "] is not in Position.");
        }
        // ロールIDがロールに存在しない場合
        if (stvIdmfRoleMstMapper
                .selectByPrimaryKey(positionRole.getRoleId()) == null) {
            throw new ImRecordInexistenceException("The roleId["
                    + positionRole.getRoleId() + "] is not in RoleMst.");
        }

        // entityのidがnullの場合、自動採番してDBに登録
        if (StringUtils.isEmpty(positionRole.getPositionRoleId())) {
            positionRole.setPositionRoleId(serialGenerator
                    .selectSerial(ImPositionRole.SERIAL_TABLE));
        } else {

            // 重複するidを登録する場合に、例外をthrow
            if (stvIdmfPositionRoleMapper.selectByPrimaryKey(
                    positionRole.getPositionRoleId()) != null) {
                throw new ImRuntimeException(
                        "The positionRoleId[" + positionRole.getPositionRoleId()
                                + "] has been exists in PositionRole.");
            }
        }

        positionRole.setCreatedTime(CommonUtils.getSystemTime());

        if (StringUtils.isEmpty(positionRole.getCreatedUser())) {
            positionRole.setCreatedUser(CommonUtils.getLoginUser());
        }
        if (positionRole.getActiveStartTime() == null) {
            positionRole.setActiveStartTime(CommonUtils.getSystemTime());
        }
        positionRole.setUpdatedTime(null);
        positionRole.setUpdatedUser(null);

        List<ImPositionRole> stvIdmfPositionRoleList = stvIdmfPositionRoleMapper
                .selectByPositionRoleCode(positionRole.getPositionRoleCode());
        if (CollectionUtils.isEmpty(stvIdmfPositionRoleList)) {
            positionRole.setVersionNo(0);
            positionRole.setDeletedFlg(Short.valueOf("0"));
            if (stvIdmfPositionRoleMapper.insert(positionRole) == 0) {
                throw new ImRuntimeException(
                        "Insert position role data into DB with failed.");
            }
        } else {
            throw new ImRuntimeException("The same code with failed.");
        }

        return positionRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole updatePositionRole(
            ImPositionRole positionRole) {
        ImPositionRole selectResult = stvIdmfPositionRoleMapper
                .selectByPrimaryKey(positionRole.getPositionRoleId());
        // ポジションIDがポジションに存在しない場合
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The positionId[" + positionRole.getPositionId()
                            + "] is not in PositionRole.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            // ポジションIDがポジションに存在しない場合
            if (stvIdmfPositionMapper
                    .selectByPrimaryKey(positionRole.getPositionId()) == null) {
                throw new ImRecordInexistenceException(
                        "The positionId[" + positionRole.getPositionId()
                                + "] is not in Position.");
            }
            // ロールIDがロールに存在しない場合
            if (stvIdmfRoleMstMapper
                    .selectByPrimaryKey(positionRole.getRoleId()) == null) {
                throw new ImRecordInexistenceException("The roleId["
                        + positionRole.getRoleId() + "] is not in RoleMst.");
            }

            // 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
            String code = positionRole.getPositionRoleCode();
            if (code != null
                    && !selectResult.getPositionRoleCode().equals(code)) {
                throw new ImRecordInexistenceException(
                        "There is a record with disaccorded code[" + code
                                + "]");
            }
            int inputVersionNo = positionRole.getVersionNo();
            // ポジション_ロールコードにより、指定したバージョンがポジション_ロールに存在しない場合
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            positionRole.setUpdatedTime(CommonUtils.getSystemTime());

            String updatedUser = positionRole.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                positionRole.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            positionRole.setVersionNo(stvIdmfPositionRoleMapper
                    .selectMaxVersion(positionRole.getPositionRoleCode()) + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("positionRole", positionRole);
            map.put("versionBase", inputVersionNo);
            if (stvIdmfPositionRoleMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update position role data into DB with failed.");
            }
        }

        return positionRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePositionRole(ImPositionRole positionRole) {
        ImPositionRole selectResult = stvIdmfPositionRoleMapper
                .selectByPrimaryKey(positionRole.getPositionRoleId());
        // ポジションIDがポジションに存在しない場合
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The positionId[" + positionRole.getPositionRoleId()
                            + "] is not in PositionRole.");
        }
        // ポジションIDにより、バージョン 排他処理
        Integer inputVersionNo = positionRole.getVersionNo();
        if (!selectResult.getVersionNo().equals(inputVersionNo)) {
            // ポジションIDにより、指定したバージョンがポジションに存在しない場合
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }

        if (stvIdmfPositionRoleMapper
                .deleteByPositionRoleIdByVersion(positionRole) == 0) {
            throw new ImRuntimeException(
                    "Delete position role data from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForcePositionRole(ImPositionRole positionRole) {
        if (stvIdmfPositionRoleMapper
                .deleteByRoleId(positionRole.getRoleId()) == 0) {
            throw new ImRuntimeException(
                    "Delete positionRole data from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole getPositionRole(String positionRoleId) {
        return stvIdmfPositionRoleMapper.selectByPrimaryKey(positionRoleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole getPositionRoleByPositionRoleCode(
            String positionRoleCode) {
        List<ImPositionRole> stvIdmfPositionRoleList = stvIdmfPositionRoleMapper
                .selectByPositionRoleCode(positionRoleCode);
        if (CollectionUtils.isEmpty(stvIdmfPositionRoleList)) {
            return null;
        }

        return stvIdmfPositionRoleList.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPositionRole> searchPositionRole(
            ImPositionRole positionRole, Integer pageNum, Integer pageSize,
            List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("positionRole", positionRole);
        map.put("pageSize", pageable.getPageSize());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(stvIdmfPositionRoleMapper
                .selectCountByPositionRole(map).longValue());
        // ページの初期化
        Page<ImPositionRole> page = new Page<ImPositionRole>(
                stvIdmfPositionRoleMapper.selectPositionRole(map), pageable);
        // ページ情報
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImRoleAndPosition> searchRole(ImPosition position,
            Integer pageNum, Integer pageSize, List<String> sort) {
        Map<String, Object> map = new HashMap<String, Object>();

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 利用ロケ－ルの値は内部API(LocaleContextHolder)から取得する。
        map.put("position", position);
        map.put("locale", LocaleContextHolder.getLocale().toString());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageSize);
        map.put("sort", CommonUtils.makeOrders(sort));
        List<ImRoleAndPosition> stvIdmfRoleAndPositionList = stvIdmfPositionRoleMapper
                .selectRoleByPositionByLocale(map);
        // 全件件数を設定
        pageable.setTotal(stvIdmfPositionRoleMapper
                .selectRoleCountByPositionByLocale(map).longValue());
        if (CollectionUtils.isEmpty(stvIdmfRoleAndPositionList)) {
            // 利用ロケールが指定されていない場合
            stvIdmfRoleAndPositionList = stvIdmfPositionRoleMapper
                    .selectRoleByPosition(map);
            pageable.setTotal(stvIdmfPositionRoleMapper
                    .selectRoleCountByPosition(map).longValue());
        }

        // ページの初期化
        Page<ImRoleAndPosition> page = new Page<ImRoleAndPosition>(
                stvIdmfRoleAndPositionList, pageable);
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
