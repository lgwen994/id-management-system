package nz.co.identity.management.api.orguserinfo.dao.impl;

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
import nz.co.identity.management.api.orguserinfo.dao.ImCompanyMstDAO;
import nz.co.identity.management.api.orguserinfo.dao.ImCompanyNameMstDAO;
import nz.co.identity.management.api.orguserinfo.dao.ImOrgMstDAO;
import nz.co.identity.management.api.orguserinfo.dao.ImTitleMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyMst;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyNameMst;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImCompanyMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImCompanyNameMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImOrgMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImTitleMstMapper;

/**
 * the implementation class of company's DAO interface.
 *
 * @since Staveware Core Ver.5.3
 */
@Service
public class ImCompanyWithoutHistoryDAOImpl
        implements ImCompanyMstDAO {
    /**
     * SerialGenerator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private SerialGenerator serialGenerator;

    /**
     * idmfCompanyMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImCompanyMstMapper idmfCompanyMstMapper;

    /**
     * idmfCompanyNameMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImCompanyNameMstMapper idmfCompanyNameMstMapper;

    /**
     * idmfOrgMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImOrgMstMapper idmfOrgMstMapper;

    /**
     * idmfOrgMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImTitleMstMapper idmfTitleMstMapper;

    /**
     * stvIdmfCompanyNameWithoutHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfCompanyNameWithoutHistoryDAO")
    private ImCompanyNameMstDAO stvIdmfCompanyNameWithoutHistoryDAO;

    /**
     * stvIdmfTitleWithoutHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfTitleWithoutHistoryDAO")
    private ImTitleMstDAO stvIdmfTitleWithoutHistoryDAO;

    /**
     * stvIdmfTitleWithoutHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource(name = "stvIdmfOrgWithoutHistoryDAO")
    private ImOrgMstDAO stvIdmfOrgWithoutHistoryDAO;

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
    public ImCompanyMst registerCompany(ImCompanyMst companyMst) {

        if (StringUtils.isEmpty(companyMst.getCompanyId())) {
            companyMst.setCompanyId(serialGenerator
                    .selectSerial(ImCompanyMst.SERIAL_TABLE));
        } else {
            if (idmfCompanyMstMapper
                    .selectByPrimaryKey(companyMst.getCompanyId()) != null) {
                throw new ImRuntimeException(
                        "The companyId[" + companyMst.getCompanyId()
                                + "] has been exists in Company.");
            }
        }

        companyMst.setCreatedTime(CommonUtils.getSystemTime());
        if (StringUtils.isEmpty(companyMst.getCreatedUser())) {
            companyMst.setCreatedUser(CommonUtils.getLoginUser());
        }
        if (companyMst.getActiveStartTime() == null) {
            companyMst.setActiveStartTime(CommonUtils.getSystemTime());
        }
        // 会社コードチェック
        List<ImCompanyMst> stvIdmfCompanyMstList = idmfCompanyMstMapper
                .selectByCompanyCode(companyMst.getCompanyCode());
        if (CollectionUtils.isEmpty(stvIdmfCompanyMstList)) {
            companyMst.setVersionNo(0);
            companyMst.setDeletedFlg(Short.valueOf("0"));
            if (idmfCompanyMstMapper.insert(companyMst) == 0) {
                throw new ImRuntimeException(
                        "Insert company data into DB with failed.");
            }
        } else {
            throw new ImRuntimeException("The same code with failed.");
        }

        return companyMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImCompanyMst updateCompany(ImCompanyMst companyMst) {
        // 会社IDが会社マスタに存在しない場合
        ImCompanyMst selectResult = idmfCompanyMstMapper
                .selectByPrimaryKey(companyMst.getCompanyId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The companyId["
                    + companyMst.getCompanyId() + "] is not in CompanyMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            // 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
            String code = companyMst.getCompanyCode();
            if (code != null && !selectResult.getCompanyCode().equals(code)) {
                throw new ImRecordInexistenceException(
                        "There is a record with disaccorded code[" + code
                                + "]");
            }
            // 会社コードにより、バージョン 排他処理
            Integer inputVersionNo = companyMst.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                // ロールコードにより、指定したバージョンがバージョンがロールマスタに存在しない場合
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            companyMst.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(companyMst.getUpdatedUser())) {
                companyMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            // 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
            companyMst.setVersionNo(idmfCompanyMstMapper
                    .selectMaxVersion(companyMst.getCompanyCode()) + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyMst", companyMst);
            map.put("versionBase", inputVersionNo);
            // 更新失敗
            if (idmfCompanyMstMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update company data into DB with failed.");
            }
        }

        return companyMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteCompany(ImCompanyMst companyMst) {

        // 会社IDが会社マスタに存在しない場合
        ImCompanyMst selectResult = idmfCompanyMstMapper
                .selectByPrimaryKey(companyMst.getCompanyId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException("The companyId["
                    + companyMst.getCompanyId() + "] is not in CompanyMst.");
        }
        // 会社名マスタ、組織マスタ、役職マスタに該当する会社IDが存在する場合
        List<ImCompanyNameMst> stvIdmfCompanyNameMstList = idmfCompanyNameMstMapper
                .selectByCompanyId(companyMst.getCompanyId());
        List<ImOrgMst> stvIdmfOrgMstList = idmfOrgMstMapper
                .selectByCompanyId(companyMst.getCompanyId());
        List<ImTitleMst> stvIdmfTitleMstList = idmfTitleMstMapper
                .selectByCompanyId(companyMst.getCompanyId());

        if (!CollectionUtils.isEmpty(stvIdmfCompanyNameMstList)
                || !CollectionUtils.isEmpty(stvIdmfOrgMstList)
                || !CollectionUtils.isEmpty(stvIdmfTitleMstList)) {
            throw new ImRecordInexistenceException(
                    "The companyId[" + companyMst.getCompanyId()
                            + "] is in CompanyNameMst or OrgMs or TitleMst.");
        }

        // 会社IDにより、バージョン 排他処理
        Integer inputVersionNo = companyMst.getVersionNo();
        if (!selectResult.getVersionNo().equals(inputVersionNo)) {
            // 会社IDにより、指定したバージョンが会社マスタに存在しない場合
            throw new ImOptimisticLockingFailureException(
                    "There is a record with exclusive error.");
        }
        // 削除失敗
        if (idmfCompanyMstMapper.deleteByCompanyIdByVersion(
                companyMst.getCompanyId(), companyMst.getVersionNo()) == 0) {
            throw new ImRuntimeException(
                    "Delete company data from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteForceCompany(ImCompanyMst companyMst) {
        // 会社IDが会社マスタに存在しない場合
        if (idmfCompanyMstMapper
                .selectByPrimaryKey(companyMst.getCompanyId()) == null) {
            throw new ImRecordInexistenceException("The companyId["
                    + companyMst.getCompanyId() + "] is not in CompanyMst.");
        }
        // 入力会社IDにより、会社マスタと対応する会社名マスタ、組織マスタ、役職マスタの情報を物理削除する。
        List<ImCompanyNameMst> stvIdmfCompanyNameMstList = idmfCompanyNameMstMapper
                .selectByCompanyId(companyMst.getCompanyId());
        List<ImOrgMst> stvIdmfOrgMstList = idmfOrgMstMapper
                .selectByCompanyId(companyMst.getCompanyId());
        List<ImTitleMst> stvIdmfTitleMstList = idmfTitleMstMapper
                .selectByCompanyId(companyMst.getCompanyId());
        // 会社名
        if (!CollectionUtils.isEmpty(stvIdmfCompanyNameMstList)) {
            ImCompanyNameMst stvIdmfCompanyNameMst = new ImCompanyNameMst();
            stvIdmfCompanyNameMst.setCompanyId(companyMst.getCompanyId());
            // 削除失敗
            if (!stvIdmfCompanyNameWithoutHistoryDAO
                    .deleteForceCompanyName(stvIdmfCompanyNameMst)) {
                throw new ImRuntimeException(
                        "Delete companyName data from DB with failed.");
            }
        }
        // 組織
        if (!CollectionUtils.isEmpty(stvIdmfOrgMstList)) {
            for (ImOrgMst stvIdmfOrgMst : stvIdmfOrgMstList) {
                if (!stvIdmfOrgWithoutHistoryDAO
                        .deleteForceOrg(stvIdmfOrgMst)) {
                    throw new ImRuntimeException(
                            "Delete org data from DB with failed.");
                }
            }
        }
        // 役職
        if (!CollectionUtils.isEmpty(stvIdmfTitleMstList)) {
            for (ImTitleMst stvIdmfTitleMst : stvIdmfTitleMstList) {
                // 削除失敗
                if (!stvIdmfTitleWithoutHistoryDAO
                        .deleteForceTitle(stvIdmfTitleMst)) {
                    throw new ImRuntimeException(
                            "Delete title data from DB with failed.");
                }
            }
        }

        // 削除失敗
        if (idmfCompanyMstMapper
                .deleteByPrimaryKey(companyMst.getCompanyId()) == 0) {
            throw new ImRuntimeException(
                    "Delete company data from DB with failed.");
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImCompanyMst getCompany(String companyId) {
        String locale = LocaleContextHolder.getLocale().toString();

        // 利用ロケールが指定されている場合
        ImCompanyMst stvIdmfCompanyMst = idmfCompanyMstMapper
                .selectByCompanyIdByLocale(companyId, locale);
        if (stvIdmfCompanyMst == null) {
            // 利用ロケールが指定されていない場合
            stvIdmfCompanyMst = idmfCompanyMstMapper
                    .selectByPrimaryKey(companyId);
        }

        return stvIdmfCompanyMst;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImCompanyMst getCompanyByCode(String companyCode) {

        String locale = LocaleContextHolder.getLocale().toString();
        // 利用ロケールが指定されている場合
        List<ImCompanyMst> stvIdmfCompanyMst = idmfCompanyMstMapper
                .selectByCompanyCodeByLocale(companyCode, locale);
        if (stvIdmfCompanyMst == null) {
            // 利用ロケールが指定されていない場合
            stvIdmfCompanyMst = idmfCompanyMstMapper
                    .selectByCompanyCode(companyCode);
        }
        // 検索失敗
        if (CollectionUtils.isEmpty(stvIdmfCompanyMst)) {
            return null;
        }

        return stvIdmfCompanyMst.get(0);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Page<ImCompanyMst> searchCompany(ImCompanyMst companyMst,
            Integer pageNum, Integer pageSize, List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyMst", companyMst);
        map.put("locale", LocaleContextHolder.getLocale().toString());
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        List<ImCompanyMst> stvIdmfCompanyMstList = null;
        if (localeEnabled.equals("on")) {
            // 全件件数を設定
            pageable.setTotal(idmfCompanyMstMapper
                    .selectCountByCompanyMstByLocale(map).longValue());
            // 明細情報を取得
            stvIdmfCompanyMstList = idmfCompanyMstMapper
                    .selectByCompanyMstByLocale(map);
        }
        if (CollectionUtils.isEmpty(stvIdmfCompanyMstList)) {
            // 全件件数を設定
            pageable.setTotal(idmfCompanyMstMapper.selectCountByCompanyMst(map)
                    .longValue());
            // 明細情報を取得
            stvIdmfCompanyMstList = idmfCompanyMstMapper
                    .selectByCompanyMst(map);
        }

        // ページの初期化
        Page<ImCompanyMst> page = new Page<ImCompanyMst>(
                stvIdmfCompanyMstList, pageable);
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
