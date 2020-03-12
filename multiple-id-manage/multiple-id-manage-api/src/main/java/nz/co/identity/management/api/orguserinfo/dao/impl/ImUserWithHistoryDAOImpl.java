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
import nz.co.identity.management.api.logininfo.dao.ImUserLoginInfoDAO;
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;
import nz.co.identity.management.api.logininfo.mapper.ImUserLoginInfoMapper;
import nz.co.identity.management.api.orguserinfo.dao.ImUserMstDAO;
import nz.co.identity.management.api.orguserinfo.dao.ImUserNameMstDAO;
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.orguserinfo.entity.ImUserMst;
import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;
import nz.co.identity.management.api.orguserinfo.mapper.ImPositionMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImUserMstMapper;
import nz.co.identity.management.api.orguserinfo.mapper.ImUserNameMstMapper;

/**
 * Get user name information by access DB.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
public class ImUserWithHistoryDAOImpl implements ImUserMstDAO {

	/**
	 * serial Generator
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private SerialGenerator serialGenerator;

	/**
	 * idmfUserMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImUserMstMapper idmfUserMstMapper;

	/**
	 * idmfUserNameMstMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImUserNameMstMapper idmfUserNameMstMapper;

	/**
	 * idmfPositionMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImPositionMapper idmfPositionMapper;

	/**
	 * idmfUserLoginInfoMapper
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource
	private ImUserLoginInfoMapper idmfUserLoginInfoMapper;

	/**
	 * stvIdmfUserLoginInfoWithHistoryDAO
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource(name = "stvIdmfUserLoginInfoWithHistoryDAO")
	private ImUserLoginInfoDAO stvIdmfUserLoginInfoWithHistoryDAO;

	/**
	 * stvIdmfUserNameWithHistoryDAO
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	@Resource(name = "stvIdmfUserNameWithHistoryDAO")
	private ImUserNameMstDAO stvIdmfUserNameWithHistoryDAO;

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
	public ImUserMst registerUser(ImUserMst userMst) {

		// 役職コードチェック
		List<ImUserMst> stvIdmfUserMstList = idmfUserMstMapper.selectByUserCode(userMst.getUserCode());
		if (CollectionUtils.isEmpty(stvIdmfUserMstList)) {
			userMst.setVersionNo(0);
		} else {
			// 新規追加するレコードのコードと同じコードを持つレコードが存在し、有効期間が被る場合、例外をスローする。
			Date titleEndTime = userMst.getActiveEndTime();
			Date titleStartTime = userMst.getActiveStartTime();
			Date startTime = null;
			Date endTime = null;
			for (ImUserMst stvIdmfUserMst : stvIdmfUserMstList) {
				startTime = stvIdmfUserMst.getActiveStartTime();
				endTime = stvIdmfUserMst.getActiveEndTime();

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
			userMst.setVersionNo(idmfUserMstMapper.selectMaxVersion(userMst.getUserCode()) + 1);
		}

		// entityのidがnullの場合、自動採番してDBに登録
		if (StringUtils.isEmpty(userMst.getUserId())) {
			userMst.setUserId(serialGenerator.selectSerial(ImUserMst.SERIAL_TABLE));
		} else {

			// 重複するidを登録する場合に、例外をthrow
			if (idmfUserMstMapper.selectByPrimaryKey(userMst.getUserId()) != null) {
				throw new ImRuntimeException("The userId[" + userMst.getUserId() + "] has been exists in User.");
			}
		}

		userMst.setCreatedTime(CommonUtils.getSystemTime());
		if (StringUtils.isEmpty(userMst.getCreatedUser())) {
			userMst.setCreatedUser(CommonUtils.getLoginUser());
		}
		if (userMst.getActiveStartTime() == null) {
			userMst.setActiveStartTime(CommonUtils.getSystemTime());
		}
		userMst.setUpdatedTime(null);
		userMst.setUpdatedUser(null);
		userMst.setDeletedFlg(Short.valueOf("0"));
		if (idmfUserMstMapper.insert(userMst) == 0) {
			throw new ImRuntimeException("Insert user data into DB with failed.");
		}

		return userMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImUserMst updateUser(ImUserMst userMst) {
		// ユーザIDがユーザマスタに存在しない場合
		ImUserMst selectResult = idmfUserMstMapper.selectByPrimaryKey(userMst.getUserId());
		if (selectResult == null) {
			throw new ImRecordInexistenceException("The userId[" + userMst.getUserId() + "] is not in UserMst.");
		} else {
			if (selectResult.getDeletedFlg() == 1) {
				throw new ImRuntimeException("there are record which deletedFlg is 1.");
			}
			// 更新レコードのコードと更新対象レコードのコードが不一致の場合、例外をスローする。
			String code = userMst.getUserCode();
			if (code != null && !selectResult.getUserCode().equals(code)) {
				throw new ImRecordInexistenceException("There is a record with disaccorded code[" + code + "]");
			}
			// ユーザコードにより、バージョン 排他処理
			Integer inputVersionNo = userMst.getVersionNo();
			if (!selectResult.getVersionNo().equals(inputVersionNo)) {
				// ユーザコードにより、指定したバージョンがバージョンがユーザマスタに存在しない場合
				throw new ImOptimisticLockingFailureException("There is a record with exclusive error.");
			}

			userMst.setUpdatedTime(CommonUtils.getSystemTime());
			if (StringUtils.isEmpty(userMst.getUpdatedUser())) {
				userMst.setUpdatedUser(CommonUtils.getLoginUser());
			}
			// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
			userMst.setVersionNo(idmfUserMstMapper.selectMaxVersion(selectResult.getUserCode()) + 1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userMst", userMst);
			map.put("versionBase", inputVersionNo);
			// 更新失敗
			if (idmfUserMstMapper.updateByPrimaryKey(map) == 0) {
				throw new ImRuntimeException("Update User data into DB with failed.");
			}

		}

		return userMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean deleteUser(ImUserMst userMst) {
		// ユーザIDがユーザマスタに存在しない場合
		ImUserMst selectResult = idmfUserMstMapper.selectByPrimaryKey(userMst.getUserId());
		if (selectResult == null) {
			throw new ImRecordInexistenceException("The userId[" + userMst.getUserId() + "] is not in UserMst.");
		}
		// ユーザ名マスタ、ユーザ_ログイン情報、ポジションに該当するユーザIDが存在する場合
		List<ImUserNameMst> stvIdmfUserNameMstList = idmfUserNameMstMapper.selectByUserId(userMst.getUserId());
		List<ImPosition> stvIdmfPositionList = idmfPositionMapper.selectByUserId(userMst.getUserId());
		List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
				.selectByUserId(userMst.getUserId());
		if ((!CollectionUtils.isEmpty(stvIdmfUserNameMstList)) || (!CollectionUtils.isEmpty(stvIdmfPositionList))
				|| (!CollectionUtils.isEmpty(stvIdmfUserLoginInfoList))) {
			throw new ImRecordInexistenceException(
					"The userId[" + userMst.getUserId() + "] is in UserNameMst or Position or UserLoginInfo.");
		}
		// ユーザコードにより、バージョン 排他処理
		Integer inputVersionNo = userMst.getVersionNo();
		if (!selectResult.getVersionNo().equals(inputVersionNo)) {
			// ユーザコードにより、指定したバージョンがユーザマスタに存在しない場合
			throw new ImOptimisticLockingFailureException("There is a record with exclusive error.");
		}

		userMst.setUpdatedTime(CommonUtils.getSystemTime());
		if (StringUtils.isEmpty(userMst.getUpdatedUser())) {
			userMst.setUpdatedUser(CommonUtils.getLoginUser());
		}
		// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
		userMst.setVersionNo(idmfUserMstMapper.selectMaxVersion(selectResult.getUserCode()) + 1);
		userMst.setDeletedFlg(Short.valueOf("1"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userMst", userMst);
		map.put("versionBase", inputVersionNo);

		// 論理削除失敗
		if (idmfUserMstMapper.updateByPrimaryKeySelective(map) == 0) {
			throw new ImRuntimeException("Delete user data by logic from DB with failed.");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean deleteForceUser(ImUserMst userMst) {
		// ユーザIDがユーザマスタに存在しない場合
		if (idmfUserMstMapper.selectByPrimaryKey(userMst.getUserId()) == null) {
			throw new ImRecordInexistenceException("The userId[" + userMst.getUserId() + "] is not in UserMst.");
		}
		// ユーザ名マスタ、ユーザ_ログイン情報、ポジションに該当するユーザIDが存在する場合、入力ユーザIDにより、ユーザマスタと対応するユーザ名マスタ、ユーザ_ログイン情報、ポジションの情報を論理削除する。
		List<ImUserNameMst> stvIdmfUserNameMstList = idmfUserNameMstMapper.selectByUserId(userMst.getUserId());
		List<ImUserLoginInfo> stvIdmfUserLoginInfoList = idmfUserLoginInfoMapper
				.selectByUserId(userMst.getUserId());
		// ユーザ名マスタ
		if (!CollectionUtils.isEmpty(stvIdmfUserNameMstList)) {
			for (ImUserNameMst stvIdmfUserNameMst : stvIdmfUserNameMstList) {
				if (!stvIdmfUserNameWithHistoryDAO.deleteForceUserName(stvIdmfUserNameMst)) {
					throw new ImRuntimeException("Delete user name data by logic from DB with failed.");
				}
			}
		}

		// ユーザ_ログイン情報
		if (!CollectionUtils.isEmpty(stvIdmfUserLoginInfoList)) {
			for (ImUserLoginInfo stvIdmfUserLoginInfo : stvIdmfUserLoginInfoList) {
				if (!stvIdmfUserLoginInfoWithHistoryDAO.deleteForceUserLoginInfo(stvIdmfUserLoginInfo)) {
					throw new ImRuntimeException("Delete userLoginInfo name data by logic from DB with failed.");
				}
			}
		}

		userMst.setUpdatedTime(CommonUtils.getSystemTime());
		if (StringUtils.isEmpty(userMst.getUpdatedUser())) {
			userMst.setUpdatedUser(CommonUtils.getLoginUser());
		}
		// 更新するレコードと同じコードを持つレコードの中で最大のバージョン ＋1 の値を、バージョンに設定する。
		userMst.setVersionNo(idmfUserMstMapper.selectMaxVersion(userMst.getUserCode()) + 1);
		userMst.setDeletedFlg(Short.valueOf("1"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userMst", userMst);
		// 論理削除失敗
		if (idmfUserMstMapper.updateByPrimaryKeySelective(map) == 0) {
			throw new ImRuntimeException("Delete user data by logic from DB with failed.");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImUserMst getUser(String userId) {
		// ユーザ実例
		String locale = LocaleContextHolder.getLocale().toString();
		// 利用ロケールが指定されている場合
		ImUserMst stvIdmfUserMst = idmfUserMstMapper.selectByUserIdByLocale(userId, locale);
		if (stvIdmfUserMst == null) {
			// 利用ロケールが指定されていない場合
			stvIdmfUserMst = idmfUserMstMapper.selectByPrimaryKey(userId);
		}

		return stvIdmfUserMst;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public ImUserMst getUserByUserCode(String userCode) {

		String locale = LocaleContextHolder.getLocale().toString();
		// 利用ロケールが指定されている場合
		List<ImUserMst> stvIdmfUserMstList = idmfUserMstMapper.selectByUserCodeByLocale(userCode, locale);
		if (CollectionUtils.isEmpty(stvIdmfUserMstList)) {
			// 利用ロケールが指定されていない場合
			stvIdmfUserMstList = idmfUserMstMapper.selectByUserCode(userCode);
		}
		// 検索失敗
		if (CollectionUtils.isEmpty(stvIdmfUserMstList)) {
			return null;
		}

		return stvIdmfUserMstList.get(0);

	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Page<ImUserMst> searchUser(ImUserMst userMst, Integer pageNum, Integer pageSize,
			List<String> sort) {

		// ページング情報の初期化
		Pageable pageable = new Pageable(pageNum, pageSize);

		// 検索条件を編集
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userMst", userMst);
		map.put("locale", LocaleContextHolder.getLocale().toString());
		map.put("offsetNum", CommonUtils.getOffsetNum(pageNum, pageSize));
		map.put("pageSize", pageable.getPageSize());
		map.put("sort", CommonUtils.makeOrders(sort));

		List<ImUserMst> stvIdmfUserMstList = null;
		if (localeEnabled.equals("on")) {
			// 全件件数を設定
			pageable.setTotal(idmfUserMstMapper.selectCountByUserMstByLocale(map).longValue());
			// 利用ロケールが指定されている場合
			stvIdmfUserMstList = idmfUserMstMapper.selectByUserMstByLocale(map);
		}
		if (CollectionUtils.isEmpty(stvIdmfUserMstList)) {
			// 全件件数を設定
			pageable.setTotal(idmfUserMstMapper.selectCountByUserMst(map).longValue());
			// 利用ロケールが指定されていない場合
			stvIdmfUserMstList = idmfUserMstMapper.selectByUserMst(map);
		}

		// ページの初期化
		Page<ImUserMst> page = new Page<ImUserMst>(stvIdmfUserMstList, pageable);
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
