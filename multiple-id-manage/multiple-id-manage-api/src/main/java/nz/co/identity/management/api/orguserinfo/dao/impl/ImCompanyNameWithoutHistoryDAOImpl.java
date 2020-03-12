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
import nz.co.identity.management.api.orguserinfo.dao.ImCompanyNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyMst;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyNameMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImCompanyMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImCompanyNameMstMapper;

/**
 * the implementation class of companyName's DAO interface.
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfCompanyNameWithoutHistoryDAO")
public class ImCompanyNameWithoutHistoryDAOImpl
        implements ImCompanyNameMstDAO {
    /**
     * SerialGenerator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * IdmfCompanyNameMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImCompanyNameMstMapper stvIdmfCompanyNameMstMapper;

    /**
     * IdmfCompanyMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImCompanyMstMapper stvIdmfCompanyMstMapper;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImCompanyNameMst registerCompanyName(
            ImCompanyNameMst companyNameMst) {
        // 会社IDが会社マスタに存在しない場合
        ImCompanyMst companyMst = stvIdmfCompanyMstMapper
                .selectByPrimaryKey(companyNameMst.getCompanyId());
        if (companyMst == null) {
            throw new ImRecordInexistenceException("The companyId["
                    + companyNameMst.getCompanyId() + "] is not in Company.");
        }
        String locale = companyNameMst.getLocale();
        if (StringUtils.isEmpty(locale)) {
            locale = LocaleContextHolder.getLocale().toString();
        }
        // 会社IDとロケールが会社名マスタに存在する場合，登録済みの異常とする
        ImCompanyNameMst strCompanyNameMst = stvIdmfCompanyNameMstMapper
                .selectByCompanyIdByLocale(companyNameMst.getCompanyId(),
                        locale);
        if (strCompanyNameMst != null) {
            throw new ImRuntimeException(
                    "Insert companyName data into DB with failed because of same locale.");
        } else {
            // ロールIDとロケールがロール名マスタに存在しない場合，登録処理(INSERT)

            // entityのidがnullの場合、自動採番してDBに登録
            if (StringUtils.isEmpty(companyNameMst.getCompanyNameId())) {
                companyNameMst.setCompanyNameId(serialGenerator
                        .selectSerial(ImCompanyNameMst.SERIAL_TABLE));
            } else {

                // 重複するidを登録する場合に、例外をthrow
                if (stvIdmfCompanyNameMstMapper.selectByPrimaryKey(
                        companyNameMst.getCompanyNameId()) != null) {
                    throw new ImRuntimeException("The companyNameId["
                            + companyNameMst.getCompanyNameId()
                            + "] has been exists in CompanyName.");
                }
            }

            companyNameMst.setCreatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(companyMst.getCreatedUser())) {
                companyNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            if (companyNameMst.getActiveStartTime() == null) {
                companyNameMst.setActiveStartTime(CommonUtils.getSystemTime());
            }
            companyNameMst.setCreatedTime(CommonUtils.getSystemTime());
            companyNameMst.setVersionNo(0);
            companyNameMst.setDeletedFlg(Short.valueOf("0"));
            companyNameMst.setLocale(locale);
            companyNameMst.setUpdatedTime(null);
            companyNameMst.setUpdatedUser(null);

            if (stvIdmfCompanyNameMstMapper.insert(companyNameMst) == 0) {
                throw new ImRuntimeException(
                        "Insert companyName data into DB with failed.");
            }
        }

        return companyNameMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImCompanyNameMst updateCompanyName(
            ImCompanyNameMst companyNameMst) {
        // 会社IDが会社マスタに存在しない場合
        if (stvIdmfCompanyMstMapper
                .selectByPrimaryKey(companyNameMst.getCompanyId()) == null) {
            throw new ImRecordInexistenceException("The companyId["
                    + companyNameMst.getCompanyId() + "] is not in Company.");
        }

        ImCompanyNameMst selectResult = stvIdmfCompanyNameMstMapper
                .selectByPrimaryKey(companyNameMst.getCompanyNameId());
        // 会社名IDにより、バージョンが会社名マスタに存在しない場合
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The companyNameId[" + companyNameMst.getCompanyNameId()
                            + "] is not in CompanyNameMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            Integer inputVersionNo = companyNameMst.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }
            companyNameMst.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(companyNameMst.getUpdatedUser())) {
                companyNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }

            companyNameMst.setVersionNo(inputVersionNo + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyNameMst", companyNameMst);
            map.put("versionBase", inputVersionNo);
            if (stvIdmfCompanyNameMstMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update companyName data into DB with failed.");
            }
        }

        return companyNameMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteCompanyName(ImCompanyNameMst companyName) {

        String companyId = companyName.getCompanyId();
        String locale = companyName.getLocale();

        // 会社IDで指定された会社の名称を会社名マスタから削除する。
        if (StringUtils.isEmpty(locale)) {
            List<ImCompanyNameMst> stvIdmfCompanyNameMstList = stvIdmfCompanyNameMstMapper
                    .selectByCompanyId(companyId);
            // 会社IDが会社名マスタに存在しない場合
            if (CollectionUtils.isEmpty(stvIdmfCompanyNameMstList)) {
                throw new ImRecordInexistenceException(
                        "The companyId[" + companyName.getCompanyId()
                                + "] is not in CompanyName.");
            }
            if (stvIdmfCompanyNameMstMapper.deleteByCompanyId(companyId) == 0) {
                throw new ImRuntimeException(
                        "Delete companyName data from DB with failed.");
            }
        } else {
            // ロケールが指定されている場合は、当該ロケールの名称のみ削除する。
            if (stvIdmfCompanyNameMstMapper.selectByCompanyIdByLocale(
                    companyName.getCompanyId(),
                    companyName.getLocale()) == null) {
                throw new ImRecordInexistenceException("The companyId["
                        + companyName.getCompanyId() + "] and locale["
                        + companyName.getLocale() + "] is not in CompanyName.");
            }

            if (stvIdmfCompanyNameMstMapper.deleteByCompanyIdByLocale(companyId,
                    locale) == 0) {
                throw new ImRuntimeException(
                        "Delete companyName data from DB with failed.");
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteForceCompanyName(ImCompanyNameMst companyName) {
        if (stvIdmfCompanyNameMstMapper
                .deleteByCompanyId(companyName.getCompanyId()) == 0) {
            throw new ImRuntimeException(
                    "Delete companyName data from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<ImCompanyNameMst> getCompanyName(String companyId) {
        return stvIdmfCompanyNameMstMapper.selectByCompanyId(companyId);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImCompanyNameMst getCompanyNameById(String companyNameId) {
        return stvIdmfCompanyNameMstMapper.selectByPrimaryKey(companyNameId);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<ImCompanyNameMst> getCompanyNameByCompanyCode(
            String companyCode) {
        return stvIdmfCompanyNameMstMapper.selectByCompanyCode(companyCode);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Page<ImCompanyNameMst> searchCompanyName(
            ImCompanyNameMst companyNameMst, Integer pageNum,
            Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyNameMst", companyNameMst);
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(stvIdmfCompanyNameMstMapper
                .selectCountByCompanyNameMst(map).longValue());

        // ページの初期化
        Page<ImCompanyNameMst> page = new Page<ImCompanyNameMst>(
                stvIdmfCompanyNameMstMapper.selectByCompanyNameMst(map),
                pageable);
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
