/**
 * @(#)Application.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：StvWebStoreのサーバ側起動アプリケーション
 * クラス名：Application
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
package example.identity.management.webstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * StvWebStoreのサーバ側起動アプリケーション
 *
 * @since StvWebStore Ver.1.0
 */
@SpringBootApplication
public class Application{

    /**
     * アプリ起動
     *
     * @param args 起動時の引数
     * @since StvWebStore Ver.1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}