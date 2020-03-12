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
import nz.co.identity.management.api.orguserinfo.dao.ImOrgNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImOrgMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImOrgNameMstMapper;

/**
 * Get organizational name information by access DB.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfOrgNameMstWithoutHistoryDAO")
public class ImOrgNameMstWithoutHistoryDAOImpl
        implements ImOrgNameMstDAO {

    /**
     * serial Generator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * IdmfOrgMst Mapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImOrgMstMapper stvIdmfOrgMstMapper;

    /**
     * IdmfOrgNameMst Mapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImOrgNameMstMapper stvIdmfOrgNameMstMapper;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImOrgNameMst registerOrgName(ImOrgNameMst orgNameMst) {
        ImOrgMst stvIdmfOrgMst = stvIdmfOrgMstMapper
                .selectByPrimaryKey(orgNameMst.getOrgId());
        // 組織IDが組織マスタに存在しない場合
        if (stvIdmfOrgMst == null) {
            throw new ImRecordInexistenceException("The orgId["
                    + orgNameMst.getOrgId() + "] is not in OrgMst.");
        }
        String locale = orgNameMst.getLocale();
        if (StringUtils.isEmpty(locale)) {
            locale = LocaleContextHolder.getLocale().toString();
        }
        // 組織IDとロケールが組織名マスタに存在する場合，更新処理(UPDATE)
        ImOrgNameMst stvIdmfOrgNameMst = stvIdmfOrgNameMstMapper
                .selectByOrgIdByLocale(orgNameMst.getOrgId(), locale);
        if (stvIdmfOrgNameMst != null) {
            throw new ImRuntimeException(
                    "Insert orgName data into DB with failed because of same locale.");
        } else {
            // 組織IDとロケールが組織名マスタに存在しない場合，登録処理(INSERT)
            
            // entityのidがnullの場合、自動採番してDBに登録
            if (StringUtils.isEmpty(orgNameMst.getOrgNameId())) {
                orgNameMst.setOrgNameId(serialGenerator
                        .selectSerial(ImOrgNameMst.SERIAL_TABLE));
            } else {

                // 重複するidを登録する場合に、例外をthrow
                if (stvIdmfOrgNameMstMapper.selectByPrimaryKey(
                        orgNameMst.getOrgNameId()) != null) {
                    throw new ImRuntimeException(
                            "The orgNameId[" + orgNameMst.getOrgNameId()
                                    + "] has been exists in OrgName.");
                }
            }

            orgNameMst.setCreatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(orgNameMst.getCreatedUser())) {
                orgNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            if (orgNameMst.getActiveStartTime() == null) {
                orgNameMst.setActiveStartTime(CommonUtils.getSystemTime());
            }
            orgNameMst.setCreatedTime(CommonUtils.getSystemTime());
            orgNameMst.setVersionNo(0);
            orgNameMst.setDeletedFlg(Short.valueOf("0"));
            orgNameMst.setLocale(locale);
            orgNameMst.setUpdatedTime(null);
            orgNameMst.setUpdatedUser(null);

            if (stvIdmfOrgNameMstMapper.insert(orgNameMst) == 0) {
                throw new ImRuntimeException(
                        "Insert org name data into DB with failed.");
            }
        }
        return orgNameMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImOrgNameMst updateOrgName(ImOrgNameMst orgNameMst) {
        ImOrgMst stvIdmfOrgMst = stvIdmfOrgMstMapper
                .selectByPrimaryKey(orgNameMst.getOrgId());
        // 組織IDが組織マスタに存在しない場合
        if (stvIdmfOrgMst == null) {
            throw new ImRecordInexistenceException("The orgId["
                    + orgNameMst.getOrgId() + "] is not in OrgMst.");

        }

        // 組織名IDにより、バージョンが組織名マスタに存在しない場合
        ImOrgNameMst selectResult = stvIdmfOrgNameMstMapper
                .selectByPrimaryKey(orgNameMst.getOrgNameId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The orgNameId["
                    + orgNameMst.getOrgNameId() + "] is not in OrgNameMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            Integer inputVersionNo = orgNameMst.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            orgNameMst.setUpdatedTime(CommonUtils.getSystemTime());
            String updatedUser = orgNameMst.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                orgNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            orgNameMst.setVersionNo(inputVersionNo + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orgNameMst", orgNameMst);
            map.put("versionBase", inputVersionNo);
            if (stvIdmfOrgNameMstMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update org name data into DB with failed.");
            }
        }

        return orgNameMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteOrgName(ImOrgNameMst orgNameMst) {
        String orgId = orgNameMst.getOrgId();
        String locale = orgNameMst.getLocale();

        // 組織IDで指定された組織の名称を組織名マスタから削除する。
        if (StringUtils.isEmpty(locale)) {
            List<ImOrgNameMst> stvIdmfOrgNameMstList = stvIdmfOrgNameMstMapper
                    .selectByOrgId(orgId);
            // 組織IDが組織名マスタに存在しない場合
            if (CollectionUtils.isEmpty(stvIdmfOrgNameMstList)) {
                throw new ImRecordInexistenceException(
                        "The orgId[" + orgId + "] is not in OrgName.");
            }

            if (stvIdmfOrgNameMstMapper.deleteByOrgId(orgId) == 0) {
                throw new ImRuntimeException(
                        "Delete org name from DB with failed.");
            }

        } else {
            // ロケールが指定されている場合は、当該ロケールの名称のみ削除する。
            if (stvIdmfOrgNameMstMapper.selectByOrgIdByLocale(orgId,
                    locale) == null) {
                throw new ImRecordInexistenceException(
                        "The orgId[" + orgId + "] and locale[" + locale
                                + "] is not in orgName.");
            }

            if (stvIdmfOrgNameMstMapper.deleteByOrgIdByLocale(orgId,
                    locale) == 0) {
                throw new ImRuntimeException(
                        "Delete org name data from DB with failed.");
            }

        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteForceOrgName(ImOrgNameMst orgNameMst) {
        if (stvIdmfOrgNameMstMapper.deleteByOrgId(orgNameMst.getOrgId()) == 0) {
            throw new ImRuntimeException(
                    "Delete org name from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<ImOrgNameMst> getOrgName(String orgId) {
        return stvIdmfOrgNameMstMapper.selectByOrgId(orgId);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImOrgNameMst getOrgNameById(String orgNameId) {
        return stvIdmfOrgNameMstMapper.selectByPrimaryKey(orgNameId);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<ImOrgNameMst> getOrgNameByOrgCode(String orgCode) {
        return stvIdmfOrgNameMstMapper.selectByOrgCode(orgCode);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Page<ImOrgNameMst> searchOrgName(ImOrgNameMst orgNameMst,
            Integer pageNum, Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orgNameMst", orgNameMst);
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(stvIdmfOrgNameMstMapper.selectCountByOrgNameMst(map)
                .longValue());

        // ページの初期化
        Page<ImOrgNameMst> page = new Page<ImOrgNameMst>(
                stvIdmfOrgNameMstMapper.selectByOrgNameMst(map), pageable);
        // ページ情報
        return page;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public String getHistoryType() {
        return BaseDAO.HISTORY_TYPE_OFF;
    }

}
