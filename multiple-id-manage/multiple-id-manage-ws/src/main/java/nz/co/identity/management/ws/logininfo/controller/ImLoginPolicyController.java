package nz.co.identity.management.ws.logininfo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.logininfo.entity.ImLoginPolicy;
import nz.co.identity.management.api.logininfo.service.ImLoginPolicyService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of loginpolicy information management
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_login_policies")
public class ImLoginPolicyController {

    /**
     * the statement of loginpolicy service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImLoginPolicyService loginPolicyService;

    /**
     * register loginpolicy information.<br>
     *
     * @param loginPolicy
     *        loginpolicy entity
     * @return loginpolicy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImLoginPolicy registerLoginPolicy(
            @RequestBody ImLoginPolicy loginPolicy) {

        return loginPolicyService.registerLoginPolicy(loginPolicy);
    }

    /**
     * register loginpolicy information in one time.<br>
     *
     *
     * @param loginPolicyList
     *        loginpolicy information list
     * @return registered loginpolicy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImLoginPolicy> registerBulkLoginPolicy(
            @RequestBody List<ImLoginPolicy> loginPolicyList) {

        List<ImLoginPolicy> iPosList = new ArrayList<ImLoginPolicy>();
        ImLoginPolicy retLoginPolicy = null;

        for (int i = 0; i < loginPolicyList.size(); i++) {
            retLoginPolicy = loginPolicyService
                    .registerLoginPolicy(loginPolicyList.get(i));
            iPosList.add(retLoginPolicy);
        }

        return iPosList;
    }

    /**
     * register loginpolicy information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered loginpolicy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImLoginPolicy> registerLoginPolicyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImLoginPolicy> iPosList = new ArrayList<ImLoginPolicy>();
        List<ImLoginPolicy> loginPolicyList = null;
        try {
            loginPolicyList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImLoginPolicy.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImLoginPolicy retLoginPolicy = null;

        for (int i = 0; i < loginPolicyList.size(); i++) {
            retLoginPolicy = loginPolicyService
                    .registerLoginPolicy(loginPolicyList.get(i));
            iPosList.add(retLoginPolicy);
        }
        return iPosList;
    }

    /**
     * update loginpolicy information by loginpolicy id.<br>
     *
     * @param loginPolicy
     *        loginpolicy entity
     * @return updated loginpolicy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImLoginPolicy updateLoginPolicy(
            @RequestBody ImLoginPolicy loginPolicy) {
        return loginPolicyService.updateLoginPolicy(loginPolicy);
    }

    /**
     * update loginpolicy informations by loginpolicy information list in one
     * time.<br>
     *
     * @param loginPolicyList
     *        the loginpolicy information list
     * @return updated loginpolicy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImLoginPolicy> updateBulkLoginPolicy(
            @RequestBody List<ImLoginPolicy> loginPolicyList) {

        List<ImLoginPolicy> iPosList = new ArrayList<ImLoginPolicy>();
        ImLoginPolicy retLoginPolicy = null;

        for (int i = 0; i < loginPolicyList.size(); i++) {
            retLoginPolicy = loginPolicyService
                    .updateLoginPolicy(loginPolicyList.get(i));
            iPosList.add(retLoginPolicy);
        }

        return iPosList;
    }

    /**
     * update loginpolicy information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated loginpolicy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImLoginPolicy> updateLoginPolicyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImLoginPolicy> iPosList = new ArrayList<ImLoginPolicy>();
        List<ImLoginPolicy> loginPlicyList = null;
        try {
            loginPlicyList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImLoginPolicy.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImLoginPolicy retLoginPolicy = null;

        for (int i = 0; i < loginPlicyList.size(); i++) {
            retLoginPolicy = loginPolicyService
                    .updateLoginPolicy(loginPlicyList.get(i));
            iPosList.add(retLoginPolicy);
        }
        return iPosList;
    }

    /**
     * delete loginpolicy information by loginpolicy id and version.<br>
     *
     * @param loginPolicyId
     *        the loginpolicy id
     * @param version
     *        version
     * @return deleted loginpolicy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{loginPolicyId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteLoginPolicy(@PathVariable String loginPolicyId,
            @PathVariable String version) {
        ImLoginPolicy loginPolicy = new ImLoginPolicy();
        loginPolicy.setLoginPolicyId(loginPolicyId);
        loginPolicy.setVersionNo(Integer.valueOf(version));
        return loginPolicyService.deleteLoginPolicy(loginPolicy);
    }

    /**
     * delete loginpolicy informations by loginpolicy id list in one time.<br>
     *
     * @param loginPolicyList
     *        the loginpolicy id list
     * @return deleted loginpolicy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkLoginPolicy(
            @RequestBody List<ImLoginPolicy> loginPolicyList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < loginPolicyList.size(); i++) {
            resultList.add(loginPolicyService
                    .deleteLoginPolicy(loginPolicyList.get(i)));
        }
        return resultList;
    }

    /**
     * delete loginpolicy information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted loginpolicy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteLoginPolicyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImLoginPolicy> loginPolicyList = null;
        try {
            loginPolicyList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImLoginPolicy.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < loginPolicyList.size(); i++) {
            resultList.add(loginPolicyService
                    .deleteLoginPolicy(loginPolicyList.get(i)));
        }
        return resultList;
    }

    /**
     * find loginpolicy information by loginpolicyid
     *
     * @param loginPolicyId
     *        the loginpolicy id
     * @return selected loginpolicy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{loginPolicyId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImLoginPolicy findLoginPolicy(
            @PathVariable String loginPolicyId) {
        return loginPolicyService.getLoginPolicy(loginPolicyId);
    }

    /**
     * find loginpolicy information by loginpolicycode
     *
     * @param loginpolicyCode
     *        the loginpolicy code
     * @return selected loginpolicy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{loginpolicyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImLoginPolicy findLoginPolicyByCode(
            @PathVariable String loginpolicyCode) {
        return loginPolicyService.getLoginPolicyByCode(loginpolicyCode);
    }

    /**
     * search loginpolicy information by loginpolicy
     *
     * @param loginPolicy
     *        the loginpolicy
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected loginpolicy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImLoginPolicy> searchLoginPolicy(
            @RequestBody ImLoginPolicy loginPolicy,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return loginPolicyService.searchLoginPolicy(loginPolicy, pageNo,
                pageSize, sort);
    }

}