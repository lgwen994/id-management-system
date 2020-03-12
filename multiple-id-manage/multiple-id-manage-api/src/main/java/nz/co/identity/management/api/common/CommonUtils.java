/**
 * @(#)CommonUtils.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the common utility
 * クラス名：CommonUtils
 *
 *   ver     変更日         所属       担当者        変更内容
 * ──────────────────────────────────
 *  V1.0   2017/10/08      Neusoft    初版
 *
 *┌─────────────────────────────────┐
 *│  本技術情報には当社の機密情報が含まれておりますので、当社の      │
 *│  書面による承諾がなく第三者に開示することはできません。          │
 *│  また、当社の承諾を得た場合であっても、本技術情報は外国為替      │
 *│  及び外国貿易管理法に定める特定技術に該当するため、非居住者に    │
 *│  提供する場合には、同法に基づく許可を要することがあります。      │
 *│                      東芝デジタルソリューションズ  株式会社      │
 *└─────────────────────────────────┘
 */
package nz.co.identity.management.api.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import nz.co.identity.management.api.common.page.Pageable;
import nz.co.identity.management.api.common.page.Sort;
import nz.co.identity.management.api.common.page.Sort.Direction;


/**
 * the common utility
 * 
 * @since Staveware Core Ver.5.3
 *
 */
public class CommonUtils {

	/**
	 * constructor
	 * 
	 * @since Staveware Core Ver.5.3
	 */
	private CommonUtils() {

	}

	/**
	 * System time getter
	 * 
	 * @return the current time
	 * @since Staveware Core Ver.5.3
	 */
	public static Date getSystemTime() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * LoginUser getter
	 * 
	 * @return user
	 * @since Staveware Core Ver.5.3
	 */
	public static String getLoginUser() {
		String username = null;
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null) {
//			username = (String) authentication.getPrincipal();
//		}
		return username;
	}

	/**
	 * offset number getter
	 * 
	 * @param pageNum  page number
	 * @param pageSize page size
	 * @return offset number
	 * @since Staveware Core Ver.5.3
	 */
	public static int getOffsetNum(Integer pageNum, Integer pageSize) {

		if (pageNum != null && pageSize == null) {
			throw new IllegalArgumentException("Page size must not be null when the page index must not be null!");
		}

		int offsetNum = 0;
		if (pageNum != null || pageSize != null) {
			if (pageNum == null) {
				pageNum = Pageable.DEFAULT_PAGE_NUM;
			}
			offsetNum = (pageNum.intValue() - 1) * pageSize.intValue();
		}
		return offsetNum;
	}

	/**
	 * make orders
	 * 
	 * @param sort list of sort
	 * @return orders
	 * @since Staveware Core Ver.5.3
	 */
	public static List<Sort> makeOrders(List<String> sort) {
		if (sort == null || sort.isEmpty()) {
			return null;
		}

		List<Sort> orders = new ArrayList<Sort>();
		Sort order;
		String trimItem;
		for (String item : sort) {
			trimItem = StringUtils.trimLeadingWhitespace(item);
			if (trimItem.startsWith("-")) {
				String subStr = trimItem.substring(1);
				order = new Sort(Direction.DESC, subStr);
			} else {
				order = new Sort(Direction.ASC, trimItem);
			}
			orders.add(order);
		}
		return orders;
	}

}
