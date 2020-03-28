/**
 * @(#)MstaccessApplication.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：MstaccessApplication.
 * クラス名：MstaccessApplication
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

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nz.co.identity.management.api.ImApiConfig;
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;

/**
 * MstaccessApplication.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@SpringBootApplication
@Import(ImApiConfig.class)
//@PropertySource(value = "classpath:staveware-id-manage-api.properties")
@MapperScan({ "nz.co.identity.management.api.common.mapper", "nz.co.identity.management.api.authzpolicy.mapper",
		"nz.co.identity.management.api.logininfo.mapper", "nz.co.identity.management.api.orguserinfo.mapper",
		"nz.co.identity.management.api.roleinfo.mapper" })
@RestController
public class ImApplication {

	/**
	 *
	 * main.<br>
	 *
	 * @param args arguments
	 *
	 * @since Staveware Core Ver.5.3
	 */
	public static void main(String[] args) {
		SpringApplication.run(ImApplication.class, args);
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String hello() {
		
		return "hello test";

	}
}
