/**
 * @(#)Sort.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：sort information.
 * クラス名：Sort
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

import org.springframework.util.StringUtils;

/**
 * Sort option for queries. You have to provide at least a list of properties to
 * sort for that must not include null or empty strings. The direction defaults
 * to DEFAULT_DIRECTION.
 *
 * @since Staveware Core Ver.5.3
 */
public class Sort {
    /**
     * DEFAULT_DIRECTION
     * 
     * @since Staveware Core Ver.5.3
     */
    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    /**
     * Direction
     * 
     * @since Staveware Core Ver.5.3
     */
    private final Direction direction;

    /**
     * property for sort key
     * 
     * @since Staveware Core Ver.5.3
     */
    private final String property;

    /**
     * Creates a new Sort instance. if order is null then order defaults to
     * Sort#DEFAULT_DIRECTION
     * 
     * @param property
     *        must not be null or empty.
     */
    public Sort(String property) {
        this(DEFAULT_DIRECTION, property);
    }

    /**
     * Creates a new Sort instance. if Sort is {@literal null} then Sort
     * defaults to {@link Sort#DEFAULT_DIRECTION}
     * 
     * @param direction
     *        can be {@literal null}, will default to
     *        {@link Sort#DEFAULT_DIRECTION}
     * @param property
     *        must not be {@literal null} or empty.
     * @since Staveware Core Ver.5.3
     */
    public Sort(Direction direction, String property) {

        if (!StringUtils.hasText(property)) {
            throw new IllegalArgumentException(
                    "Property must not null or empty!");
        }

        if (direction == null) {
            this.direction = DEFAULT_DIRECTION;
        } else {
            this.direction = direction;
        }
        this.property = property;
    }

    /**
     * Returns the order the property shall be sorted for.
     * 
     * @return the order the property
     * @since Staveware Core Ver.5.3
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the property to order for.
     * 
     * @return property
     * @since Staveware Core Ver.5.3
     */
    public String getProperty() {
        return property;
    }

    /**
     * 
     * Direction
     * 
     * @since Staveware Core Ver.5.3
     */
    public enum Direction {
        /**
         * asc and desc
         * 
         * @since Staveware Core Ver.5.3
         */
        ASC, DESC;
    }
}
