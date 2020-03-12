/**
 * @(#)StvPasswordEncoderGenerator.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the generator of password encoder component.
 * クラス名：StvPasswordEncoderGenerator
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
package nz.co.identity.management.api.common.password;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * the generator of password encoder component.
 *
 * @since Staveware Core Ver.5.3
 */
@Component
public class PasswordEncoderGenerator {

    /**
     * ApplicationContext
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ApplicationContext context;

    /**
     * password.bcrypt.strength
     */
    @Value("${password.bcrypt.strength:10}")
    private String strengthStr;

    /**
     * minimum length
     * 
     * @since Staveware Core Ver.5.3
     */
    private final static int LEAST_LENGTH = 4;
    /**
     * max length
     * 
     * @since Staveware Core Ver.5.3
     */
    private final static int MAX_LENGTH = 31;

    /**
     * get the password encoder
     * 
     * @return password encoder
     * @since Staveware Core Ver.5.3
     */
//    public PasswordEncoder getPasswordEncoder() {
//        PasswordEncoder passwordEncoder;
//        if (context.containsBean("stvPasswordEncoder")) {
//            try {
//                passwordEncoder = (PasswordEncoder) context
//                        .getBean("stvPasswordEncoder");
//            } catch (BeansException e) {
//                throw new MstaccessRuntimeException(
//                        "The bean which has name of 'stvPasswordEncoder', isn't implement PasswordEncoder interface.",
//                        e);
//            }
//        } else {
//            Integer strength = Integer.valueOf(strengthStr);
//            if (LEAST_LENGTH <= strength && strength <= MAX_LENGTH) {
//                passwordEncoder = new BCryptPasswordEncoder(strength);
//            } else {
//                throw new MstaccessRuntimeException(
//                        "The value of password.bcrypt.strength, isn't between 4 and 31.");
//            }
//
//        }
//        return passwordEncoder;
//    }
}
