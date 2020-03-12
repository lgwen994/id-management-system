/**
 * @(#)PasswordPolicyChecker.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：the checker of password policy.
 * クラス名：PasswordPolicyChecker
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

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * the checker of password policy.
 *
 * @since Staveware Core Ver.5.3
 */
@Component
public class PasswordPolicyChecker {

//    /**
//     * policy.password.id
//     * 
//     * @since Staveware Core Ver.5.3
//     */
//    @Value("${policy.password.id}")
//    private String pwdPolicyId;
//
//    /**
//     * pwdPolicyId setter
//     * 
//     * @param pwdPolicyId
//     *        pwdPolicyId
//     * @since Staveware Core Ver.5.3
//     */
//    public void setPwdPolicyId(String pwdPolicyId) {
//        if (!StringUtils.isEmpty(pwdPolicyId)) {
//            this.pwdPolicyId = pwdPolicyId;
//        }
//    }
//
//    /**
//     * passwordHistoryMapper
//     * 
//     * @since Staveware Core Ver.5.3
//     */
//    @Resource
//    private IdmfPasswordHistoryMapper passwordHistoryMapper;
//
//    /**
//     * passwordMapper
//     * 
//     * @since Staveware Core Ver.5.3
//     */
//    @Resource
//    private IdmfPasswordMapper passwordMapper;
//    /**
//     * passwordPolicyMapper
//     * 
//     * @since Staveware Core Ver.5.3
//     */
//    @Resource
//    private IdmfPasswordPolicyMapper passwordPolicyMapper;
//    /**
//     * passwordEncoder
//     * 
//     * @since Staveware Core Ver.5.3
//     */
//    private PasswordEncoder passwordEncoder;
//
//    /**
//     * setter of passwordEncoder
//     * 
//     * @param passwordEncoder
//     *        passwordEncoder
//     * @since Staveware Core Ver.5.3
//     */
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    /**
//     * check password policy
//     * 
//     * @param idmfpassword
//     *        idmfpassword object
//     * @return true: comply with the password policy; false: don't comply with
//     *         the password policy.default value: true
//     * @since Staveware Core Ver.5.3
//     */
//    public boolean checkPasswordPolicy(StvIdmfPassword idmfpassword) {
//        boolean result = true;
//        StvIdmfPasswordPolicy pwdPolicy = passwordPolicyMapper
//                .selectByPrimaryKey(pwdPolicyId);
//        if (pwdPolicy != null) {
//            String inputPassword = idmfpassword.getPassword();
//
//            // パスワードに設定可能な最低文字数
//            boolean passwordMinLengthResult = (pwdPolicy.getPasswordMinLength()
//                    .compareTo((short) inputPassword.length()) < 1);
//
//            // パスワードで利用可能な文字種類
//            String[] passwordLetterType = pwdPolicy.getPasswordLetterType()
//                    .split(",");
//            StringBuffer passwordLetterTypeRegex = new StringBuffer("[");
//            for (String item : passwordLetterType) {
//                passwordLetterTypeRegex.append(item);
//            }
//            passwordLetterTypeRegex.append("]+");
//            boolean passwordLetterTypeResult = Pattern
//                    .matches(passwordLetterTypeRegex.toString(), inputPassword);
//
//            // パスワード最低文字種
//            int passwordLetterTypeCount = 0;
//            for (String item : passwordLetterType) {
//                if (processLetterTypeToRegexAndJudge(inputPassword, item)) {
//                    passwordLetterTypeCount++;
//                }
//            }
//            boolean passwordMinLetterTypeResult = (pwdPolicy
//                    .getPasswordMinLetterType()
//                    .compareTo((short) passwordLetterTypeCount) < 1);
//
//            // パスワード履歴数
//            StvIdmfPasswordHistory passwordHistory = new StvIdmfPasswordHistory();
//            passwordHistory.setLoginId(idmfpassword.getLoginId());
//            passwordHistory.setCompanyCode(idmfpassword.getCompanyCode());
//            List<StvIdmfPasswordHistory> passwordHistories = passwordHistoryMapper
//                    .selectByPasswordHistory(passwordHistory);
//            boolean passwordHistorysResult = true;
//            if (CollectionUtils.isEmpty(passwordHistories)) {
//                StvIdmfPassword dbIdmfPassword = passwordMapper
//                        .selectByPrimaryKey(idmfpassword);
//                if (dbIdmfPassword != null) {
//                    passwordHistorysResult = !passwordEncoder.matches(
//                            inputPassword, dbIdmfPassword.getPassword());
//                }
//            } else {
//                List<StvIdmfPasswordHistory> subPasswordHistories = passwordHistories
//                        .subList(0, Math.min(passwordHistories.size(),
//                                pwdPolicy.getPasswordInHistory()));
//                for (StvIdmfPasswordHistory item : subPasswordHistories) {
//                    passwordHistorysResult = !passwordEncoder
//                            .matches(inputPassword, item.getPasswordHistory());
//                    if (!passwordHistorysResult) {
//                        break;
//                    }
//                }
//            }
//            result = passwordMinLengthResult && passwordLetterTypeResult
//                    && passwordMinLetterTypeResult && passwordHistorysResult;
//        }
//
//        return result;
//    }
//
//    /**
//     * process letter type to regex
//     *
//     * @param password
//     *        password
//     * @param letterType
//     *        letterType
//     * @return true or false.
//     * @since Staveware Core Ver.5.3
//     */
//    private boolean processLetterTypeToRegexAndJudge(String password,
//            String letterType) {
//
//        String subStrRegex = ".*";
//        String letterTypeRegex = subStrRegex + "[" + letterType + "]+"
//                + subStrRegex;
//
//        return password.matches(letterTypeRegex);
//    }
}
