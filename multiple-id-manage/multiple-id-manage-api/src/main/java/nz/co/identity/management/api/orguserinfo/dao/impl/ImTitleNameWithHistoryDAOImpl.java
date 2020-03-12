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
import nz.co.identity.management.api.orguserinfo.dao.ImTitleNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleNameMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImTitleMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImTitleNameMstMapper;

/**
 * Get title name information by access DB.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfTitleNameWithHistoryDAO")
public class ImTitleNameWithHistoryDAOImpl
        implements ImTitleNameMstDAO {

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
    private ImTitleMstMapper idmfTitleMstMapper;

    /**
     * IdmfTitleNameMstMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImTitleNameMstMapper idmfTitleNameMstMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleNameMst registerTitleName(
            ImTitleNameMst titleNameMst) {

        // 役職IDが役職マスタに存在しない場合
        if (idmfTitleMstMapper
                .selectByPrimaryKey(titleNameMst.getTitleId()) == null) {
            throw new ImRecordInexistenceException("The titleId["
                    + titleNameMst.getTitleId() + "] is not in TitleMst.");
        }
        String locale = titleNameMst.getLocale();
        if (StringUtils.isEmpty(locale)) {
            locale = LocaleContextHolder.getLocale().toString();
        }
        // ロールIDとロケールがロール名マスタに存在する場合，登録済みの異常とする
        ImTitleNameMst stvIdmfTitleNameMst = idmfTitleNameMstMapper
                .selectByTitleIdByLocale(titleNameMst.getTitleId(), locale);
        if (stvIdmfTitleNameMst != null) {
            throw new ImRuntimeException(
                    "Insert title name data into DB with failed because of same locale.");
        } else {
            // ロールIDとロケールがロール名マスタに存在しない場合，登録処理(INSERT)
            
            // entityのidがnullの場合、自動採番してDBに登録
            if (StringUtils.isEmpty(titleNameMst.getTitleNameId())) {
                titleNameMst.setTitleNameId(serialGenerator
                        .selectSerial(ImTitleNameMst.SERIAL_TABLE));
            } else {

                // 重複するidを登録する場合に、例外をthrow
                if (idmfTitleNameMstMapper.selectByPrimaryKey(
                        titleNameMst.getTitleNameId()) != null) {
                    throw new ImRuntimeException(
                            "The titleNameId[" + titleNameMst.getTitleNameId()
                                    + "] has been exists in TitleName.");
                }
            }

            titleNameMst.setCreatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(titleNameMst.getCreatedUser())) {
                titleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }
            titleNameMst.setTitleId(titleNameMst.getTitleId());
            if (titleNameMst.getActiveStartTime() == null) {
                titleNameMst.setActiveStartTime(CommonUtils.getSystemTime());
            }
            titleNameMst.setVersionNo(0);
            titleNameMst.setDeletedFlg(Short.valueOf("0"));
            titleNameMst.setLocale(locale);
            titleNameMst.setUpdatedTime(null);
            titleNameMst.setUpdatedUser(null);

            if (idmfTitleNameMstMapper.insert(titleNameMst) == 0) {
                throw new ImRuntimeException(
                        "Insert title name data into DB with failed.");
            }
        }

        return titleNameMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleNameMst updateTitleName(
            ImTitleNameMst titleNameMst) {
        // 役職IDが役職マスタに存在しない場合
        if (idmfTitleMstMapper
                .selectByPrimaryKey(titleNameMst.getTitleId()) == null) {
            throw new ImRecordInexistenceException("The titleId["
                    + titleNameMst.getTitleId() + "] is not in TitleMst.");
        }
        // 役職名IDにより、バージョンが役職名マスタに存在しない場合
        ImTitleNameMst selectResult = idmfTitleNameMstMapper
                .selectByPrimaryKey(titleNameMst.getTitleNameId());
        if (selectResult == null) {
            throw new ImRecordInexistenceException(
                    "The TitleNameId[" + titleNameMst.getTitleNameId()
                            + "] is not in titleNameMst.");
        } else {
            if (selectResult.getDeletedFlg() == 1) {
                throw new ImRuntimeException(
                        "there are record which deletedFlg is 1.");
            }
            Integer inputVersionNo = titleNameMst.getVersionNo();
            if (!selectResult.getVersionNo().equals(inputVersionNo)) {
                throw new ImOptimisticLockingFailureException(
                        "There is a record with exclusive error.");
            }

            titleNameMst.setUpdatedTime(CommonUtils.getSystemTime());
            if (StringUtils.isEmpty(titleNameMst.getUpdatedUser())) {
                titleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
            }

            titleNameMst.setVersionNo(inputVersionNo + 1);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("titleNameMst", titleNameMst);
            map.put("versionBase", inputVersionNo);
            if (idmfTitleNameMstMapper.updateByPrimaryKey(map) == 0) {
                throw new ImRuntimeException(
                        "Update title name data into DB with failed.");
            }
        }
        return titleNameMst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteTitleName(ImTitleNameMst titleNameMst) {
        int versionNo = 0;
        // 役職IDで指定されたロールの名称を役職名マスタから削除する。
        if (StringUtils.isEmpty(titleNameMst.getLocale())) {
            List<ImTitleNameMst> stvIdmfTitleNameMstList = idmfTitleNameMstMapper
                    .selectByTitleId(titleNameMst.getTitleId());
            // 役職IDが役職名マスタに存在しない場合
            if (CollectionUtils.isEmpty(stvIdmfTitleNameMstList)) {
                throw new ImRecordInexistenceException("The titleId["
                        + titleNameMst.getTitleId() + "] is not in titleName.");
            }

            for (ImTitleNameMst stvIdmfTitleNameMst : stvIdmfTitleNameMstList) {
                versionNo = stvIdmfTitleNameMst.getVersionNo();
                stvIdmfTitleNameMst.setUpdatedTime(CommonUtils.getSystemTime());
                stvIdmfTitleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
                stvIdmfTitleNameMst.setVersionNo(versionNo + 1);
                stvIdmfTitleNameMst.setDeletedFlg(Short.valueOf("1"));

                Map<String, Object> mapRoleName = new HashMap<String, Object>();
                mapRoleName.put("titleNameMst", stvIdmfTitleNameMst);
                mapRoleName.put("versionBase", versionNo);
                if (idmfTitleNameMstMapper
                        .updateByPrimaryKeySelective(mapRoleName) == 0) {
                    throw new ImRuntimeException(
                            "Delete title name data by logic from DB with failed.");
                }
            }
        } else {
            // ロケールが指定されている場合は、当該ロケールの名称のみ削除する。
            ImTitleNameMst titleName = idmfTitleNameMstMapper
                    .selectByTitleIdByLocale(titleNameMst.getTitleId(),
                            titleNameMst.getLocale());
            if (titleName == null) {
                throw new ImRecordInexistenceException("The titleId["
                        + titleNameMst.getTitleId() + "] and locale["
                        + titleNameMst.getLocale() + "] is not in TitleNameMst.");
            }
            versionNo = titleName.getVersionNo();

            // バージョン 排他処理
            this.checkVersionNo(versionNo, titleNameMst.getVersionNo());

            titleName.setUpdatedTime(CommonUtils.getSystemTime());

            String updatedUser = titleName.getUpdatedUser();
            if (StringUtils.isEmpty(updatedUser)) {
                titleName.setUpdatedUser(CommonUtils.getLoginUser());
            }
            titleName.setVersionNo(titleName.getVersionNo() + 1);
            titleName.setDeletedFlg(Short.valueOf("1"));
            titleName.setLocale("");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("titleNameMst", titleName);
            map.put("versionBase", versionNo);
            if (idmfTitleNameMstMapper.updateByPrimaryKeySelective(map) == 0) {
                throw new ImRuntimeException(
                        "Delete title name data by logic from DB with failed.");
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceTitleName(ImTitleNameMst titleNameMst) {

        titleNameMst.setUpdatedTime(CommonUtils.getSystemTime());
        titleNameMst.setUpdatedUser(CommonUtils.getLoginUser());
        titleNameMst.setVersionNo(titleNameMst.getVersionNo() + 1);
        titleNameMst.setDeletedFlg(Short.valueOf("1"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("titleNameMst", titleNameMst);

        if (idmfTitleNameMstMapper.updateByPrimaryKeySelective(map) == 0) {
            throw new ImRuntimeException(
                    "Delete title name data by logic from DB with failed.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImTitleNameMst> getTitleName(String titleId) {
        return idmfTitleNameMstMapper.selectByTitleId(titleId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ImTitleNameMst getTitleNameById(String titleNameId) {
        return idmfTitleNameMstMapper.selectByPrimaryKey(titleNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImTitleNameMst> getTitleNameByTitleCode(String titleCode) {
        return idmfTitleNameMstMapper.selectByTitleCode(titleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImTitleNameMst> searchTitleName(
            ImTitleNameMst titleNameMst, Integer pageNum, Integer pageSize,
            List<String> sort) {

        // ページング情報の初期化
        Pageable pageable = new Pageable(pageNum, pageSize);

        // 検索条件を編集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("titleNameMst", titleNameMst);
        map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
        map.put("pageSize", pageable.getPageSize());
        map.put("sort", CommonUtils.makeOrders(sort));

        // 全件件数を設定
        pageable.setTotal(idmfTitleNameMstMapper.selectCountByTitleNameMst(map)
                .longValue());
        // ページの初期化
        Page<ImTitleNameMst> page = new Page<ImTitleNameMst>(
                idmfTitleNameMstMapper.selectByTitleNameMst(map), pageable);
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
