/**
 * @(#)MstaccessValidationException.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：validaton's Exception
 * クラス名：MstaccessValidationException
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
package nz.co.identity.management.api.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * validaton's Exception
 *
 * @since Staveware Core Ver.5.3
 */
public class ImValidationException extends ImRuntimeException {

    /**
     * serialVersionUID
     *
     * @since Staveware Core Ver.5.3
     */
    private static final long serialVersionUID = 1L;

    /**
     * validaton's result
     *
     * @since Staveware Core Ver.5.3
     */
    private BindingResult result;

    /**
     * constructor
     *
     * @param result
     *        result
     * @since Staveware Core Ver.5.3
     */
    public ImValidationException(BindingResult result) {
        this.result = result;
    }

    /**
     * get the result
     *
     * @return result
     * @since Staveware Core Ver.5.3
     */
    public BindingResult getBindingResult() {
        return this.result;
    }

    /**
     * get the message
     *
     * @return message
     * @since Staveware Core Ver.5.3
     */
    public String getMessage() {
        List<ObjectError> list = this.result.getAllErrors();
        List<String> messages = new ArrayList<String>();
        for (ObjectError error : list) {
            messages.add(error.getDefaultMessage());
        }
        return messages.toString();
    }
}