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
import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;
import nz.co.identity.management.api.logininfo.service.ImLoginControlInfoService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;


/**
 * the controller class of login control info management
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_login_control_infos")
public class ImLoginControlInfoController {

    /**
     * the statement of login control info service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImLoginControlInfoService loginControlInfoService;

    /**
     * register login control info.<br>
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return positionrole information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImLoginControlInfo registerLoginControlInfo(
            @RequestBody ImLoginControlInfo loginControlInfo) {
        return loginControlInfoService
                .registerLoginControlInfo(loginControlInfo);
    }

    /**
     * register login control info in one time.<br>
     *
     *
     * @param loginControlInfoList
     *        logincontrolinfo information list
     * @return registered logincontrolinfo information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImLoginControlInfo> registerBulkLoginControlInfo(
            @RequestBody List<ImLoginControlInfo> loginControlInfoList) {

        List<ImLoginControlInfo> iPosList = new ArrayList<ImLoginControlInfo>();
        ImLoginControlInfo loginControlInfo = null;

        for (int i = 0; i < loginControlInfoList.size(); i++) {
            loginControlInfo = loginControlInfoService
                    .registerLoginControlInfo(loginControlInfoList.get(i));
            iPosList.add(loginControlInfo);
        }
        return iPosList;
    }

    /**
     * register login control info by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered logincontrolinfo information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImLoginControlInfo> registerLoginControlInfoByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImLoginControlInfo> iPosList = new ArrayList<ImLoginControlInfo>();
        List<ImLoginControlInfo> loginControlInfoList = null;
        try {
            loginControlInfoList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImLoginControlInfo.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImLoginControlInfo loginControlInfo = null;

        for (int i = 0; i < loginControlInfoList.size(); i++) {
            loginControlInfo = loginControlInfoService
                    .registerLoginControlInfo(loginControlInfoList.get(i));
            iPosList.add(loginControlInfo);
        }
        return iPosList;
    }

    /**
     * update login control info by login id.<br>
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @return updated logincontrolinfo information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImLoginControlInfo updateLoginControlInfo(
            @RequestBody ImLoginControlInfo loginControlInfo) {
        return loginControlInfoService.updateLoginControlInfo(loginControlInfo);
    }

    /**
     * update login control info by login information list in one time.<br>
     *
     * @param loginControlInfoList
     *        the logincontrolinfo information list
     * @return updated logincontrolinfo information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImLoginControlInfo> updateBulkLoginControlInfo(
            @RequestBody List<ImLoginControlInfo> loginControlInfoList) {

        List<ImLoginControlInfo> iPosList = new ArrayList<ImLoginControlInfo>();
        ImLoginControlInfo retLoginControlInfo = null;

        for (int i = 0; i < loginControlInfoList.size(); i++) {
            retLoginControlInfo = loginControlInfoService
                    .updateLoginControlInfo(loginControlInfoList.get(i));
            iPosList.add(retLoginControlInfo);
        }

        return iPosList;
    }

    /**
     * update login control info by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated logincontrolinfo information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImLoginControlInfo> updateLoginControlInfoByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImLoginControlInfo> iPosList = new ArrayList<ImLoginControlInfo>();
        List<ImLoginControlInfo> loginControlInfoList = null;
        try {
            loginControlInfoList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImLoginControlInfo.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImLoginControlInfo retLoginControlInfo = null;

        for (int i = 0; i < loginControlInfoList.size(); i++) {
            retLoginControlInfo = loginControlInfoService
                    .updateLoginControlInfo(loginControlInfoList.get(i));
            iPosList.add(retLoginControlInfo);
        }
        return iPosList;
    }

    /**
     * delete login control info information by login id and companyCode.<br>
     *
     * @param loginId
     *        loginId
     * @param companyCode
     *        companyCode
     * @return deleted logincontrolinfo information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{loginId}/company_code/{companyCode}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteLoginControlInfo(@PathVariable String loginId,
            @PathVariable String companyCode) {
        ImLoginControlInfo loginControlInfo = new ImLoginControlInfo();
        loginControlInfo.setLoginId(loginId);
        loginControlInfo.setCompanyCode(companyCode);
        return loginControlInfoService.deleteLoginControlInfo(loginControlInfo);
    }

    /**
     * delete login control info by login information list in one time.<br>
     *
     * @param loginControlInfoList
     *        the logincontrolinfo information list
     * @return deleted logincontrolinfo information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkLoginControlInfo(
            @RequestBody List<ImLoginControlInfo> loginControlInfoList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < loginControlInfoList.size(); i++) {
            resultList.add(loginControlInfoService
                    .deleteLoginControlInfo(loginControlInfoList.get(i)));
        }
        return resultList;
    }

    /**
     * delete login control info by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted logincontrolinfo information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteLoginControlInfoByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImLoginControlInfo> loginControlInfoList = null;
        try {
            loginControlInfoList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImLoginControlInfo.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < loginControlInfoList.size(); i++) {
            resultList.add(loginControlInfoService
                    .deleteLoginControlInfo(loginControlInfoList.get(i)));
        }
        return resultList;
    }

    /**
     * find login control info by loginid and companyCode.
     *
     * @param loginId
     *        loginId
     * @param companyCode
     *        companyCode
     * @return selected logincontrolinfo information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{loginId}/company_code/{companyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImLoginControlInfo findLoginControlInfo(
            @PathVariable String loginId, @PathVariable String companyCode) {
        ImLoginControlInfo loginControlInfo = new ImLoginControlInfo();
        loginControlInfo.setLoginId(loginId);
        loginControlInfo.setCompanyCode(companyCode);
        return loginControlInfoService.getLoginControlInfo(loginControlInfo);
         
    }

    /**
     * search login control info by logincontrolinfo
     *
     * @param loginControlInfo
     *        logincontrolinfo entity
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected logincontrolinfo information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImLoginControlInfo> searchLoginControlInfo(
            @RequestBody ImLoginControlInfo loginControlInfo,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return loginControlInfoService.searchLoginControlInfo(loginControlInfo,
                pageNo, pageSize, sort);
    }

}