/**
 * @(#)Pageable.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：pagination information.
 * クラス名：Pageable
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

import nz.co.identity.management.api.common.exception.ImRuntimeException;

/**
 * pagination information.
 *
 * @since Staveware Core Ver.5.3
 */
public class Pageable {

    /**
     * default page number
     *
     * @since Staveware Core Ver.5.3
     */
    public final static Integer DEFAULT_PAGE_NUM = Integer.valueOf(1);

    /**
     * total the total amount of items available. The total might be adapted
     * considering the length of the content given, if it is going to be the
     * content of the last page. This is in place to mitigate inconsistencies
     *
     * @since Staveware Core Ver.5.3
     */
    private long total;

    /**
     * page number
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer pageNum;

    /**
     * page size
     *
     * @since Staveware Core Ver.5.3
     */
    private Integer pageSize;

    /**
     * Creates a new Pageable. Pages are one indexed, thus providing 1 for
     * {@code page} will return the first page.
     *
     * @param pageNum
     *            must not be less than one.
     * @param pageSize
     *            must not be less than one.
     */
    public Pageable(Integer pageNum, Integer pageSize) {

        if (pageNum != null && pageSize == null) {
            throw new IllegalArgumentException("Page size must not be null when the page index must not be null!");
        }

        if (pageNum != null && pageNum.intValue() < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (pageSize != null && pageSize.intValue() < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        if (pageNum != null || pageSize != null) {
            if (pageNum == null) {
                this.pageNum = DEFAULT_PAGE_NUM;
            } else {
                this.pageNum = pageNum;
            }
            this.pageSize = pageSize;
        }
    }

    /**
     * total setter
     *
     * @param total
     *            the total to set
     * @since Staveware Core Ver.5.3
     */
    public void setTotal(long total) {
        this.total = total;
        // 存在しないページを指定した場合は、例外を返す
        countLastPageNum(total);
    }

    /**
     * get total
     *
     * @return total
     * @since Staveware Core Ver.5.3
     */
    public long getTotal() {
        return total;
    }

    /**
     * get page size
     *
     * @return pageSize
     * @since Staveware Core Ver.5.3
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * get page number
     *
     * @return pageNum
     * @since Staveware Core Ver.5.3
     */
    public Integer getPageNumber() {
        return pageNum;
    }

    /**
     * counting the last page number if the setting page number is less than the
     * last page number ,the exception is thrown.
     *
     * @param total
     *            total size
     * @since Staveware Core Ver.5.3
     */
    private void countLastPageNum(long total) {

        long lastPage = 0;
        if (this.pageSize != null) {
            if (total % this.pageSize.intValue() == 0) {
                lastPage = (total / this.pageSize.intValue());
            } else {
                lastPage = (total / this.pageSize.intValue()) + 1L;
            }
            if (lastPage != 0 && this.pageNum > lastPage) {
                throw new ImRuntimeException("Page index must not be more than " + lastPage + ".");
            }
        }

    }
}
