/**
 * @(#)AppServletInitializer.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the class for deploying with web server.
 * クラス名：AppServletInitializer
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
package nz.co.identity.management.ws;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * for deploying with web server.
 *
 * @since Staveware Core Ver.5.3
 */
@Configuration
public class AppServletInitializer extends SpringBootServletInitializer {
    /**
     * {@inheritDoc}
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(ImApplication.class);
    }
}