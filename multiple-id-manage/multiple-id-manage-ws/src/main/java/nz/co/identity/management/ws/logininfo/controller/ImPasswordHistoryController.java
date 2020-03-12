package nz.co.identity.management.ws.logininfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nz.co.identity.management.api.logininfo.entity.ImPasswordHistory;
import nz.co.identity.management.api.logininfo.service.ImPasswordHistoryService;

/**
 * the controller class of password history information management
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_password_histories")
public class ImPasswordHistoryController {

    /**
     * the statement of password history service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordHistoryService passwordHistoryService;

    /**
     * select password history information by login id and company code.
     * 
     * @param loginId
     *        loginId
     * @param companyCode
     *        companyCode
     * @return selected passwordhistory information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{loginId}/company_code/{companyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImPasswordHistory> findPasswordHistory(
            @PathVariable String loginId, @PathVariable String companyCode) {

        ImPasswordHistory passwordHistory = new ImPasswordHistory();
        passwordHistory.setLoginId(loginId);
        passwordHistory.setCompanyCode(companyCode);
        return passwordHistoryService.getPasswordHistory(passwordHistory);

    }

}