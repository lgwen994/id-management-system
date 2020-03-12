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
import nz.co.identity.management.api.orguserinfo.dao.ImOrgMstDAO;
import nz.co.identity.management.api.orguserinfo.dao.ImOrgNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;
import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.orguserinfo.mapper.ImCompanyMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImOrgMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImOrgNameMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImPositionMapper;

/**
 *
 * the implementation class of org's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service(value = "stvIdmfOrgWithoutHistoryDAO")
public class ImOrgWithoutHistoryDAOImpl implements ImOrgMstDAO {
	/**
	 * serial Generator
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private SerialGenerator serialGenerator;

	/**
	 * IdmfOrgMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImOrgMstMapper stvIdmfOrgMstMapper;

	/**
	 * IdmfOrgNameMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImOrgNameMstMapper stvIdmfOrgNameMstMapper;

	/**
	 * IdmfPositionOrgMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImPositionMapper stvIdmfPositionMapper;

	/**
	 * IdmfCompanyMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImCompanyMstMapper stvIdmfCompanyMstMapper;

	/**
	 * stvIdmfPositionOrgWithHistoryDAO
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource(name = "stvIdmfOrgNameMstWithoutHistoryDAO")
	private ImOrgNameMstDAO stvIdmfOrgNameMstWithoutHistoryDAO;

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
	public ImOrgMst registerOrg(ImOrgMst orgMst) {

		// entityのidがnullの場合、自動採番してDBに登録
		if (StringUtils.isEmpty(orgMst.getOrgId())) {
			orgMst.setOrgId(serialGenerator.selectSerial(ImOrgMst.SERIAL_TABLE));
		} else {

			// 重複するidを登録する場合に、例外をthrow
			if (stvIdmfOrgMstMapper.selectByPrimaryKey(orgMst.getOrgId()) != null) {
				throw new ImRuntimeException("The orgId[" + orgMst.getOrgId() + "] has been exists in Org.");
			}
		}

		orgMst.setCreatedTime(CommonUtils.getSystemTime());
		if (StringUtils.isEmpty(orgMst.getCreatedUser())) {
			orgMst.setCreatedUser(CommonUtils.getLoginUser());
		}
		if (orgMst.getActiveStartTime() == null) {
			orgMst.setActiveStartTime(CommonUtils.getSystemTime());
		}
		orgMst.setUpdatedTime(null);
		orgMst.setUpdatedUser(null);
		// 会社IDが会社マスタに存在しない場合
		if (stvIdmfCompanyMstMapper.selectByPrimaryKey(orgMst.getCompanyId()) == null) {
			throw new ImRecordInexistenceException(
					"The companyId[" + orgMst.getCompanyId() + "] is not in Company.");
		}

		// 組織コードチェック
		List<ImOrgMst> stvIdmfOrgMstList = stvIdmfOrgMstMapper.selectByOrgCode(orgMst.getOrgCode());
		if (CollectionUtils.isEmpty(stvIdmfOrgMstList)) {
			orgMst.setVersionNo(0);
			orgMst.setDeletedFlg(Short.valueOf("0"));
			if (stvIdmfOrgMstMapper.insert(orgMst) == 0) {
				throw new ImRuntimeException("Insert org data into DB with failed.");
			}
		} else {
			throw new ImRuntimeException("The same code with failed.");
		}

		return orgMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImOrgMst updateOrg(ImOrgMst orgMst) {

		// 会社IDが会社マスタに存在しない場合
		if (stvIdmfCompanyMstMapper.selectByPrimaryKey(orgMst.getCompanyId()) == null) {
			throw new ImRecordInexistenceException(
					"The companyId[" + orgMst.getCompanyId() + "] is not in Company.");
		}

		// 組織IDが組織マスタに存在しない場合
		ImOrgMst selectResult = stvIdmfOrgMstMapper.selectByPrimaryKey(orgMst.getOrgId());
		if (selectResult == null) {
			throw new ImRecordInexistenceException("The orgId[" + orgMst.getOrgId() + "] is not in OrgMst.");
		} else {
			if (selectResult.getDeletedFlg() == 1) {
				throw new ImRuntimeException("there are record which deletedFlg is 1.");
			}
			// 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
			String code = orgMst.getOrgCode();
			if (code != null && !selectResult.getOrgCode().equals(code)) {
				throw new ImRecordInexistenceException("There is a record with disaccorded code[" + code + "]");
			}

			// 組織コードにより、バージョン 排他処理
			Integer inputVersionNo = orgMst.getVersionNo();
			if (!selectResult.getVersionNo().equals(inputVersionNo)) {
				// 組織コードにより、指定したバージョンがバージョンが組織マスタに存在しない場合
				throw new ImOptimisticLockingFailureException("There is a record with exclusive error.");
			}

			orgMst.setUpdatedTime(CommonUtils.getSystemTime());
			if (StringUtils.isEmpty(orgMst.getUpdatedUser())) {
				orgMst.setUpdatedUser(CommonUtils.getLoginUser());
			}
			// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
			orgMst.setVersionNo(stvIdmfOrgMstMapper.selectMaxVersion(selectResult.getOrgCode()) + 1);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgMst", orgMst);
			map.put("versionBase", inputVersionNo);

			// 更新失敗
			if (stvIdmfOrgMstMapper.updateByPrimaryKey(map) == 0) {
				throw new ImRuntimeException("Update org data into DB with failed.");
			}
		}

		return orgMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean deleteOrg(ImOrgMst orgMst) {
		// 組織IDが組織マスタに存在しない場合
		ImOrgMst selectResult = stvIdmfOrgMstMapper.selectByPrimaryKey(orgMst.getOrgId());
		if (selectResult == null) {
			throw new ImRecordInexistenceException("The orgId[" + orgMst.getOrgId() + "] is not in OrgMst.");
		}

		// 組織名マスタ、ポジション_組織に該当する組織IDが存在する場合
		List<ImOrgNameMst> stvIdmfOrgNameMstList = stvIdmfOrgNameMstMapper.selectByOrgId(orgMst.getOrgId());
		List<ImPosition> stvIdmfPositionList = stvIdmfPositionMapper.selectByOrgId(orgMst.getOrgId());
		if ((!CollectionUtils.isEmpty(stvIdmfOrgNameMstList)) || (!CollectionUtils.isEmpty(stvIdmfPositionList))) {
			throw new ImRecordInexistenceException(
					"The orgId[" + orgMst.getOrgId() + "] is in OrgNameMst or PositionOrg or orgHierarchy.");
		}

		// 組織IDにより、バージョン 排他処理
		Integer inputVersionNo = orgMst.getVersionNo();
		if (!selectResult.getVersionNo().equals(inputVersionNo)) {
			// 組織IDにより、指定したバージョンが組織マスタに存在しない場合
			throw new ImOptimisticLockingFailureException("There is a record with exclusive error.");
		}

		// 削除失敗
		if (stvIdmfOrgMstMapper.deleteByOrgIdByVersion(orgMst.getOrgId(), orgMst.getVersionNo()) == 0) {
			throw new ImRuntimeException("Delete org data from DB with failed.");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean deleteForceOrg(ImOrgMst orgMst) {
		// 組織IDが組織マスタに存在しない場合
		if (stvIdmfOrgMstMapper.selectByPrimaryKey(orgMst.getOrgId()) == null) {
			throw new ImRecordInexistenceException("The orgId[" + orgMst.getOrgId() + "] is not in OrgMst.");
		}

		// 組織名マスタ、ポジション_組織に該当する組織IDが存在する場合、入力組織IDにより、組織マスタと対応する組織名マスタ、ポジション_組織の情報を物理削除する。
		List<ImOrgNameMst> stvIdmfOrgNameMstList = stvIdmfOrgNameMstMapper.selectByOrgId(orgMst.getOrgId());

		if (!CollectionUtils.isEmpty(stvIdmfOrgNameMstList)) {
			ImOrgNameMst orgNameMst = new ImOrgNameMst();
			orgNameMst.setOrgId(orgMst.getOrgId());
			// 削除失敗
			if (!stvIdmfOrgNameMstWithoutHistoryDAO.deleteOrgName(orgNameMst)) {
				throw new ImRuntimeException("Delete org name from DB with failed.");
			}
		}

		// 削除失敗
		if (stvIdmfOrgMstMapper.deleteByPrimaryKey(orgMst.getOrgId()) == 0) {
			throw new ImRuntimeException("Delete org data from DB with failed.");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImOrgMst getOrg(String orgId) {

		// 利用ロケールが指定されている場合
		ImOrgMst stvIdmfOrgMst = stvIdmfOrgMstMapper.selectByOrgIdByLocale(orgId,
				LocaleContextHolder.getLocale().toString());
		if (stvIdmfOrgMst == null) {
			// 利用ロケールが指定されていない場合
			stvIdmfOrgMst = stvIdmfOrgMstMapper.selectByPrimaryKey(orgId);
		}
		return stvIdmfOrgMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImOrgMst getOrgByOrgCode(String orgCode) {

		// 利用ロケールが指定されている場合
		List<ImOrgMst> stvIdmfOrgMstList = stvIdmfOrgMstMapper.selectByOrgCodeByLocale(orgCode,
				LocaleContextHolder.getLocale().toString());
		if (CollectionUtils.isEmpty(stvIdmfOrgMstList)) {
			// 利用ロケールが指定されていない場合
			stvIdmfOrgMstList = stvIdmfOrgMstMapper.selectByOrgCode(orgCode);
		}
		// 検索失敗
		if (CollectionUtils.isEmpty(stvIdmfOrgMstList)) {
			return null;
		}
		return stvIdmfOrgMstList.get(0);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Page<ImOrgMst> searchOrg(ImOrgMst orgMst, Integer pageNum, Integer pageSize, List<String> sort) {

		// ページング情報の初期化
		Pageable pageable = new Pageable(pageNum, pageSize);

		// 検索条件を編集
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgMst", orgMst);
		map.put("locale", LocaleContextHolder.getLocale().toString());
		map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
		map.put("pageSize", pageable.getPageSize());
		map.put("sort", CommonUtils.makeOrders(sort));

		List<ImOrgMst> stvIdmfOrgMstList = null;
		if (localeEnabled.equals("on")) {
			// 全件件数を設定
			pageable.setTotal(stvIdmfOrgMstMapper.selectCountByOrgMstByLocale(map).longValue());
			// 利用ロケールが指定されている場合
			stvIdmfOrgMstList = stvIdmfOrgMstMapper.selectByOrgMstByLocale(map);
		}
		if (CollectionUtils.isEmpty(stvIdmfOrgMstList)) {
			// 全件件数を設定
			pageable.setTotal(stvIdmfOrgMstMapper.selectCountByOrgMst(map).longValue());
			// 利用ロケールが指定されていない場合
			stvIdmfOrgMstList = stvIdmfOrgMstMapper.selectByOrgMst(map);
		}

		// ページの初期化
		Page<ImOrgMst> page = new Page<ImOrgMst>(stvIdmfOrgMstList, pageable);
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
