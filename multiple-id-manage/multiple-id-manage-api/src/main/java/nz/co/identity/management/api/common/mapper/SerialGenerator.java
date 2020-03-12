/**
 * @(#)SerialGenerator.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the interface of generating the serial.
 * クラス名：SerialGenerator
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
package nz.co.identity.management.api.common.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * the interface of generating the serial.
 * 
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface SerialGenerator {

    /**
     * generate the serial.
     * 
     * @param tableName
     *        table name
     * @return serial
     * @since Staveware Core Ver.5.3
     */
    String selectSerial(String tableName);
}
