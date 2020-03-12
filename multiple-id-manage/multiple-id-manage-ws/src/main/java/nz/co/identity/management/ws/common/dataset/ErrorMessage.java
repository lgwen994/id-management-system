/**
 * @(#)ErrorMessage.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：REST API error object.
 * クラス名：ErrorMessage
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
package nz.co.identity.management.ws.common.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * REST API error object.
 *
 * @since Staveware Core Ver.5.3
 */
@JsonInclude(Include.NON_NULL)
public class ErrorMessage {

    /**
     * error code
     *
     * @since Staveware Core Ver.5.3
     */
    private String errorCode;

    /**
     * error message
     *
     * @since Staveware Core Ver.5.3
     */
    private String message;

    /**
     * the detail of error message
     *
     * @since Staveware Core Ver.5.3
     */
    private String detail;

    /**
     * fault actor of error
     *
     * @since Staveware Core Ver.5.3
     */
    private String faultactor;

    /**
     * get error code
     *
     * @return error code
     *
     * @since Staveware Core Ver.5.3
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * set error code
     *
     * @param errorCode
     *        error code
     *
     * @since Staveware Core Ver.5.3
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * get error message
     *
     * @return error message
     *
     * @since Staveware Core Ver.5.3
     */
    public String getMessage() {
        return message;
    }

    /**
     * set error message
     *
     * @param message
     *        error message
     *
     * @since Staveware Core Ver.5.3
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * get error fault actor
     *
     * @return error fault actor
     *
     * @since Staveware Core Ver.5.3
     */
    public String getFaultactor() {
        return faultactor;
    }

    /**
     * set error fault actor
     *
     * @param faultactor
     *        error fault actor
     *
     * @since Staveware Core Ver.5.3
     */
    public void setFaultactor(String faultactor) {
        this.faultactor = faultactor;
    }

    /**
     * get the detail of error message
     *
     * @return the detail of error message
     *
     * @since Staveware Core Ver.5.3
     */
    public String getDetail() {
        return detail;
    }

    /**
     * set the detail of error message
     *
     * @param detail
     *        the detail of error message
     *
     * @since Staveware Core Ver.5.3
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
