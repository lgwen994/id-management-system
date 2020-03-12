/**
 * @(#)Page.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：A page is a sublist of a list of objects.
 * クラス名：Page
 *
 *   ver     変更日         所属       担当者        変更内容
 * ──────────────────────────────────
 *  V1.0   2018/01/25      Neusoft    初版
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
package nz.co.identity.management.api.common.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

/**
 * A page is a sublist of a list of objects. It allows gain information about
 * the position of it in the containing entire list.
 * 
 * @param <T>
 *        the type of which the page consists.
 * @since Staveware Core Ver.5.3
 */
public class Page<T> {

    /**
     * the paging information
     * 
     * @since Staveware Core Ver.5.3
     */
    private final Pageable paging;

    /**
     * the data of this page
     * 
     * @since Staveware Core Ver.5.3
     */
    private final List<T> data = new ArrayList<T>();

    /**
     * Creates a new Page with the given content. This will result in the
     * created Page being identical to the entire List.
     * 
     * @param data
     *        the content of this page, must not be {@literal null}.
     * @param paging
     *        the paging information, can be {@literal null}.
     */
    public Page(List<T> data, Pageable paging) {
        Assert.notNull(data, "data must not be null!");
        this.data.addAll(data);
        this.paging = paging;
    }

    /**
     * get data
     * 
     * @return data
     * @since Staveware Core Ver.5.3
     */
    public List<T> getData() {
        return Collections.unmodifiableList(data);
    }

    /**
     * get paging
     * 
     * @return paging
     * @since Staveware Core Ver.5.3
     */
    public Pageable getPaging() {
        return paging;
    }

}
