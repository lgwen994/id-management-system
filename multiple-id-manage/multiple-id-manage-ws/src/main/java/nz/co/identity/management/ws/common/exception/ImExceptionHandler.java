/**
 * @(#)MstaccessExceptionHandler.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：MstaccessException's exceptionHandler.
 * クラス名：MstaccessExceptionHandler
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
package nz.co.identity.management.ws.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.ws.common.dataset.ErrorMessage;

/**
 * MstaccessException's exceptionHandler.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@ControllerAdvice(value = "jp.co.toshiba_sol.staveware.idmf.mstaccess")
public class ImExceptionHandler {

    /**
     * default code
     * 
     * @since Staveware Core Ver.5.3
     */
    private final static String DEFAULT_CODE = "500001";

    /**
     * default message
     * 
     * @since Staveware Core Ver.5.3
     */
    private final static String DEFAULT_MESSAGE = "Failed to processing of server.";

    /**
     * constructor
     * 
     * @since Staveware Core Ver.5.3
     */
    ImExceptionHandler() {
        errorCodeMap = new HashMap<String, String>();
        errorCodeMap.put("MstaccessOptimisticLockingFailureException",
                "400001");
        errorCodeMap.put("MstaccessValidationException", "400002");
        errorCodeMap.put("MstaccessRecordInexistenceException", "400003");
        errorCodeMap.put("MstaccessRuntimeException", "400004");
    }

    /**
     * the map of error code.
     *
     * @since Staveware Core Ver.5.3
     */
    protected Map<String, String> errorCodeMap;
    /**
     * the flag of whether return message of exception. default is false.
     *
     * @since Staveware Core Ver.5.3
     */
//    @Value("${idmf.containDetailMessage}")
    private String containDetailMessage;

    /**
     * system name.
     *
     * @since Staveware Core Ver.5.3
     */
//    @Value("${idmf.systemname}")
    private String systemName;

    /**
     * message source
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private MessageSource messageSource;

    /**
     *
     * handleUnexpectedServerError.<br>
     *
     * @param ex
     *        MstaccessServerException
     * @return error message
     *
     * @since Staveware Core Ver.5.3
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleUnexpectedServerError(Exception ex) {
        return makeErrorMessage(ex);
    }

    /**
     *
     * handle MstaccessRuntimeException Error.<br>
     *
     * @param ex
     *        MstaccessRequestException
     * @return error message
     *
     * @since Staveware Core Ver.5.3
     */
    @ExceptionHandler(ImRuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleRequestError(ImRuntimeException ex) {
        return makeErrorMessage(ex);
    }

    /**
     * handle HttpMessageNotReadableException Error
     * 
     * @param ex
     *        HttpMessageNotReadableException
     * @return error message
     * @since Staveware Core Ver.5.3
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleRequestError(HttpMessageNotReadableException ex) {
        return makeErrorMessage(ex);
    }

    /**
     * make error message
     * 
     * @param ex
     *        MstaccessException
     * @return error message
     * @since Staveware Core Ver.5.3
     */
    private ErrorMessage makeErrorMessage(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        String key = ex.getClass().getSimpleName();
        errorMessage.setErrorCode(getErrorCode(key));
        errorMessage.setFaultactor(systemName);
        errorMessage.setMessage(getMessageCode(key));
        if (containDetailMessage.equalsIgnoreCase("true")) {
            errorMessage.setDetail(ex.getMessage());
        }
        return errorMessage;
    }

    /**
     * get error code
     * 
     * @param key
     *        key
     * @return error code
     * @since Staveware Core Ver.5.3
     */
    private String getErrorCode(String key) {
        String code = errorCodeMap.get(key);
        if (StringUtils.isEmpty(code)) {
            code = DEFAULT_CODE;
        }
        return code;
    }

    /**
     * get error message
     * 
     * @param key
     *        key
     * @return error message
     * @since Staveware Core Ver.5.3
     */
    private String getMessageCode(String key) {
        return messageSource.getMessage(key, null, DEFAULT_MESSAGE, null);
    }
}
