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
import nz.co.identity.management.api.logininfo.entity.ImPasswordPolicy;
import nz.co.identity.management.api.logininfo.service.ImPasswordPolicyService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of password policy information management
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_password_policies")
public class ImPasswordPolicyController {

    /**
     * the statement of password policy service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordPolicyService passwordPolicyService;

    /**
     * register password policy information.<br>
     *
     * @param passwordPolicy
     *        password policy entity
     * @return password policy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImPasswordPolicy registerPasswordPolicy(
            @RequestBody ImPasswordPolicy passwordPolicy) {

        return passwordPolicyService.registerPasswordPolicy(passwordPolicy);
    }

    /**
     * register password policy information in one time.<br>
     *
     *
     * @param passwordPolicyList
     *        password policy information list
     * @return registered password policy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPasswordPolicy> registerBulkPasswordPolicy(
            @RequestBody List<ImPasswordPolicy> passwordPolicyList) {

        List<ImPasswordPolicy> iPosList = new ArrayList<ImPasswordPolicy>();
        ImPasswordPolicy retPasswordPolicy = null;

        for (int i = 0; i < passwordPolicyList.size(); i++) {
            retPasswordPolicy = passwordPolicyService
                    .registerPasswordPolicy(passwordPolicyList.get(i));
            iPosList.add(retPasswordPolicy);
        }

        return iPosList;
    }

    /**
     * register password policy information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered password policy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPasswordPolicy> registerPasswordPolicyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPasswordPolicy> iPosList = new ArrayList<ImPasswordPolicy>();
        List<ImPasswordPolicy> passwordPolicyList = null;
        try {
            passwordPolicyList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPasswordPolicy.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImPasswordPolicy retPasswordPolicy = null;

        for (int i = 0; i < passwordPolicyList.size(); i++) {
            retPasswordPolicy = passwordPolicyService
                    .registerPasswordPolicy(passwordPolicyList.get(i));
            iPosList.add(retPasswordPolicy);
        }
        return iPosList;
    }

    /**
     * update password policy information by passwordpolicy id.<br>
     *
     * @param passwordPolicy
     *        password policy entity
     * @return updated password policy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImPasswordPolicy updatePasswordPolicy(
            @RequestBody ImPasswordPolicy passwordPolicy) {
        return passwordPolicyService.updatePasswordPolicy(passwordPolicy);
    }

    /**
     * update password policy informations by password policy information list
     * in one time.<br>
     *
     * @param passwordPolicyList
     *        the password policy information list
     * @return updated password policy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImPasswordPolicy> updateBulkPasswordPolicy(
            @RequestBody List<ImPasswordPolicy> passwordPolicyList) {

        List<ImPasswordPolicy> iPosList = new ArrayList<ImPasswordPolicy>();
        ImPasswordPolicy retPasswordPolicy = null;

        for (int i = 0; i < passwordPolicyList.size(); i++) {
            retPasswordPolicy = passwordPolicyService
                    .updatePasswordPolicy(passwordPolicyList.get(i));
            iPosList.add(retPasswordPolicy);
        }

        return iPosList;
    }

    /**
     * update password policy information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated password policy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPasswordPolicy> updatePasswordPolicyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPasswordPolicy> iPosList = new ArrayList<ImPasswordPolicy>();
        List<ImPasswordPolicy> passwordPolicyList = null;
        try {
            passwordPolicyList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPasswordPolicy.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImPasswordPolicy retPasswordPolicy = null;

        for (int i = 0; i < passwordPolicyList.size(); i++) {
            retPasswordPolicy = passwordPolicyService
                    .updatePasswordPolicy(passwordPolicyList.get(i));
            iPosList.add(retPasswordPolicy);
        }
        return iPosList;
    }

    /**
     * delete password policy information by password policy id and version.<br>
     *
     * @param passwordPolicyId
     *        passwordPolicyId
     * @param version
     *        version
     * @return deleted password policy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{passwordPolicyId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deletePasswordPolicy(@PathVariable String passwordPolicyId,
            @PathVariable String version) {
        ImPasswordPolicy passwordPolicy = new ImPasswordPolicy();
        passwordPolicy.setPasswordPolicyId(passwordPolicyId);
        passwordPolicy.setVersionNo(Integer.valueOf(version));
        return passwordPolicyService.deletePasswordPolicy(passwordPolicy);
    }

    /**
     * delete password policy informations by password policy id list in one
     * time.<br>
     *
     * @param passwordPolicyList
     *        the password policy id list
     * @return deleted password policy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkPasswordPolicy(
            @RequestBody List<ImPasswordPolicy> passwordPolicyList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < passwordPolicyList.size(); i++) {
            resultList.add(passwordPolicyService
                    .deletePasswordPolicy(passwordPolicyList.get(i)));
        }
        return resultList;
    }

    /**
     * delete password policy information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted password policy information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deletePasswordPolicyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPasswordPolicy> passwordPolicyList = null;
        try {
            passwordPolicyList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPasswordPolicy.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < passwordPolicyList.size(); i++) {
            resultList.add(passwordPolicyService
                    .deletePasswordPolicy(passwordPolicyList.get(i)));
        }
        return resultList;
    }

    /**
     * find password policy information by password policy id
     *
     * @param passwordPolicyId
     *        the password policy id
     * @return selected password policy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{passwordPolicyId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImPasswordPolicy findPasswordPolicy(
            @PathVariable String passwordPolicyId) {

        return passwordPolicyService.getPasswordPolicy(passwordPolicyId);
    }

    /**
     * find password policy information by password policy code
     *
     * @param passwordPolicyCode
     *        the password policy code
     * @return selected password policy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{passwordPolicyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImPasswordPolicy findPasswordPolicyByCode(
            @PathVariable String passwordPolicyCode) {

        return passwordPolicyService
                .getPasswordPolicyByCode(passwordPolicyCode);
    }

    /**
     * search password policy information by password policy
     *
     * @param passwordPolicy
     *        the password policy
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected password policy information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImPasswordPolicy> searchPasswordPolicy(
            @RequestBody ImPasswordPolicy passwordPolicy,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return passwordPolicyService.searchPasswordPolicy(passwordPolicy,
                pageNo, pageSize, sort);
    }

}