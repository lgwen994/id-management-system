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
import nz.co.identity.management.api.logininfo.entity.ImPassword;
import nz.co.identity.management.api.logininfo.service.ImPasswordService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of password information management
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_passwords")
public class ImPasswordController {

    /**
     * the statement of password service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordService passwordService;

    /**
     * register password information.<br>
     *
     * @param password
     *        password entity
     * @return password information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImPassword registerPassword(
            @RequestBody ImPassword password) {

        return passwordService.registerPassword(password);
    }

    /**
     * register password information in one time.<br>
     *
     *
     * @param passwordList
     *        password information list
     * @return registered password information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPassword> registerBulkPassword(
            @RequestBody List<ImPassword> passwordList) {

        List<ImPassword> iPosList = new ArrayList<ImPassword>();
        ImPassword password = null;

        for (int i = 0; i < passwordList.size(); i++) {
            password = passwordService.registerPassword(passwordList.get(i));
            iPosList.add(password);
        }
        return iPosList;
    }

    /**
     * register password information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered password information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPassword> registerPasswordByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPassword> iPosList = new ArrayList<ImPassword>();
        List<ImPassword> passwordList = null;
        try {
            passwordList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPassword.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImPassword password = null;

        for (int i = 0; i < passwordList.size(); i++) {
            password = passwordService.registerPassword(passwordList.get(i));
            iPosList.add(password);
        }
        return iPosList;
    }

    /**
     * update password information by login id and company code.<br>
     *
     * @param password
     *        password entity
     * @return updated password information
     * 
     * @since Staveware Core Ver.5.3
     */
    // パスワード更新
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImPassword updatePassword(
            @RequestBody ImPassword password) {
        return passwordService.updatePassword(password);
    }

    /**
     * update password informations by password information list in one
     * time.<br>
     *
     * @param passwordList
     *        the password information list
     * @return updated password information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImPassword> updateBulkPassword(
            @RequestBody List<ImPassword> passwordList) {

        List<ImPassword> iPosList = new ArrayList<ImPassword>();
        ImPassword retPassword = null;

        for (int i = 0; i < passwordList.size(); i++) {
            retPassword = passwordService.updatePassword(passwordList.get(i));
            iPosList.add(retPassword);
        }

        return iPosList;
    }

    /**
     * update password information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated password information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPassword> updatePasswordByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPassword> iPosList = new ArrayList<ImPassword>();
        List<ImPassword> passwordList = null;
        try {
            passwordList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPassword.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImPassword retPassword = null;

        for (int i = 0; i < passwordList.size(); i++) {
            retPassword = passwordService.updatePassword(passwordList.get(i));
            iPosList.add(retPassword);
        }
        return iPosList;
    }

    /**
     * delete password information by login id and company code.<br>
     *
     * @param loginId
     *        the login id
     * @param companyCode
     *        the company code
     * @return deleted password information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{loginId}/company_code/{companyCode}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deletePassword(@PathVariable String loginId,
            @PathVariable String companyCode) {
        ImPassword password = new ImPassword();
        password.setLoginId(loginId);
        password.setCompanyCode(companyCode);
        return passwordService.deletePassword(password);
    }

    /**
     * delete password informations by login id and company code list in one
     * time.<br>
     *
     * @param passwordList
     *        the login id and company code list
     * @return deleted password information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkPassword(
            @RequestBody List<ImPassword> passwordList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < passwordList.size(); i++) {
            resultList.add(passwordService.deletePassword(passwordList.get(i)));
        }
        return resultList;
    }

    /**
     * delete password information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted password information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deletePasswordByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPassword> passwordList = null;
        try {
            passwordList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPassword.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < passwordList.size(); i++) {
            resultList.add(passwordService.deletePassword(passwordList.get(i)));
        }
        return resultList;
    }

    /**
     * find password information by loginid and companyCode
     *
     * @param loginId
     *        the login id
     * @param companyCode
     *        the company code
     * @return selected password information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{loginId}/company_code/{companyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImPassword findPasswordByLoginId(@PathVariable String loginId,
            @PathVariable String companyCode) {

        ImPassword password = new ImPassword();
        password.setLoginId(loginId);
        password.setCompanyCode(companyCode);
        return passwordService.getPassword(password);
    }

    /**
     * search password information by password
     *
     * @param password
     *        password entity
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected password information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImPassword> searchPassword(
            @RequestBody ImPassword password,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return passwordService.searchPassword(password, pageNo, pageSize, sort);
    }

}