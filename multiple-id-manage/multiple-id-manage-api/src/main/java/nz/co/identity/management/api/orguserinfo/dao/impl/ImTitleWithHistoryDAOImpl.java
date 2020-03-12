package nz.co.identity.management.api.orguserinfo.dao.impl;

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
import nz.co.identity.management.api.orguserinfo.dao.ImTitleMstDAO;
import nz.co.identity.management.api.orguserinfo.dao.ImTitleNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleMst;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleNameMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImCompanyMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImPositionMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImTitleMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImTitleNameMstMapper;

/**
 * Get title information by access DB.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfTitleWithHistoryDAO")
public class ImTitleWithHistoryDAOImpl implements ImTitleMstDAO {

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
	 * idmfTitleNameMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImTitleNameMstMapper idmfTitleNameMstMapper;

	/**
	 * idmfPositionMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImPositionMapper idmfPositionMapper;

	/**
	 * idmfCompanyMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImCompanyMstMapper idmfCompanyMstMapper;

	/**
	 * stvIdmfTitleNameMstWithHistoryDAO
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource(name = "stvIdmfTitleNameWithHistoryDAO")
	private ImTitleNameMstDAO stvIdmfTitleNameMstWithHistoryDAO;

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
	public ImTitleMst registerTitle(ImTitleMst titleMst) {
		// 会社IDが会社マスタに存在しない場合
		if (idmfCompanyMstMapper.selectByPrimaryKey(titleMst.getCompanyId()) == null) {
			throw new ImRecordInexistenceException(
					"The companyId[" + titleMst.getCompanyId() + "] is not in CompanyMst.");
		}

		// entityのidがnullの場合、自動採番してDBに登録
		if (StringUtils.isEmpty(titleMst.getTitleId())) {
			titleMst.setTitleId(serialGenerator.selectSerial(ImTitleMst.SERIAL_TABLE));
		} else {

			// 重複するidを登録する場合に、例外をthrow
			if (idmfTitleMstMapper.selectByPrimaryKey(titleMst.getTitleId()) != null) {
				throw new ImRuntimeException(
						"The titleId[" + titleMst.getTitleId() + "] has been exists in Title.");
			}
		}

		titleMst.setCreatedTime(CommonUtils.getSystemTime());
		if (StringUtils.isEmpty(titleMst.getCreatedUser())) {
			titleMst.setCreatedUser(CommonUtils.getLoginUser());
		}
		if (titleMst.getActiveStartTime() == null) {
			titleMst.setActiveStartTime(CommonUtils.getSystemTime());
		}
		titleMst.setUpdatedTime(null);
		titleMst.setUpdatedUser(null);
		// 役職コードチェック
		List<ImTitleMst> stvIdmfTitleMstList = idmfTitleMstMapper.selectByTitleCode(titleMst.getTitleCode());
		if (CollectionUtils.isEmpty(stvIdmfTitleMstList)) {
			titleMst.setVersionNo(0);
		} else {
			// 新規追加するレコードのコードと同じコードを持つレコードが存在し、有効期間が被る場合、例外をスローする。
			Date titleEndTime = titleMst.getActiveEndTime();
			Date titleStartTime = titleMst.getActiveStartTime();
			Date startTime = null;
			Date endTime = null;
			for (ImTitleMst stvIdmfTitleMst : stvIdmfTitleMstList) {
				endTime = stvIdmfTitleMst.getActiveEndTime();
				startTime = stvIdmfTitleMst.getActiveStartTime();

				if (titleStartTime.getTime() > startTime.getTime()) {
					if (endTime == null || titleStartTime.getTime() < endTime.getTime()) {
						throw new ImRuntimeException(
								"There is a record with active start time or active end time covered.");
					}
				} else {
					if (titleEndTime == null || titleEndTime.getTime() > startTime.getTime()) {
						throw new ImRuntimeException(
								"There is a record with active start time or active end time covered.");
					}
				}
			}
			// 新規追加するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
			titleMst.setVersionNo(idmfTitleMstMapper.selectMaxVersion(titleMst.getTitleCode()) + 1);

		}
		titleMst.setDeletedFlg(Short.valueOf("0"));
		if (idmfTitleMstMapper.insert(titleMst) == 0) {
			throw new ImRuntimeException("Insert title data into DB with failed.");
		}

		return titleMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImTitleMst updateTitle(ImTitleMst titleMst) {
		// 会社IDが会社マスタに存在しない場合
		if (idmfCompanyMstMapper.selectByPrimaryKey(titleMst.getCompanyId()) == null) {
			throw new ImRecordInexistenceException(
					"The companyId[" + titleMst.getCompanyId() + "] is not in CompanyMst.");
		}
		// 役職IDが役職マスタに存在しない場合
		ImTitleMst selectResult = idmfTitleMstMapper.selectByPrimaryKey(titleMst.getTitleId());
		if (selectResult == null) {
			throw new ImRecordInexistenceException(
					"The titleid[" + titleMst.getTitleId() + "] is not in TitleMst.");
		} else {
			if (selectResult.getDeletedFlg() == 1) {
				throw new ImRuntimeException("there are record which deletedFlg is 1.");
			}
			// 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
			String code = titleMst.getTitleCode();
			if (code != null && !selectResult.getTitleCode().equals(code)) {
				throw new ImRecordInexistenceException("There is a record with disaccorded code[" + code + "]");
			}
			// 役職コードにより、バージョン 排他処理
			Integer inputVersionNo = titleMst.getVersionNo();
			if (!selectResult.getVersionNo().equals(inputVersionNo)) {
				// 役職コードにより、指定したバージョンがバージョンが役職マスタに存在しない場合
				throw new ImOptimisticLockingFailureException("There is a record with exclusive error.");
			}

			titleMst.setUpdatedTime(CommonUtils.getSystemTime());
			if (StringUtils.isEmpty(titleMst.getUpdatedUser())) {
				titleMst.setUpdatedUser(CommonUtils.getLoginUser());
			}
			// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
			titleMst.setVersionNo(idmfTitleMstMapper.selectMaxVersion(titleMst.getTitleCode()) + 1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("titleMst", titleMst);
			map.put("versionBase", inputVersionNo);
			// 更新失敗
			if (idmfTitleMstMapper.updateByPrimaryKey(map) == 0) {
				throw new ImRuntimeException("Update title data into DB with failed.");
			}
		}

		return titleMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean deleteTitle(ImTitleMst titleMst) {

		// 役職IDが役職マスタに存在しない場合
		ImTitleMst selectResult = idmfTitleMstMapper.selectByPrimaryKey(titleMst.getTitleId());
		if (selectResult == null) {
			throw new ImRecordInexistenceException(
					"The titleId[" + titleMst.getTitleId() + "] is not in TitleMst.");
		}
		// 役職名マスタ、ポジションに該当する役職IDが存在する場合
		List<ImTitleNameMst> stvIdmfTitleNameMstList = idmfTitleNameMstMapper
				.selectByTitleId(titleMst.getTitleId());
		List<ImPosition> stvIdmfPositionList = idmfPositionMapper.selectByTitleId(titleMst.getTitleId());
		if ((!CollectionUtils.isEmpty(stvIdmfTitleNameMstList)) || (!CollectionUtils.isEmpty(stvIdmfPositionList))) {
			throw new ImRecordInexistenceException(
					"The titleId[" + titleMst.getTitleId() + "] is in TitleNameMst or Position.");
		}
		// 役職コードにより、バージョン 排他処理
		Integer inputVersionNo = titleMst.getVersionNo();
		if (!selectResult.getVersionNo().equals(inputVersionNo)) {
			// 役職コードにより、指定したバージョンが役職マスタに存在しない場合
			throw new ImOptimisticLockingFailureException("There is a record with exclusive error.");
		}
		titleMst.setUpdatedTime(CommonUtils.getSystemTime());
		String updatedUser = titleMst.getUpdatedUser();
		if (StringUtils.isEmpty(updatedUser)) {
			titleMst.setUpdatedUser(CommonUtils.getLoginUser());
		}
		// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
		titleMst.setVersionNo(idmfTitleMstMapper.selectMaxVersion(selectResult.getTitleCode()) + 1);
		titleMst.setDeletedFlg(Short.valueOf("1"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("titleMst", titleMst);
		map.put("versionBase", inputVersionNo);

		// 論理削除失敗
		if (idmfTitleMstMapper.updateByPrimaryKeySelective(map) == 0) {
			throw new ImRuntimeException("Delete title data by logic from DB with failed.");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean deleteForceTitle(ImTitleMst titleMst) {
		if (idmfTitleMstMapper.selectByPrimaryKey(titleMst.getTitleId()) == null) {
			throw new ImRecordInexistenceException(
					"The titleId[" + titleMst.getTitleId() + "] is not in TitleMst.");
		}

		// 役職名マスタ、ポジションに該当する役職IDが存在する場合、入力役職IDにより、役職マスタと対応する役職名マスタ、ポジションの情報を論理削除する。
		List<ImTitleNameMst> stvIdmfTitleNameMstList = idmfTitleNameMstMapper
				.selectByTitleId(titleMst.getTitleId());
		// 役職名マスタ
		if (!CollectionUtils.isEmpty(stvIdmfTitleNameMstList)) {
			for (ImTitleNameMst stvIdmfTitleNameMst : stvIdmfTitleNameMstList) {
				if (!stvIdmfTitleNameMstWithHistoryDAO.deleteForceTitleName(stvIdmfTitleNameMst)) {
					throw new ImRuntimeException("Delete title name data by logic from DB with failed.");
				}
			}
		}

		titleMst.setUpdatedTime(CommonUtils.getSystemTime());
		if (StringUtils.isEmpty(titleMst.getUpdatedUser())) {
			titleMst.setUpdatedUser(CommonUtils.getLoginUser());
		}
		// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
		titleMst.setVersionNo(idmfTitleMstMapper.selectMaxVersion(titleMst.getTitleCode()) + 1);
		titleMst.setDeletedFlg(Short.valueOf("1"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("titleMst", titleMst);
		// 論理削除失敗
		if (idmfTitleMstMapper.updateByPrimaryKeySelective(map) == 0) {
			throw new ImRuntimeException("Delete title data by logic from DB with failed.");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImTitleMst getTitle(String titleId) {
		String locale = LocaleContextHolder.getLocale().toString();
		// 利用ロケールが指定されている場合
		ImTitleMst stvIdmfTitleMst = idmfTitleMstMapper.selectByTitleIdByLocale(titleId, locale);
		if (idmfTitleMstMapper.selectByTitleIdByLocale(titleId, locale) == null) {
			// 利用ロケールが指定されていない場合
			stvIdmfTitleMst = idmfTitleMstMapper.selectByPrimaryKey(titleId);
		}

		return stvIdmfTitleMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImTitleMst getTitleByTitleCode(String titleCode) {
		// 役職実例
		String locale = LocaleContextHolder.getLocale().toString();
		// 利用ロケールが指定されている場合
		List<ImTitleMst> stvIdmfTitleMstList = idmfTitleMstMapper.selectByTitleCodeByLocale(titleCode, locale);
		if (CollectionUtils.isEmpty(stvIdmfTitleMstList)) {
			// 利用ロケールが指定されていない場合
			stvIdmfTitleMstList = idmfTitleMstMapper.selectByTitleCode(titleCode);
		}
		// 検索失敗
		if (CollectionUtils.isEmpty(stvIdmfTitleMstList)) {
			return null;
		}

		return stvIdmfTitleMstList.get(0);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Page<ImTitleMst> searchTitle(ImTitleMst titleMst, Integer pageNum, Integer pageSize,
			List<String> sort) {

		// ページング情報の初期化
		Pageable pageable = new Pageable(pageNum, pageSize);

		// 検索条件を編集
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("titleMst", titleMst);
		map.put("locale", LocaleContextHolder.getLocale().toString());
		map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
		map.put("pageSize", pageable.getPageSize());
		map.put("sort", CommonUtils.makeOrders(sort));

		List<ImTitleMst> stvIdmfTitleMstList = null;
		if (localeEnabled.equals("on")) {
			// 全件件数を設定
			pageable.setTotal(idmfTitleMstMapper.selectCountByTitleMstByLocale(map).longValue());
			// 利用ロケールが指定されている場合
			stvIdmfTitleMstList = idmfTitleMstMapper.selectByTitleMstByLocale(map);
		}
		if (CollectionUtils.isEmpty(stvIdmfTitleMstList)) {
			// 全件件数を設定
			pageable.setTotal(idmfTitleMstMapper.selectCountByTitleMst(map).longValue());
			// 利用ロケールが指定されていない場合
			stvIdmfTitleMstList = idmfTitleMstMapper.selectByTitleMst(map);
		}

		// ページの初期化
		Page<ImTitleMst> page = new Page<ImTitleMst>(stvIdmfTitleMstList, pageable);
		// ページ情報
		return page;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public String getHistoryType() {
		return BaseDAO.HISTORY_TYPE_ON;
	}

}
